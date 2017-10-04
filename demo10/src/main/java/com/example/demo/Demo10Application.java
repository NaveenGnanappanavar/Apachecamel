package com.example.demo;

import org.apache.camel.CamelContext;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo10Application {

	public static void main(String[] args) throws Exception {

		Producer queueProd = new Producer();
		queueProd.setupConnection();
		System.out.println(queueProd.toString());

		CamelContext context = new DefaultCamelContext();

		context.addRoutes(new RouteBuilder() {

			public void configure() throws Exception {
				System.out.println("Merged file");
				from("file:D:\\inbox?noop=true")
				.convertBodyTo(String.class)
						.aggregate(simple("${file:ext} == 'txt'"), new MyAggregationStrategy()).completionSize(2)
						.to("rabbitmq://localhost:5672/amq.direct?routingKey=Merge");

			}
		});

		context.start();
		Thread.sleep(4000);
		context.stop();
	}
}
