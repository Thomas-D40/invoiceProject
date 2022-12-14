package com.project.debiterProject.reader;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.project.debiterProject.entity.Client;
import com.project.debiterProject.entity.Invoice;

@Component
public class JpaCursorReader {
	
	@Autowired
	@Qualifier("bankEntityManagerFactory")
	private EntityManagerFactory bankEntityManagerFactory;
	
	public JpaCursorItemReader<Invoice> invoiceJpaCursorItemReader() {
		JpaCursorItemReader<Invoice> invoiceJpaCursorItemReader = new JpaCursorItemReader<>();
		
		invoiceJpaCursorItemReader.setEntityManagerFactory(bankEntityManagerFactory);
		invoiceJpaCursorItemReader.setQueryString("From Invoice");

		
		return invoiceJpaCursorItemReader;
	}
	
	public JpaCursorItemReader<Client> clientJpaCursorItemReader() {
		JpaCursorItemReader<Client> jpaCursorItemReader = new JpaCursorItemReader<>();
		
		jpaCursorItemReader.setEntityManagerFactory(bankEntityManagerFactory);
		jpaCursorItemReader.setQueryString("From Client");
		
		return jpaCursorItemReader;
	}
	
	

}
