package com.purnima.jain.openapi.json.request;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.purnima.jain.openapi.enums.GenderEnum;

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

	@Schema(description = "Gender of the Customer", example = "MALE", required = true)
	private GenderEnum gender;

	@Valid
	private List<HobbyPutRequestDto> hobbies;

}