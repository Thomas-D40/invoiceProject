package com.tom.ws.soap.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tom.ws.soap.service.InvoiceService;

@Configuration
public class InvoiceServiceConfig {

	@Autowired
	private Bus bus;
	
	@Autowired
	private InvoiceService invoiceService;

	@Bean
	Endpoint endpoint() {
		Endpoint endpoint = new EndpointImpl(bus, invoiceService);
		endpoint.publish("/addinvoice");
		return endpoint;
	}

}
