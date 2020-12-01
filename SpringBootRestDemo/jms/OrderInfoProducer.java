package com.mercury.SpringBootRestDemo.jms;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.mercury.beans.OrderInfo;

@Component
public class OrderInfoProducer {
	
	@Autowired
	private JmsTemplate jmsQueueTemplate;
	
	public void sendOrderForReport(int oid) {
		
		OrderInfo oi = new OrderInfo();
		
		oi.setId(oid);
		
		oi.setQty(222);
		oi.setProductId(123);
		
		jmsQueueTemplate.send("OrderReport", new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage((Serializable)oi);
			}
		});
		//jmsQueueTemplate.send("OrderReport", session -> session.createObjectMessage((Serializable)oi));
	}
}
