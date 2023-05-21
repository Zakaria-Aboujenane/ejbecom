package com.isga.entities;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity

public class Commande implements Serializable {

	   
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_commande;
	private String titre;
	private Date date;
	@Column(name="fraisLivraison", columnDefinition="Decimal(10,2) default '25.00'")
	private double fraisLivraison;
	private boolean valide;
	private boolean isCurrent;
	private boolean livred;
	
	@ManyToOne
	User user;
	
	@OneToMany(mappedBy = "commande")
	List<LigneCommande> ligneCommandes;
	private static final long serialVersionUID = 1L;

	public Commande() {
		super();
	}   
	public Long getId_commande() {
		return this.id_commande;
	}

	public void setId_commande(Long id_commande) {
		this.id_commande = id_commande;
	}   
	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}   
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isValide() {
		return valide;
	}
	public void setValide(boolean valide) {
		this.valide = valide;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<LigneCommande> getLigneCommandes() {
		return ligneCommandes;
	}
	public void setLigneCommandes(List<LigneCommande> ligneCommandes) {
		this.ligneCommandes = ligneCommandes;
	}
	public boolean isCurrent() {
		return isCurrent;
	}
	public void setCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}
	public boolean isLivred() {
		return livred;
	}
	public void setLivred(boolean livred) {
		this.livred = livred;
	}
	public double getFraisLivraison() {
		return fraisLivraison;
	}
	public void setFraisLivraison(double fraisLivraison) {
		this.fraisLivraison = fraisLivraison;
	}
	
	
	
	
   
}
