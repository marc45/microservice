package org.apache.micro.order.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by HuanagYueWen on 2016-12-7.
 */
@FeignClient(name="productApplication")
public interface GoodResource {

    @Path("/get/{appName}")
    @GET
    public Response test(@PathParam("appName") String appName) ;
}
