package com.isga.sessions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.management.RuntimeErrorException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.isga.entities.Categorie;
import com.isga.entities.Commande;
import com.isga.entities.LigneCommande;
import com.isga.entities.Menu;
import com.isga.entities.User;
import com.isga.utils.PasswordEncoder;


@Stateless
public class EcomSessionBean implements IEcomSessionBeanLocal {

	@PersistenceContext(name = "aaa",unitName = "isga-ejb")
	// pas la peine d'avoir unitName car on a 1 seule unite de persistance
	private EntityManager em;
	@EJB
	private ISecurityBeanLocal security;
    public EcomSessionBean() {
    }
	@Override
	public Menu saveMenu(Menu menu) {
		em.persist(menu);
		return menu;
	}
	@Override
	public Categorie saveCategory(Categorie cat) {
		em.persist(cat);
		return cat;
		
	}
	@Override
	public Commande saveCommande(Commande commande) {
		em.persist(commande);
		return commande;
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Commande> listerCommandes() {
		Query query = em.createQuery("select c from Commande c");
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> listerMenus() {
		Query query = em.createQuery("select m from Menu m");
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Categorie> listerCategories() {
		Query query = em.createQuery("select c from Categorie c");
		return query.getResultList();
	}
	@Override
	public void changerEtatCommande(Long id_commande) {
		Commande c = getCommandebyId(id_commande);
		c.setValide(!c.isValide());
		
	}
	@Override
	@Transactional
	public void addMenuToCommande(Long idMenu, Long idCommande,Long qte) {
		LigneCommande ligneCommande = new LigneCommande();
		ligneCommande.setCommande(getCommandebyId(idCommande));
		Menu m = getMenuById(idMenu);
		if(qte<=m.getQteMax()) {
			ligneCommande.setMenu(m);
			LigneCommande exists =  getLignesForCommande(idCommande)
									.stream()
									.filter(l->l.getMenu().getId_menu().equals(idMenu))
									.findFirst()
									.orElse(null);
			m.setQteMax(m.getQteMax()-qte);
			if(exists == null) {
				ligneCommande.setQantite(qte);
				em.persist(ligneCommande);
			}else {
				exists.getQantite();
				exists.setQantite(exists.getQantite()+qte);
			}
		}else {
			throw new RuntimeException("la quantite voulue n'est pas disponible pour le moment !");
		}
		
	}
	@Override
	public Commande getCommandebyId(Long idCommande) {
		Commande com = em.find(Commande.class, idCommande);
		if(com==null) new RuntimeException("commande introuvable");
		return com;
	}
	@Override
	public Categorie getCategorieById(Long idCategorie) {
		Categorie cat = em.find(Categorie.class, idCategorie);
		if(cat==null) new RuntimeException("categorie introuvable");
		return cat;
	}
	@Override
	public Menu getMenuById(Long idMenu) {
		Menu menu = em.find(Menu.class, idMenu);
		if(menu==null) new RuntimeException("menu introuvable");
		return menu;
	}
	@Override
	public List<Commande> getUserCommandes(Long userID) {
		User u = em.find(User.class,userID);
		if(u == null) new RuntimeException("utilisateur introuvable");
		List<Commande> cs = new ArrayList<Commande>();
		for(Commande c:u.getCommandes()) {
			cs.add(c);
		}
		return cs;
	}
	@Override
	public List<LigneCommande> getLignesForCommande(Long idCommande) {
		Commande c= getCommandebyId(idCommande);
		List<LigneCommande> ls = new ArrayList<LigneCommande>();
		for (LigneCommande ligneCommande : c.getLigneCommandes()) {
			ls.add(ligneCommande);
		}
		return ls;
	}
	@Override
	public double getTotalOfCommande(Long idCommande) {
		Commande c = getCommandebyId(idCommande);
		return c.getLigneCommandes()
				.stream()
				.mapToDouble(
						l->l.getMenu().getPrix()*l.getQantite()+l.getCommande().getFraisLivraison())
				.sum();
	}
	@Override
	public void terminerCommande(Long idCommande) {
		Commande c = getCommandebyId(idCommande);
		if(c.getLigneCommandes().size() ==0) {
			new RuntimeException("commande vide !");
		}else {
			c.setCurrent(false);
		}
		
		
	}
	@Override
	@Transactional
	public Menu updateMenu(Long idMenu, Menu m) {
		Menu m1 = getMenuById(idMenu);
		m1.setCategorie(m.getCategorie());
		m1.setName(m.getName());
		m1.setImage(m.getImage());
		m1.setDescription(m.getDescription());
		m1.setPrix(m.getPrix());
		m1.setLigneCommandes(m.getLigneCommandes());
		return m1;
		
	}
	@Override
	public void deleteMenu(Long idMenu) {
		Menu m = getMenuById(idMenu);
		em.remove(m);
	}
	
	@Override
	public void deleteMenuFromCommande(Long idMenu, Long idCommande) {
		LigneCommande exists =  getLignesForCommande(idCommande)
								.stream()
								.filter(l->l.getMenu().getId_menu().equals(idMenu))
								.findFirst()
								.orElse(null);
		if(exists != null) {
			em.remove(exists);
		}
		
	}
	@Override
	public List<Menu> getMenusByCategorie(Long idCategorie) {
		Categorie c = getCategorieById(idCategorie);
		List<Menu> menus = (List<Menu>) c.getMenus();
		return menus;
	}
	@Override
	public double getLivraisonDefault() {
		// on peut faire une livraison selon la location ..etc.
		return 25.00;
	}
	@Override
	public List<Menu> searchMenus(String keyword) {
		Query query = em.createQuery("from Menu as m where m.name like :searchField");
		query.setParameter("searchField","%"+keyword+"%");
		return query.getResultList();
	}
	@Override
	@Transactional
	public Commande getUserCurrentCommande(String username) {
	 	List<Commande> commandes = this.getUserCommandes(security.getUserByUserName(username).getId_client());
    	Commande comm = commandes.stream().filter(p->p.isCurrent()).findFirst().orElse(null);
    	if(comm==null) {
    		comm = new Commande();
			comm.setTitre("Commande");
	    	comm.setFraisLivraison(25.00);
			comm.setUser(security.getUserByUserName(username));
			comm.setDate(new Date());
			comm.setValide(false);
			comm.setCurrent(true);
			comm = saveCommande(comm);
    	}
    	return comm;
	}


}
