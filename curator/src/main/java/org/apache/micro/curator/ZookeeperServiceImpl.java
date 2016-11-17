package org.apache.micro.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by HuangYueWen on 2016-11-17.
 */
public class ZookeeperServiceImpl implements ZookeeperService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperServiceImpl.class) ;

    private CuratorFramework curatorFramework  ;

    public void getNextZnode(String path) {

        try {
//            curatorFramework.getData().forPath()
            String s = curatorFramework.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("") ;
            curatorFramework.getData().watched().inBackground(new BackgroundCallback() {
                public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                    switch(event.getType()){
                        case CREATE :
                                break;
                        case DELETE:
                            break;
                        case EXISTS:
                            break;
                        case GET_DATA:
                            break;
                        case SET_DATA:
                            break;
                        case CHILDREN:
                            break;
                        case SYNC:
                            break;
                        case GET_ACL:
                            break;
                        case SET_ACL:
                            break;
                        case WATCHED:
                            break;
                        case CLOSING:
                            CloseableUtils.closeQuietly(client);
                            break;
                    }

                }
            }).forPath("") ;
        } catch (Exception e) {
//            LOGGER.error(e.getMessage(),e);
            throw new RuntimeException(e.getMessage()) ;
        }

    }
}
