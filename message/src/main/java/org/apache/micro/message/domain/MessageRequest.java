package org.apache.micro.message.domain;

public class MessageRequest {


    /**
     *
     */
    private long id ;

    private String application ;

    private String service ;

    /**
     * 默认都使用post
     */
    private String method ;

    private String messageBody ;

    private String status ;





//    private Map<String,String> messageProperties ;


}
