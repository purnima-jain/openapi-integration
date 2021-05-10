package com.purnima.jain.openapi.json.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonRootName("customer")
@Schema(name = "CustomerPutRequestDto", description = "Customer PUT Request Model")
public class CustomerPutRequestDto {

	@Schema(description = "First Name of the Customer", example = "John", required = true)
	private String firstName;

	@Schema(description = "Last Name of the Customer", example = "Doe", required = true)
	private String lastName;

	@Schema(description = "Gender of the Customer", example = "Male", required = true)
	private String gender;

	private List<HobbyPutRequestDto> hobbies;

}