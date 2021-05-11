package com.purnima.jain.openapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.purnima.jain.openapi.enums.GenderEnum;
import com.purnima.jain.openapi.json.request.CustomerPostRequestDto;
import com.purnima.jain.openapi.json.request.CustomerPutRequestDto;
import com.purnima.jain.openapi.json.response.CustomerGetResponseDto;
import com.purnima.jain.openapi.json.response.HobbyGetResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/v1/customers")
@Slf4j
@Tag(name = "Customer API", description = "APIs for Customer")
public class OpenApiController {
	
	@Operation(operationId = "getCustomers", summary = "Gets All Customers based on filtration criteria",
			description = "Retrieves All Customers based on filtration criteria", tags = {"customer"},
			parameters = {
					@Parameter(name = "firstName", description = "First Name of the Customer", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string")),
					@Parameter(name = "lastName", description = "Last Name of the Customer", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string")),
					@Parameter(name = "gender", description = "Gender of the Customer", in = ParameterIn.QUERY, required = false, schema = @Schema(implementation = GenderEnum.class)),
					@Parameter(name = "hobbies", description = "Hobbies of the Customer", in = ParameterIn.QUERY, required = false, array = @ArraySchema(schema = @Schema(type = "string")))
			},
			responses = {
					@ApiResponse(responseCode = "200", description = "Customers Retrieved Successfully", 
							content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = CustomerGetResponseDto.class)))),
					@ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
					@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
			})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerGetResponseDto>> getCustomers(
			@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName, 
			@RequestParam(required = false) String gender,			
			@RequestParam(required = false) List<String> hobbies
			) {
		log.info("Inside getCustomers() with search criteria as firstName={}, lastName={}, gender={}, hobbies={}", firstName, lastName, gender, hobbies);
		List<CustomerGetResponseDto> customerList = new ArrayList<>();
		
		CustomerGetResponseDto customerJohnDoe = new CustomerGetResponseDto();
		customerJohnDoe.setCustomerId(100);
		customerJohnDoe.setFirstName("John");
		customerJohnDoe.setLastName("Doe");
		customerJohnDoe.setGender(GenderEnum.MALE);
		customerJohnDoe.setHobbies(List.of(new HobbyGetResponseDto("Reading"), new HobbyGetResponseDto("Gardening")));
		customerList.add(customerJohnDoe);
		
		CustomerGetResponseDto customerJaneDoe = new CustomerGetResponseDto();
		customerJaneDoe.setCustomerId(101);
		customerJaneDoe.setFirstName("Jane");
		customerJaneDoe.setLastName("Doe");
		customerJaneDoe.setGender(GenderEnum.FEMALE);
		customerJaneDoe.setHobbies(List.of(new HobbyGetResponseDto("Movies"), new HobbyGetResponseDto("Running")));
		customerList.add(customerJaneDoe);

		return ResponseEntity.ok().body(customerList);
	}	
	
	@Operation(operationId = "getCustomerById", summary = "Gets Customer for a given customerId",
			description = "Retrieves Customer for a given CustomerId", tags = {"customer"},
			parameters = {
					@Parameter(name = "customerId", description = "Id of the Customer", in = ParameterIn.PATH, required = true, schema = @Schema(type = "integer"))
			},
			responses = {
					@ApiResponse(responseCode = "200", description = "Customer Retrieved Successfully", 
							content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerGetResponseDto.class))),
					@ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
					@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
					@ApiResponse(responseCode = "404", description = "Customer Not Found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
					
			})
	@GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerGetResponseDto> getCustomerById(@PathVariable("customerId") Integer customerId) {
		log.info("Input Customer Id :: {}", customerId);
		
		CustomerGetResponseDto customerJohnDoe = new CustomerGetResponseDto();
		customerJohnDoe.setCustomerId(customerId);
		customerJohnDoe.setFirstName("John");
		customerJohnDoe.setLastName("Doe");
		customerJohnDoe.setGender(GenderEnum.MALE);
		customerJohnDoe.setHobbies(List.of(new HobbyGetResponseDto("Reading"), new HobbyGetResponseDto("Gardening")));
		return ResponseEntity.ok().body(customerJohnDoe);
	}
	
	@Operation(operationId = "createCustomer", summary = "Creates a new Customer",
			description = "Creates a new Customer", tags = {"customer"},
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerPostRequestDto.class))
            ),
			responses = {
					@ApiResponse(responseCode = "200", description = "Customer Created Successfully", 
							content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerGetResponseDto.class))),
					@ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
					@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
			})
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerGetResponseDto> createCustomer(
			@Parameter(description = "Customer as Json", required = true)
			@Valid @RequestBody CustomerPostRequestDto customerPostRequestDto) {
		log.info("Input Customer Request Body :: {}", customerPostRequestDto);
		
		CustomerGetResponseDto customerJohnDoe = new CustomerGetResponseDto();
		customerJohnDoe.setCustomerId(getNextId());
		customerJohnDoe.setFirstName(customerPostRequestDto.getFirstName());
		customerJohnDoe.setLastName(customerPostRequestDto.getLastName());
		customerJohnDoe.setGender(customerPostRequestDto.getGender());
		customerJohnDoe.setHobbies(customerPostRequestDto.getHobbies().stream().map(hobby -> new HobbyGetResponseDto(hobby.getHobby())).collect(Collectors.toList()));
		return ResponseEntity.ok().body(customerJohnDoe);
	}
	
	// This gives you a random number in between 10 (inclusive) and 100 (exclusive)
	private Integer getNextId() {
		Random r = new Random();
		int low = 10;
		int high = 100;
		int result = r.nextInt(high-low) + low;
		return result;
	}
	
	@Operation(operationId = "editCustomer", summary = "Updates an existing Customer",
			description = "Updates an existing Customer", tags = {"customer"},
			parameters = {
					@Parameter(name = "customerId", description = "Id of the Customer", in = ParameterIn.PATH, required = true, schema = @Schema(type = "integer"))
			},
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerPutRequestDto.class))
            ),
			responses = {
					@ApiResponse(responseCode = "200", description = "Customer updated successfully", 
							content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerGetResponseDto.class))),
					@ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
					@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
			})
	@PutMapping(value = "/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerGetResponseDto> editCustomer(
			@PathVariable("customerId") Integer customerId,
			@Parameter(description = "Customer as Json", required = true) @Valid @RequestBody CustomerPutRequestDto customerPutRequestDto) {
		log.info("Input Customer for Update:: {}", customerId);
		log.info("Edit Customer Request Body :: {}", customerPutRequestDto);
		
		CustomerGetResponseDto customerJohnDoe = new CustomerGetResponseDto();
		customerJohnDoe.setCustomerId(customerId);
		customerJohnDoe.setFirstName(customerPutRequestDto.getFirstName());
		customerJohnDoe.setLastName(customerPutRequestDto.getLastName());
		customerJohnDoe.setGender(customerPutRequestDto.getGender());
		customerJohnDoe.setHobbies(customerPutRequestDto.getHobbies().stream().map(hobby -> new HobbyGetResponseDto(hobby.getHobby())).collect(Collectors.toList()));
		
		return ResponseEntity.ok().body(customerJohnDoe);
	}
	
	@Operation(operationId = "deleteCustomer", summary = "Deletes an existing Customer",
			description = "Deletes an existing Customer", tags = {"customer"},			
			parameters = {
					@Parameter(name = "customerId", description = "Customer Id of the Customer", in = ParameterIn.PATH, required = true, schema = @Schema(type = "integer"))
			},
			responses = {
					@ApiResponse(responseCode = "200", description = "Customer deleted successfully"),
					@ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
					@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
					@ApiResponse(responseCode = "404", description = "Guarantee Type Not Found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
			})
	@DeleteMapping(value = "/{customerId}")
	public ResponseEntity<Void> deleteCustomer(
			@Parameter(description="Id of the Customer to be deleted. Cannot be empty.", required = true) @PathVariable("customerId") Integer customerId) {
		log.info("Input Customer Id for deletion:: {}", customerId);
		
		return ResponseEntity.ok().build();
	}

}
