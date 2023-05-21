package com.isga.sessions;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.isga.entities.Role;
import com.isga.entities.User;
import com.isga.utils.PasswordEncoder;

@Stateless
@LocalBean
public class SecurityBean implements ISecurityBeanLocal{
	
	@PersistenceContext(name = "aaa",unitName = "isga-ejb")
	private EntityManager em;
   
    public SecurityBean() {
        
    }

	@Override
	public User authUser(String email, String password) {
		Query query = em.createQuery("select u from User u where u.email= :em and u.password= :pass");
		query.setParameter("em",email);
		query.setParameter("pass",PasswordEncoder.getMd5(password));
		User u = null;
		try {
			u =  (User) query.getSingleResult();
		}catch (Exception e) {
			new RuntimeException("nom ou mot de passe incorect");
		}
		return u;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAllRoles() {
		Query query = em.createQuery("select r from Role r");
		return query.getResultList();
	}

	@Override
	public void saveRole(String roleName) {
		Role role = new Role();
		role.setRolename(roleName);
		em.persist(role);
	}

	@Override
	public void addRoleToUser(Long idUser, String roleName) {
		User u = getUserById(idUser);
		Role r = getRoleByRolename(roleName);
		u.getRoles().add(r);
		
	}

	@Override
	public User getUserById(Long id_Client) {
		User u = em.find(User.class,id_Client);
		if(u == null) new RuntimeException("utilisateur introuvable");
		return u;
	}

	@Override
	public Role getRoleByRolename(String roleName) {
		Role r = em.find(Role.class,roleName);
		if(r == null) new RuntimeException("role introuvable");
		return r;
	}

	@Override
	public User saveUser(User user) {
		user.setPassword(PasswordEncoder.getMd5(user.getPassword()));
		em.persist(user);
		return user;
		
	}

	@Override
	public List<Role> getUserRoles(Long idUser) {
		return (List<Role>) getUserById(idUser).getRoles();
		
	}

	@Override
	public boolean hasRole(Long userID, String roleName) {
		List<Role> rs = getUserById(userID)
				.getRoles()
				.stream()
				.filter(p->p.getRolename().equals(roleName))
				.collect(Collectors.toList());
		if(rs.size()>0) return true;
		else return false;
	}

	@Override
	public User getUserByUserName(String username) {
		Query query = em.createQuery("select u from User u where u.name=:name");
		query.setParameter("name", username);
		System.out.println("user : "+username);
		User u = null;
		try {
			u =  (User) query.getSingleResult();
		}catch (Exception e) {
			System.out.println("USER NOT FOUND !");
		}
		return u;
	}

	@Override
	public boolean hasRole(String username, String roleName) {
		
		List<Role> rs = getUserByUserName(username)
				.getRoles()
				.stream()
				.filter(p->p.getRolename().equals(roleName))
				.collect(Collectors.toList());
		if(rs.size()>0) return true;
		else return false;
	}


}
