package com.medach.location.admin.api.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.medach.location.admin.dao.entity.Car;
import com.medach.location.admin.dao.entity.Modele;

public class CarDto {

	private Long id_modele;

	private String transmissionType;

	private Integer numberOfDoors;

	private Integer numberOfBags;

	private Integer numberOfSeats;

	private Boolean hasPetrol;

	private Boolean hasDiesel;

	private Boolean hasAirConditioning;

	private String imageUrl;

	private String type;

	private String matricule;

	private Date disponibilite;

	private BigDecimal prix;

	public Long getId_modele() {
		return id_modele;
	}

	public void setId_modele(Long id_modele) {
		this.id_modele = id_modele;
	}

	public String getTransmissionType() {
		return transmissionType;
	}

	public void setTransmissionType(String transmissionType) {
		this.transmissionType = transmissionType;
	}

	public Integer getNumberOfDoors() {
		return numberOfDoors;
	}

	public void setNumberOfDoors(Integer numberOfDoors) {
		this.numberOfDoors = numberOfDoors;
	}

	public Integer getNumberOfBags() {
		return numberOfBags;
	}

	public void setNumberOfBags(Integer numberOfBags) {
		this.numberOfBags = numberOfBags;
	}

	public Integer getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public Boolean getHasPetrol() {
		return hasPetrol;
	}

	public void setHasPetrol(Boolean hasPetrol) {
		this.hasPetrol = hasPetrol;
	}

	public Boolean getHasDiesel() {
		return hasDiesel;
	}

	public void setHasDiesel(Boolean hasDiesel) {
		this.hasDiesel = hasDiesel;
	}

	public Boolean getHasAirConditioning() {
		return hasAirConditioning;
	}

	public void setHasAirConditioning(Boolean hasAirConditioning) {
		this.hasAirConditioning = hasAirConditioning;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public Date getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(Date disponibilite) {
		this.disponibilite = disponibilite;
	}

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public Car dtoToCar() {

		Car car = new Car();
		car.setId(null);
		car.setDisponibilite(disponibilite);
		car.setHasAirConditioning(hasAirConditioning);
		car.setHasDiesel(hasDiesel);
		car.setHasPetrol(hasPetrol);
		car.setImageUrl(imageUrl);
		car.setMatricule(matricule);
		Modele modele = new Modele();
		modele.setId(id_modele);
		car.setModele(modele);
		car.setNumberOfBags(numberOfBags);
		car.setNumberOfDoors(numberOfDoors);
		car.setNumberOfSeats(numberOfSeats);
		car.setTransmissionType(transmissionType);
		car.setType(type);

		return car;
	}

}
