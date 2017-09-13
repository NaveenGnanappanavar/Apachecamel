package com.example.demo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("file:D:\\inbox?noop=true").convertBodyTo(String.class)
				.aggregate(simple("${file:ext} == 'txt'"), new MyAggregationStrategy()).completionSize(2)

				.to("file:D:\\outbox");

	}
}
