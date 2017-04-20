/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entity.Admin;
import entity.AdminGroup;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Lenovo
 */
@Stateless
public class AdminFacade extends AbstractFacade<Admin> implements AdminFacadeLocal {

    @PersistenceContext(unitName = "SotaStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdminFacade() {
        super(Admin.class);
    }

    @Override
    public boolean login(String username, String password) {
        Query q = em.createQuery("SELECT a FROM Admin a WHERE a.userName = :userName and a.userPass = :password and a.status = :status");
        q.setParameter("userName", username);
        q.setParameter("password", password);
        q.setParameter("status", "Normal");
        return !q.getResultList().isEmpty();
    }

    @Override
    public Admin findByName(String username) {
        try {
            Query q = em.createNamedQuery("Admin.findByUserName");
            q.setParameter("userName", username);
            return (Admin) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Admin> findByGroup(AdminGroup group) {
        try {
            Query q = em.createQuery("SELECT a FROM Admin a WHERE a.admingroupid = :admingroupid");
            q.setParameter("admingroupid", group);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public boolean isUsernameAvailable(String username) {
        Query q = em.createNamedQuery("Admin.findByUserName");
        q.setParameter("userName", username);
        return !q.getResultList().isEmpty();
    }

    @Override
    public boolean isGroupHasMember(AdminGroup group) {

        Query q = em.createQuery("SELECT a FROM Admin a WHERE a.admingroupid = :admingroupid and a.status = :status");
        q.setParameter("admingroupid", group);
        q.setParameter("status", "Normal");
        return !q.getResultList().isEmpty();

    }

}
