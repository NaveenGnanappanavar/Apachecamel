package com.example.demo;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class MyAggregationStrategy implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange arg0, Exchange arg1) {
		// TODO Auto-generated method stub

		if (arg0 == null) {
			return arg1;
		}

		String first = arg0.getIn().getBody(String.class);
		String second = arg1.getIn().getBody(String.class);

		first = first + ";" + second;

		arg0.getIn().setBody(first);

		return arg0;

	}

}
