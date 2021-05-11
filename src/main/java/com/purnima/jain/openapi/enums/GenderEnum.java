package com.purnima.jain.openapi.enums;

public enum GenderEnum {

	MALE("MALE", "Male"), 
	FEMALE("FEMALE", "Female");

	private final String key;
	private final String value;

	private GenderEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return this.key;
	}

	public String getValue() {
		return this.value;
	}

	@Override // For Swagger Output as "MALE", "FEMALE"
	public String toString() {
		return this.key;
	}

}
