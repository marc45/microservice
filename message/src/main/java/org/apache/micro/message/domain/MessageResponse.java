package org.apache.micro.message.domain;

public class MessageResponse {

    private long id ;

    private long requestId ;

    private String status ;

    private String messagae ;

    private String messageBody ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessagae() {
        return messagae;
    }

    public void setMessagae(String messagae) {
        this.messagae = messagae;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
}
