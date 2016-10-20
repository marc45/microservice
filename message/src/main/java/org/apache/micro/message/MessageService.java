package org.apache.micro.message;

import org.apache.micro.message.domain.MessageRequest;
import org.apache.micro.message.domain.MessageResponse;

public interface MessageService {

	public MessageResponse tryMessage(MessageRequest request) ;
	
	public MessageResponse confirmMessage(MessageRequest request) ;
}
