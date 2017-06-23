package com.processamentotweets.api.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseDTO {
	private String keyword;
	private List<PlaceKeyword> placeKeyword;
	
	public ResponseDTO(){
		this.placeKeyword = new ArrayList<PlaceKeyword>();
	}
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public List<PlaceKeyword> getPlaceKeyword() {
		return placeKeyword;
	}
	public void setPlaceKeyword(List<PlaceKeyword> placeKeyword) {
		this.placeKeyword = placeKeyword;
	}
}
