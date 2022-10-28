package com.tom.ws.soap.config;

import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.wss4j.common.ConfigurationConstants;
import org.apache.wss4j.dom.WSConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tom.ws.soap.auth.UTPasswordCallback;
import com.tom.ws.soap.service.InvoiceService;

@Configuration
public class InvoiceServiceConfig {

	@Autowired
	private Bus bus;

	@Autowired
	private InvoiceService invoiceService;
	

	@Bean
	Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(bus, invoiceService);
		endpoint.publish("/addinvoice");

		Map<String, Object> inProps = new HashMap<>();
		inProps.put(ConfigurationConstants.ACTION, ConfigurationConstants.USERNAME_TOKEN);
		inProps.put(ConfigurationConstants.PASSWORD_TYPE, WSConstants.PASSWORD_TEXT);
		inProps.put(ConfigurationConstants.PW_CALLBACK_CLASS, UTPasswordCallback.class.getName());

		WSS4JInInterceptor inInterceptor = new WSS4JInInterceptor(inProps);
		
		endpoint.getInInterceptors().add(inInterceptor);

		return endpoint;
	}

}
