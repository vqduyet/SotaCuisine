/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entity.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Duyet
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> implements UsersFacadeLocal {
    @PersistenceContext(unitName = "SotaStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
    @Override
    public boolean verifyUser(String username, String password) {
        Query q = em.createQuery("SELECT u FROM Users u WHERE u.userName = :userName AND u.userPass = :userPass AND u.status = :status");
        q.setParameter("userName", username);
        q.setParameter("userPass", password);
        q.setParameter("status", "Normal");
        return !q.getResultList().isEmpty();
    }

    @Override
    public Users getUserByUsername(String username) {
        try {
        Query q = em.createNamedQuery("Users.findByUserName");
        q.setParameter("userName", username);
        return (Users) q.getSingleResult();
        } catch(Exception e){
            return null;
        }
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        Query q = em.createNamedQuery("Users.findByUserName");
        q.setParameter("userName", username);
        return !q.getResultList().isEmpty();
    }
    
    
    
}
