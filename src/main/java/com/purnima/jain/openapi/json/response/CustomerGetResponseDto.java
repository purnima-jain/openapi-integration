package com.purnima.jain.openapi.json.response;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.purnima.jain.openapi.enums.GenderEnum;

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

	@Schema(description = "Gender of the Customer", example = "MALE")
	private GenderEnum gender;

	@Valid
	private List<HobbyGetResponseDto> hobbies;

}
