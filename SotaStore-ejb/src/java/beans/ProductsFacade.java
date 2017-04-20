/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entity.Products;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Duyet
 */
@Stateless
public class ProductsFacade extends AbstractFacade<Products> implements ProductsFacadeLocal {
    @PersistenceContext(unitName = "SotaStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductsFacade() {
        super(Products.class);
    }
    
    @Override
    public List<Products> getProductsByName(String name) {
        Query q = em.createQuery("SELECT p FROM Products p WHERE p.name LIKE :name AND p.catalogId.parentId != :specialId AND p.status = 'Normal'");
        q.setParameter("name", "%"+name+"%");
        q.setParameter("specialId", 11);
        return q.getResultList();
    }

    @Override
    public List<Products> getProductsByCatalog(int id) {
        Query q = em.createQuery("SELECT p FROM Products p WHERE p.catalogId.id = :id AND p.status = 'Normal'");
        q.setParameter("id", id);
        return q.getResultList();
    }
    
    @Override
    public List<Products> getActiveProductList() {
        Query q = em.createNamedQuery("Products.findByStatus");
        q.setParameter("status", "Normal");
        return q.getResultList();
    }

    @Override
    public List<Products> getInactiveProductList() {
        Query q = em.createNamedQuery("Products.findByStatus");
        q.setParameter("status", "Inactive");
        return q.getResultList();
    }

    
    @Override
    public List<Products> getSpecialDishes() {
        Query q = em.createQuery("SELECT p FROM Products p WHERE p.status = 'Normal' AND p.catalogId.name = :day");
        Date now = new Date();
        SimpleDateFormat  simpleDateformat = new SimpleDateFormat("EEEE");
        q.setParameter("day", simpleDateformat.format(now));
        return q.getResultList();
    }

    @Override
    public boolean checkProductName(String productName) {
        Query q = em.createNamedQuery("Products.findByName");
        q.setParameter("name", productName);        
        return !q.getResultList().isEmpty();       
    }

    @Override
    public List<Products> checkProductNameNotIn(int productId) {
        Query q = em.createQuery("SELECT p FROM Products p WHERE p.id != :id");
        q.setParameter("id", productId);
        if(!q.getResultList().isEmpty()){
            return q.getResultList();
        }
        return null;
    }

    /* for customer home page */

    @Override
    public List<Products> getMostViewProductList() {
        Query q = em.createQuery("SELECT p FROM Products p WHERE p.status = 'Normal' AND p.catalogId.parentId != :specialId order by p.viewNo DESC");
        q.setParameter("specialId", 11);
        q.setMaxResults(6);        
        return q.getResultList();
    }

    @Override
    public List<Products> getMostBoughtProductList() {
        Query q = em.createQuery("SELECT p FROM Products p WHERE p.status = 'Normal' AND p.catalogId.parentId != :specialId order by p.bought DESC");
        q.setParameter("specialId", 11);
        q.setMaxResults(6);        
        return q.getResultList();
    }

    @Override
    public List<Products> getTodaySpecialLimitList() {
        Query q = em.createQuery("SELECT p FROM Products p WHERE p.status = 'Normal' AND p.catalogId.name = :day");
        Date now = new Date();
        SimpleDateFormat  simpleDateformat = new SimpleDateFormat("EEEE");
        q.setParameter("day", simpleDateformat.format(now));
        q.setMaxResults(6);
        return q.getResultList();
    }
    
}
