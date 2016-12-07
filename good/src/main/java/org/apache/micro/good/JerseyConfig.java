package org.apache.micro.good;

import org.apache.micro.good.service.GoodResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * Created by HuanagYueWen on 2016-12-7.
 */
@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(GoodResource.class) ;
    }
}
