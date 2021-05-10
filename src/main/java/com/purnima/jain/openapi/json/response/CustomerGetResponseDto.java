package com.purnima.jain.openapi.json.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonRootName("customer")
@Schema(name = "CustomerGetResponseDto", description = "Customer GET Response Model")
public class CustomerGetResponseDto {

	@Schema(description = "Id of the Customer", example = "11111")
	private Integer customerId;

	@Schema(description = "First Name of the Customer", example = "John")
	private String firstName;

	@Schema(description = "Last Name of the Customer", example = "Doe")
	private String lastName;

	@Schema(description = "Gender of the Customer", example = "Male")
	private String gender;

	private List<HobbyGetResponseDto> hobbies;

}
