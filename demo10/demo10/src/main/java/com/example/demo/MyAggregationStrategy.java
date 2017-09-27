package com.example.demo;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class MyAggregationStrategy implements AggregationStrategy {
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		if (oldExchange == null) {

			return newExchange;
		}

		String orders = oldExchange.getIn().getBody(String.class);
		String newLine = newExchange.getIn().getBody(String.class);

		orders = orders + ";" + newLine;

		oldExchange.getIn().setBody(orders);

		return oldExchange;

	}
}
