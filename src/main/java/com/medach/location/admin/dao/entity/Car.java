package com.medach.location.admin.dao.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Car.
 */
@Entity
@Table(name = "car")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;


    
    @ManyToOne
  	@JoinColumn(name = "id_modele")
    private Modele modele;

    @Column(name = "transmission_type")
    private String transmissionType;

    @Column(name = "number_of_doors")
    private Integer numberOfDoors;

    @Column(name = "number_of_bags")
    private Integer numberOfBags;

    @Column(name = "number_of_seats")
    private Integer numberOfSeats;

    @Column(name = "has_petrol")
    private Boolean hasPetrol;

    @Column(name = "has_diesel")
    private Boolean hasDiesel;

    @Column(name = "has_air_conditioning")
    private Boolean hasAirConditioning;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "type")
    private String type;
    

    @Column(name = "matricule")
    private String matricule;
    
    @Column(name = "disponibilite")
    private Date disponibilite;
    
    @Column(name = "prix")
    private BigDecimal prix;
    

    @OneToMany(mappedBy = "car",fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Booking> bookings = new HashSet<>();

    
    public Set<Booking> getBookings() {
		return bookings;
	}
    
    public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


  public Modele getModele() {
	return modele;
}
  public void setModele(Modele modele) {
	this.modele = modele;
}
  public Date getDisponibilite() {
	return disponibilite;
}
  public void setDisponibilite(Date disponibilite) {
	this.disponibilite = disponibilite;
}
  

    public String getTransmissionType() {
        return transmissionType;
    }

    public Car transmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
        return this;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Integer getNumberOfDoors() {
        return numberOfDoors;
    }

    public Car numberOfDoors(Integer numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
        return this;
    }

    public void setNumberOfDoors(Integer numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public Integer getNumberOfBags() {
        return numberOfBags;
    }

    public Car numberOfBags(Integer numberOfBags) {
        this.numberOfBags = numberOfBags;
        return this;
    }

    public void setNumberOfBags(Integer numberOfBags) {
        this.numberOfBags = numberOfBags;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public Car numberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
        return this;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Boolean isHasPetrol() {
        return hasPetrol;
    }

    public Car hasPetrol(Boolean hasPetrol) {
        this.hasPetrol = hasPetrol;
        return this;
    }

    public void setHasPetrol(Boolean hasPetrol) {
        this.hasPetrol = hasPetrol;
    }

    public Boolean isHasDiesel() {
        return hasDiesel;
    }

    public Car hasDiesel(Boolean hasDiesel) {
        this.hasDiesel = hasDiesel;
        return this;
    }

    public void setHasDiesel(Boolean hasDiesel) {
        this.hasDiesel = hasDiesel;
    }

    public Boolean isHasAirConditioning() {
        return hasAirConditioning;
    }

    public Car hasAirConditioning(Boolean hasAirConditioning) {
        this.hasAirConditioning = hasAirConditioning;
        return this;
    }

    public void setHasAirConditioning(Boolean hasAirConditioning) {
        this.hasAirConditioning = hasAirConditioning;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Car imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public Car type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMatricule() {
        return matricule;
    }

    public Car matricule(String matricule) {
        this.matricule = matricule;
        return this;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Car)) {
            return false;
        }
        return id != null && id.equals(((Car) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    
    

}
