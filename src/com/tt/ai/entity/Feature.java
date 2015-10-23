package com.tt.ai.entity;

public enum Feature {
	bold("b"),h1("h1"),h2("h2"),h3("h3");
	private final String value;
	
	private Feature(String value){
		this.value=value;
	}
	public String value(){
		return this.value;
	}
}

