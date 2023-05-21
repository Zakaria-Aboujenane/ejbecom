package com.isga.sessions;

import java.util.List;

import javax.ejb.Local;
import com.isga.entities.Categorie;
import com.isga.entities.Commande;
import com.isga.entities.LigneCommande;
import com.isga.entities.Menu;

@Local
public interface IEcomSessionBeanLocal {
//	gestion des commandes :
	public List<Commande> listerCommandes();
	public Commande saveCommande(Commande commande);
	public Commande getCommandebyId(Long idCommande);
	public double getTotalOfCommande(Long idCommande);
	public void changerEtatCommande(Long id_commande);
	public void terminerCommande(Long idCommande);
	public List<LigneCommande> getLignesForCommande(Long idCommande);
//	gestion des menus
	public List<Menu> listerMenus();
	public Menu saveMenu(Menu menu);
	public Menu getMenuById(Long idMenu);
	public Menu updateMenu(Long idMenu,Menu m);
	public void deleteMenu(Long idMenu);
	public List<Menu> searchMenus(String keyword);
//	menus et commandes
	public void addMenuToCommande(Long idMenu,Long idCommande,Long qte);
	public void deleteMenuFromCommande(Long idMenu,Long idCommande);
//	gestion des categories
	public List<Categorie> listerCategories();
	public Categorie saveCategory(Categorie cat);
	public Categorie getCategorieById(Long idCategorie);
//	menus et categories
	public List<Menu> getMenusByCategorie(Long idCategorie);
	
//	livraison
	public double getLivraisonDefault();
	
// commandes et users
	public Commande getUserCurrentCommande(String username);
	public List<Commande> getUserCommandes(Long userID);
}
