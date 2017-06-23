package com.processamentotweets.api.dto;

public class PlaceKeyword {
	private String state;
	private Integer frequency;
	
	public PlaceKeyword(){
		
	}
	
	public PlaceKeyword(String state, Integer frequency){
		this.state = state;
		this.frequency = frequency;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getFrequency() {
		return frequency;
	}
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}
	
	
}
