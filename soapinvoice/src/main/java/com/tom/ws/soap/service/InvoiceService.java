package com.tom.ws.soap.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tom.ws.soap.entity.Invoice;
import com.tom.ws.soap.repository.InvoiceRepository;

@WebService
@Service
public class InvoiceService {
	
	@Autowired
	private InvoiceRepository repository;

	
	@WebMethod
	public String createInvoice(@WebParam Invoice invoice) {
		repository.save(invoice);
		return "Invoice registered";
	}
	
	
}
