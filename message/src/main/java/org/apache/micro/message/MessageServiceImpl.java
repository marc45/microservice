package org.apache.micro.message;

import org.apache.micro.message.domain.MessageRequest;
import org.apache.micro.message.domain.MessageResponse;
import org.apache.micro.message.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
@Service
@RequestMapping(path="")
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private MessageMapper messageMapper ;

	@Override
	public MessageResponse tryMessage(MessageRequest request) {
		
		return null;
	}

	@Override
	public MessageResponse confirmMessage(MessageRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
