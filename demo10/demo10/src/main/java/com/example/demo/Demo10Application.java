package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;





@SpringBootApplication
public class Demo10Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Demo10Application.class, args);

        Producer queueProd = new Producer();
        queueProd.setupConnection();
        System.out.println(queueProd.toString());

        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new RouteBuilder() {

            public void configure() throws Exception {
                System.out.println("hello world");
            	from("file:D:\\input?noop=true")
        		.aggregate(constant("all"), new MyAggregationStrategy()) 
        		.completionSize(2)
        		.to("rabbitmq://localhost:5672/amq.direct");
                    

            }
        });

        context.start();
        Thread.sleep(4000);
        context.stop();
	}
}
