package com.myshop.controller.plate;

import java.util.concurrent.atomic.AtomicLong;

import javax.validation.constraints.NotNull;

public class Plat {

	
	private Long id;
	
	@NotNull
	private String NomPlat ; 
	
	@NotNull
	private String Description;
	
	@NotNull
	private String Type ; 

	
	
	Long assignId() {
		this.id = idSequence.incrementAndGet();
		return id;
	}
	
	private static final AtomicLong idSequence = new AtomicLong();
	
	public Long getId() {
		return id;
	}

	void setId(Long id) {
		this.id = id;
	}

	public String getNomPlat() {
		return NomPlat;
	}
	public void setNomPlat(String nomPlat) {
		NomPlat = nomPlat;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
}
