package com.purnima.jain.openapi.json.response;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonRootName("HobbyGetResponseDto")
@Schema(name = "HobbyGetResponseDto", description = "Customer's Hobbies GET Response Model")
public class HobbyGetResponseDto {

	@Schema(description = "Hobby of the Customer", example = "Gardening")
	@Pattern(regexp = "Gardening|Reading|Movies|Running", flags = Pattern.Flag.CASE_INSENSITIVE)
	private String hobby;

}
