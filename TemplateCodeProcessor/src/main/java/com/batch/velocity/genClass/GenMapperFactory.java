package com.batch.velocity.genClass;

import org.springframework.stereotype.Service;



public class GenMapperFactory extends AbtractFactoryMapper {
	private String champ1; 
	private String data1Type; 
	private String documentation1;
	
	private String champ2;
	private String data2Type; 
	private String documentation2;
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GenMapperFactory [champ1=");
		builder.append(champ1);
		builder.append(", champ2=");
		builder.append(champ2);
		builder.append(", data1Type=");
		builder.append(data1Type);
		builder.append(", data2Type=");
		builder.append(data2Type);
		builder.append(", documentation1=");
		builder.append(documentation1);
		builder.append(", documentation2=");
		builder.append(documentation2);
		builder.append("]");
		return builder.toString();
	}

	
	/**
	 * @return the champ1
	 */
	public String getChamp1() {
		return champ1;
	}
	/**
	 * @param champ1 the champ1 to set
	 */
	public void setChamp1(String champ1) {
		this.champ1 = champ1;
	}
	/**
	 * @return the data1Type
	 */
	public String getData1Type() {
		return data1Type;
	}
	/**
	 * @param data1Type the data1Type to set
	 */
	public void setData1Type(String data1Type) {
		this.data1Type = data1Type;
	}
	/**
	 * @return the documentation1
	 */
	public String getDocumentation1() {
		return documentation1;
	}
	/**
	 * @param documentation1 the documentation1 to set
	 */
	public void setDocumentation1(String documentation1) {
		this.documentation1 = documentation1;
	}
	/**
	 * @return the champ2
	 */
	public String getChamp2() {
		return champ2;
	}
	/**
	 * @param champ2 the champ2 to set
	 */
	public void setChamp2(String champ2) {
		this.champ2 = champ2;
	}
	/**
	 * @return the data2Type
	 */
	public String getData2Type() {
		return data2Type;
	}
	/**
	 * @param data2Type the data2Type to set
	 */
	public void setData2Type(String data2Type) {
		this.data2Type = data2Type;
	}
	/**
	 * @return the documentation2
	 */
	public String getDocumentation2() {
		return documentation2;
	}
	/**
	 * @param documentation2 the documentation2 to set
	 */
	public void setDocumentation2(String documentation2) {
		this.documentation2 = documentation2;
	}

	
}
