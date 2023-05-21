package com.isga.entities;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Collection;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity implementation class for Entity: Menu
 *
 */
@Entity
public class Menu implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_menu;
	private String name;
	  @Lob
	  @Column( length = 100000 )
	private String description;
	private double prix;
	private Long qteMax;
	private String image;
	

	@OneToMany(mappedBy = "menu")
	@Transient
	Collection<LigneCommande> ligneCommandes;

	@ManyToOne
	private Categorie categorie;
	
	private static final long serialVersionUID = 1L;
	
	public Menu() {
		super();
	}
	
	

	public Menu(Long id_menu, String name, String description, double prix, Long qteMax, String image) {
		super();
		this.id_menu = id_menu;
		this.name = name;
		this.description = description;
		this.prix = prix;
		this.qteMax = qteMax;
		this.image = image;
	}



	public Long getId_menu() {
		return this.id_menu;
	}

	public void setId_menu(Long id_menu) {
		this.id_menu = id_menu;
	}   
	public double getPrix() {
		return this.prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}   
	public Long getQteMax() {
		return this.qteMax;
	}

	public void setQteMax(Long qteMax) {
		this.qteMax = qteMax;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	public Collection<LigneCommande> getLigneCommandes() {
		return ligneCommandes;
	}
	public void setLigneCommandes(Collection<LigneCommande> ligneCommandes) {
		this.ligneCommandes = ligneCommandes;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
   
}
