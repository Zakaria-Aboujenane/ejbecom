package com.isga.sessions;

import java.util.List;

import javax.ejb.Local;

import com.isga.entities.Role;
import com.isga.entities.User;

@Local
public interface ISecurityBeanLocal {
	public User authUser(String email,String password);
	public List<Role> getAllRoles();
	public List<Role> getUserRoles(Long idUser);
	public boolean hasRole(Long userID,String roleName);
	public boolean hasRole(String username,String roleName);
	public void saveRole(String roleName);
	public User saveUser(User user);
	public void addRoleToUser(Long idUser,String roleName);
	public User getUserById(Long id_Client);
	public Role getRoleByRolename(String roleName);
	public User getUserByUserName(String username);
	
}
