/**
 * 
 */
package com.dashda.data.repositories;

import java.util.List;

import com.dashda.data.entities.User;

/**
 * @author mhanafy
 *
 */
public interface UserDao {

    public void addUser(User user);
    
    public void editUser(User user, int userId);
 
    public void deleteUser(int userId);
 
    public User find(int userId);
 
    public List <User> findAll();
    
    public User findUserByUsername(String username);
}
