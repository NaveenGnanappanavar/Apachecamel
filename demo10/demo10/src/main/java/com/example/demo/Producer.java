package com.example.demo;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
	public class Producer {
	    public static final String QUEUE_NAME = "Send";
	    public static Connection connection;

	    public void setupConnection() throws IOException, TimeoutException {
	        ConnectionFactory factory = new ConnectionFactory();
	        factory.setHost("192.168.99.100");
	        factory.setUsername("guest");
	        factory.setPassword("guest");

	        connection = factory.newConnection();
	        Channel channel = connection.createChannel();
	        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	        channel.basicPublish("", QUEUE_NAME, null, null );
	    }
}

