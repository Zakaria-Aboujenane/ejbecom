package com.isga.entities;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class User implements Serializable {

	   
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_client;
	private String name;
	private String email;
	private String password;
	
	@OneToMany(mappedBy = "user")
	List<Commande> commandes;
	
	@ManyToMany(fetch = FetchType.EAGER)
	List<Role> roles = new ArrayList<Role>();
	
	
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}   
	public Long getId_client() {
		return this.id_client;
	}
	

	public User(Long id_client, String name, String email, String password) {
		super();
		this.id_client = id_client;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	public void setId_client(Long id_client) {
		this.id_client = id_client;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Commande> getCommandes() {
		return commandes;
	}
	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	
	
   
}
