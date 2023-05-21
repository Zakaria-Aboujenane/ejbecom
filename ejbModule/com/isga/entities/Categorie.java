package com.isga.entities;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: Categorie
 *
 */
@Entity

public class Categorie implements Serializable {

	   
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_categorie;
	private String name;
	@OneToMany(mappedBy = "categorie")
	private Collection<Menu> menus;
	
	private static final long serialVersionUID = 1L;

	public Categorie() {
		super();
	}   
	
	public Categorie(Long id_categorie, String name) {
		super();
		this.id_categorie = id_categorie;
		this.name = name;
	}

	public Long getId_categorie() {
		return this.id_categorie;
	}

	public void setId_categorie(Long id_categorie) {
		this.id_categorie = id_categorie;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Collection<Menu> getMenus() {
		return menus;
	}
	public void setMenus(Collection<Menu> menus) {
		this.menus = menus;
	}
	
   
}
