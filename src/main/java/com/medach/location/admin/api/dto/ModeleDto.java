package com.medach.location.admin.api.dto;

import com.medach.location.admin.dao.entity.Marque;
import com.medach.location.admin.dao.entity.Modele;

public class ModeleDto {
	
	private String modele;
	
	private Long marque;
	
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public Long getMarque() {
		return marque;
	}
	public void setMarque(Long marque) {
		this.marque = marque;
	}
	public String getModele() {
		return modele;
	}
	
	public void setModele(String modele) {
		this.modele = modele;
	}
	
	public Modele dtoToModele() {
		Modele m = new Modele();
		m.setId(null);
		Marque marque = new Marque();
		marque.setId(this.marque);
		m.setMarque(marque);
		m.setModele(this.modele);
		return m;
	}
	public Modele dtoToModeleUpdate() {
		Modele m = new Modele();
		m.setId(id);
		Marque marque = new Marque();
		marque.setId(this.marque);
		m.setMarque(marque);
		m.setModele(this.modele);
		return m;
	}

}
