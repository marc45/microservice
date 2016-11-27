package org.apache.micro.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by HuangYueWen on 2016-11-17.
 */
public class ZookeeperTest {

    private CuratorFramework client ;

    private String testPath = "/test" ;

    private CountDownLatch countDownLatch = null ;

    @Before
    public void setup(){

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3) ;
        client = CuratorFrameworkFactory.newClient("192.168.20.170",retryPolicy) ;
        client.start();

        try {
            final NodeCache nodeCache = new NodeCache(client,testPath) ;
            nodeCache.start();
            nodeCache.getListenable( ).addListener(new NodeCacheListener() {
                public void nodeChanged() throws Exception {
                    nodeCache.getCurrentData().getStat() ;
                    System.out.println(new String(nodeCache.getCurrentData().getData())) ;
                    System.out.println(nodeCache.getCurrentData().getPath()) ;
                }
            });
        } catch (Exception e) {
            CloseableUtils.closeQuietly(client);
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testConnect(){
        try {
            List<String> children = client.getChildren().forPath("/") ;
            Assert.assertNotNull(children);
        } catch (Exception e) {
            CloseableUtils.closeQuietly(client);
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testCreateChildrenPath(){
        int pool = 5 ;
        countDownLatch = new CountDownLatch(pool) ;
        ExecutorService service = Executors.newFixedThreadPool(pool) ;
        for(int i =0 ;i<pool ;i++){
            service.submit(new R()) ;
        }

        service.shutdown();
        while(countDownLatch.getCount() != 0){
            try {
                Thread.sleep(3000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public class R implements Runnable{

        @Override
        public void run() {
            System.out.println(client.getState().name());
            String root = testPath ;
            List<String> children = null;
            try {
                children = client.getChildren().forPath("/test");
            } catch (Exception e) {
                e.printStackTrace();
            }

            String success = null ;
            while(true){
                try {
                    success = getNextNode(children) ;
                    client.create().forPath(root+"/"+success) ;
                    break ;
                } catch (Exception e) {
                    CloseableUtils.closeQuietly(client);
                }
            }
            System.out.println("=============="+success);
            countDownLatch.countDown();
        }
    }

    public String getNextNode(List<String> children){
        int max = 0 ;
        if(children != null && children.size() > 0){

            for(String child : children){
                 if(Integer.valueOf(child) > max){
                     max = Integer.valueOf(child) ;
                 }
            }
            max = max+1;
        }
        return String.valueOf(max) ;
    }

    public void testSetData(){
        try {
            Stat stat = client.setData().forPath("/test");
           


        } catch (Exception e) {
            CloseableUtils.closeQuietly(client);
            Assert.fail(e.getMessage());

        }
    }

    @Test
    public void testDelete(){
        try {
            Void path = client.delete().forPath("/test") ;
            List<String> children = client.getChildren().forPath("/") ;
            Assert.assertNotNull(children);
            boolean existsTestNode = false ;
            for(String child : children){
                if("test".equals(child)){
                    existsTestNode = true ;
                    break ;
                }
            }
            Assert.assertFalse(existsTestNode);
        } catch (Exception e) {
            CloseableUtils.closeQuietly(client);
            Assert.fail(e.getMessage());
        }
    }

    @After
    public void close(){
        CloseableUtils.closeQuietly(client);
    }


}
