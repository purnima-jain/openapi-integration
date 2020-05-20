package com.purnima.jain.openapi.json;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonRootName("customer")
@Schema(name="CustomerPostDto", description="Customer Post DTO Model")
public class CustomerPostDto {
	
	@Schema(description = "Id of the Customer", example = "100", required = true)
	@NotNull(message = "CustomerId cannot be null")
	private Integer customerId;

	@Schema(description = "Name of the Customer", example = "John Doe", required = true)
	@NotBlank(message = "Customer Name cannot be blank")
	// @EnumValidator(enumClass = OperationType.class, message = ErrorCode.ENUM_OPERATION_TYPE, required = false)
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
