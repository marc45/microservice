package org.apache.micro.curator;

/**
 * Created by HuangYueWen on 2016-11-17.
 */
public interface ZookeeperService {

    String getDistributedLock(String appName,String serviceName,String id) ;

}
