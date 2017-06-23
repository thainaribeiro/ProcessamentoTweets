package com.processamentotweets.domain.enums;

public enum StatesEnum {
	RO("RO","Rondônia"),
	AC("AC","Acre"),
	AM("AM","Amazonas"),
	RR("RR","Roraima"),
	PA("PA","Pará"),
	AP("AP","Amapá"),
	TO("TO","Tocantins"),
	MA("MA","Maranhão"),
	PI("PI","Piauí"),
	CE("CE","Ceará"),
	RN("RN","Rio Grande do Norte"),
	PB("PB","Paraíba"),
	PE("PE","Pernambuco"),
	AL("AL","Alagoas"),
	SE("SE","Sergipe"),
	BA("BA","Bahia"),
	MG("MG","Minas Gerais"),
	ES("ES","Espírito Santo"),
	RJ("RJ","Rio de Janeiro"),
	SP("SP","São Paulo"),
	PR("PR","Paraná"),
	SC("SC","Santa Catarina"),
	RS("RS","Rio Grande do Sul"),
	MS("MS","Mato Grosso do Sul"),
	MT("MT","Mato Grosso"),
	GO("GO","Goiás"),
	DF("DF","Distrito Federal");

	private final String uf;
	private final String name;

	private StatesEnum(String uf,String nome) {
		this.uf = uf;
		this.name = nome;
	}
	
	public String getCodigo() {
		return uf;
	}
	
	public String getName(){
		return name;
	}
	
	public String getUf(){
		return uf;
	}
	
	public static String getState(String state) {
		if(state != null && !state.trim().isEmpty()){
			StatesEnum [] valores = values();
			for (int i = 0; i < valores.length; i++) {
				if(state.contains(valores[i].getName()) || state.contains(valores[i].getUf())) {
					return valores[i].getUf();
				}
			}
		}
		return null;
	}
}