package com.sec21.vaadin.model;

import java.math.BigDecimal;
import java.util.Date;

public class Client {

	private String name;
	
	private Date birthDay;
	
	private BigDecimal value;
	
	private DocumentType type;

	public Client(){
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public DocumentType getType() {
		return type;
	}

	public void setType(DocumentType type) {
		this.type = type;
	}
			
}
