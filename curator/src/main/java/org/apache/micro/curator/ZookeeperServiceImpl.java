package org.apache.micro.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

/**
 * Created by HuangYueWen on 2016-11-17.
 */
public class ZookeeperServiceImpl implements ZookeeperService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperServiceImpl.class);

    private CuratorFramework curatorFramework;

    @Override
    public String getDistributedLock(String appName, String serviceName, String id) {
        boolean isSuccess = false;

        String path = appName + File.pathSeparator + serviceName + File.pathSeparator + id;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("distributed lock path is {}", path);
        }
        try {
            curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(path);
            isSuccess = true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
        return path;
    }

    public void releaseDistributedLock(String path) {
        try {
            curatorFramework.delete().forPath(path);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 开始序列号是0开始
     *
     * @param appName
     * @param serviceName
     */
    public String getNextSeqNo(String appName, String serviceName) {
        String parentPath = appName + File.pathSeparator + serviceName + serviceName + File.pathSeparator;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("parent path is {}", parentPath);
        }
        String childPath = null;
        try {
            List<String> children = getChildren(parentPath) ;
            while (true) {
                String nextNode = childPath = getNextNode(children);
                childPath = curatorFramework.create().forPath(parentPath + File.pathSeparator + nextNode);
                break;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("child path is {}", parentPath);
        }
        return childPath;
    }

    private List<String> getChildren(String parentPath){
        assertNotNull(parentPath,"parent path can not be null");

        List<String> children = null;
        try {
            children = curatorFramework.getChildren().forPath(parentPath) ;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
        return children ;
    }

    private void assertNotNull(String path ,String message){
        if(path == null || "".equals(path) ){
            throw new IllegalArgumentException(message) ;
        }
    }

    private String getNextNode(List<String> children) {

        int max = 0;
        if (children != null && children.size() > 0) {

            for (String child : children) {
                if (Integer.valueOf(child) > max) {
                    max = Integer.valueOf(child);
                }
            }
            max = max + 1;
        }
        return String.valueOf(max);
    }

    private String createPathWithEphemeral(String path){
        assertNotNull(path,"parent path can not be null");
        String resultPath = null ;
        try {
            resultPath = curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(path) ;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }
        return resultPath ;
    }

    public void getSeqNoWithRange(String appName, String serviceName, int maxNum) {

    }
}
