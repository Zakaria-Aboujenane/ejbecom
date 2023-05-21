package com.isga.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ComMenuKey  implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long commande_id;
	private Long menu_id;
	
	
	
	public Long getId_commande() {
		return commande_id;
	}
	public void setId_commande(Long id_commande) {
		this.commande_id = id_commande;
	}
	public Long getId_menu() {
		return menu_id;
	}
	public void setId_menu(Long id_menu) {
		this.menu_id = id_menu;
	}
	
	

	@Override
	public boolean equals(Object obj) {
		ComMenuKey other = (ComMenuKey) obj;
		return this.commande_id.equals(other.commande_id) 
				&& this.menu_id.equals(other.menu_id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int startRes = 2;
		int result = (int) (prime * startRes + this.commande_id);
		int result2 = (int) (prime * startRes + this.menu_id);
		int Gresult = prime * result*result2;
		return Gresult;
	}
	
	
	

}
