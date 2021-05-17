package com.purnima.jain.openapi.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GenderEnum {

	MALE("MAN", "Male"), 
	FEMALE("WOMAN", "Female");

	private final String key;
	private final String value;

	private GenderEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	@JsonValue // For having "MAN" & "WOMAN" as Json Input in RequestBody
	public String getKey() {
		return this.key;
	}

	public String getValue() {
		return this.value;
	}

	@Override // For possible values in Swagger Schema to appear as "MAN", "WOMAN"
	public String toString() {
		return this.key;
	}

}
