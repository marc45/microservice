package org.apache.micro.common.concurrency;

import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuanagYueWen on 2016-11-29.
 */
public class MapperSelectConcurrency {

    public static void main(String[] args){
        List<String> data = new ArrayList<>() ;
        data.add("hello") ;
        data.add("world") ;
        Observable.fromArray(data).subscribe( item -> System.out.println(item)) ;
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
