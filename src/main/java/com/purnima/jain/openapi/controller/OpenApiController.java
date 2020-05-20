package com.purnima.jain.openapi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.purnima.jain.openapi.json.Customer;
import com.purnima.jain.openapi.json.CustomerPostDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Customer API", description = "APIs for Customer")
public class OpenApiController {
	
	@Operation(operationId = "getCustomerById", summary = "Gets Customer for a given customerId",
			description = "Retrieves Customer for a given CustomerId", tags = {"customer"},
			parameters = {
					@Parameter(name = "customerId", description = "Id of the Customer", in = ParameterIn.PATH, required = true, schema = @Schema(type = "integer"))
			},
			responses = {
					@ApiResponse(responseCode = "200", description = "Customer Retrieved Successfully", 
							content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Customer.class))),
					@ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
					@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
					@ApiResponse(responseCode = "404", description = "Customer Not Found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
					
			})
	@GetMapping(value = "/customer/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer getCustomerById(@PathVariable Integer customerId) {
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		customer.setName("John Doe");
		return customer;

	}
	
	@Operation(operationId = "createCustomer", summary = "Creates a new Customer",
			description = "Creates a new Customer", tags = {"customer"},
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CustomerPostDto.class))
            ),
			responses = {
					@ApiResponse(responseCode = "200", description = "Customer Created Successfully", 
							content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Customer.class))),
					@ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
					@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
			})
	@PostMapping(value = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer createCustomer(
			@Parameter(description = "Customer as Json", required = true)
			@Valid @RequestBody CustomerPostDto customerPostDto) {
		Customer customer = new Customer();
		customer.setCustomerId(customerPostDto.getCustomerId());
		customer.setName(customerPostDto.getName());
		return customer;
	}
	
	@Operation(operationId = "getAllCustomers", summary = "Gets All Customers",
			description = "Retrieves All Customers", tags = {"customer"},
			responses = {
					@ApiResponse(responseCode = "200", description = "All Customers Retrieved Successfully", 
							content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = Customer.class)))),
					@ApiResponse(responseCode = "400", description = "Invalid Input", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
					@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
					@ApiResponse(responseCode = "404", description = "Customer Not Found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
					
			})
	@GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> getAllCustomers() {
		List<Customer> customerList = new ArrayList<>();
		
		Customer customerJohnDoe = new Customer();
		customerJohnDoe.setCustomerId(100);
		customerJohnDoe.setName("John Doe");
		customerList.add(customerJohnDoe);
		
		Customer customerJaneDoe = new Customer();
		customerJaneDoe.setCustomerId(101);
		customerJaneDoe.setName("Jane Doe");
		customerList.add(customerJaneDoe);
		
		return customerList;
	}	

}
