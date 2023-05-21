package com.isga.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class LigneCommande implements Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ComMenuKey id = new ComMenuKey();
	
	private Long qantite;
	
	@ManyToOne
	@MapsId("commande_id")
	@JoinColumn(name = "commande_id")
	private Commande commande;
	
	@ManyToOne
	@MapsId("menu_id")
	@JoinColumn(name = "menu_id")
	private Menu menu;
	
	


	
	
	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public ComMenuKey getId() {
		return id;
	}

	public void setId(ComMenuKey id) {
		this.id = id;
	}

	public Long getQantite() {
		return qantite;
	}

	public void setQantite(Long qantite) {
		this.qantite = qantite;
	}
	
	

	
}
