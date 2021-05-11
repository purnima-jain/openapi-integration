package com.purnima.jain.openapi.json.request;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName("HobbyPutRequestDto")
@Schema(name = "HobbyPutRequestDto", description = "Customer's Hobbies PUT Request Model")
public class HobbyPutRequestDto {

	@Schema(description = "Hobby of the Customer", example = "Gardening", required = true)
	@Pattern(regexp = "Gardening|Reading|Movies|Running", flags = Pattern.Flag.CASE_INSENSITIVE)
	private String hobby;

}