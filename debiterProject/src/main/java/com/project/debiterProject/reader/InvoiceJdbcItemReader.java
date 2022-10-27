package com.project.debiterProject.reader;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.project.debiterProject.entity.Invoice;

@Component
public class InvoiceJdbcItemReader {
	
	@Autowired
	@Qualifier("bankDataSource")
	private DataSource bankDataSource;
	
	public JdbcCursorItemReader<Invoice> invoiceJdbcReader() {
		JdbcCursorItemReader<Invoice> jdbcCursorItemReader = new JdbcCursorItemReader<>();
		jdbcCursorItemReader.setDataSource(bankDataSource);
		jdbcCursorItemReader.setSql("select id, client_id as idClient, amount, paid as isPaid from invoices where paid = 0");
		
		jdbcCursorItemReader.setRowMapper(new BeanPropertyRowMapper<Invoice>() {
			{
				setMappedClass(Invoice.class);
			}
		});
		
		return jdbcCursorItemReader;
	}

}
