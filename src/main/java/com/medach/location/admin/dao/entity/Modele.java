package com.medach.location.admin.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "modele")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Modele implements Serializable {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	    @SequenceGenerator(name = "sequenceGenerator")
	    private Long id;
	    
	    @Column(name="modele")
		private String modele;
	    
	    @ManyToOne
	    @JoinColumn(name = "marque_id")
	    private Marque marque;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getModele() {
			return modele;
		}

		public void setModele(String modele) {
			this.modele = modele;
		}

		public Marque getMarque() {
			return marque;
		}

		public void setMarque(Marque marque) {
			this.marque = marque;
		}
	    
	    
	    

}
