package com.processamentotweets.domain.keyword;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Keyword", schema = "Keyword")
public class Keyword implements Serializable {
	private static final long serialVersionUID = 100835320792986936L;
	
	@Id
	@Column(name = "idKeyword")
	private Long idKeyword;
	
	@Column(name = "keyword")
	private String keyword;
	
	@Column(name = "uf")
	private String uf;
	
	public Long getIdKeyword() {
		return idKeyword;
	}

	public void setIdKeyword(Long idKeyword) {
		this.idKeyword = idKeyword;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
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
