package com.purnima.jain.openapi.json;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name="Customer", description="Customer Model")
public class Customer {

	@Schema(description = "Id of the Customer", example = "100", required = true)
	private Integer customerId;	
	
	@Schema(description = "Name of the Customer", example = "John Doe", required = true)
	private String name;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
