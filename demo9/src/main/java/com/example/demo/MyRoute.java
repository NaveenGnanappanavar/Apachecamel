package com.example.demo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		  from("file:D:\\input?noop=true")
          .choice()
          .when()
          .simple("${file:ext} == 'xml'")
          .to("file:D:\\first")
          .when()
          .simple("${file:ext} == 'csv'")
          .to("file:D:\\second")
          .otherwise()
            .to("file:D:\\output"); 
		
	}

}
