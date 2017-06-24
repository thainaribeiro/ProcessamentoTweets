package com.processamentotweets.domain.tweets;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tweets", schema = "Tweets")
public class Tweets implements Serializable {
	private static final long serialVersionUID = 100835320792986936L;
	
	@Id
	@Column(name = "idTweet")
	private Long id;
	
	@Column(name = "codeTweet")
	private String codeTweet;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "place")
	private String place;
	
	@Column(name = "keyword")
	private String keyword;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeTweet() {
		return codeTweet;
	}

	public void setCodeTweet(String codeTweet) {
		this.codeTweet = codeTweet;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
