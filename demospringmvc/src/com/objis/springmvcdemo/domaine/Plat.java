package com.objis.springmvcdemo.domaine;

// Generated 14 sept. 2010 22:49:51 by Hibernate Tools 3.2.4.GA

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Plat generated by hbm2java
 */
@Entity
@Table(name = "plat", catalog = "restaurant")
public class Plat implements java.io.Serializable {

	private int id;
	private String nomPlat;
	private String description;
	private String type;
	private float prix;
	private Date miseJour;
	private byte[] image;

	public Plat() {
	}

	public Plat(int id, String nomPlat, String description, String type,
			float prix, Date miseJour, byte[] image) {
		this.id = id;
		this.nomPlat = nomPlat;
		this.description = description;
		this.type = type;
		this.prix = prix;
		this.miseJour = miseJour;
		this.image = image;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "NomPlat", nullable = false)
	public String getNomPlat() {
		return this.nomPlat;
	}

	public void setNomPlat(String nomPlat) {
		this.nomPlat = nomPlat;
	}

	@Column(name = "Description", nullable = false)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "Type", nullable = false, length = 100)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "Prix", nullable = false, precision = 5)
	public float getPrix() {
		return this.prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "MiseJour", nullable = false, length = 10)
	public Date getMiseJour() {
		return this.miseJour;
	}

	public void setMiseJour(Date miseJour) {
		this.miseJour = miseJour;
	}

	@Column(name = "Image", nullable = false)
	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
