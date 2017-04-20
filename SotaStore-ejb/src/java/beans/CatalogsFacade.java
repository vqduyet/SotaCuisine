/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entity.Catalogs;
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
public class CatalogsFacade extends AbstractFacade<Catalogs> implements CatalogsFacadeLocal {
    @PersistenceContext(unitName = "SotaStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CatalogsFacade() {
        super(Catalogs.class);
    }

    /* Duyet */
    @Override
    public List<Catalogs> getCatalogByParentId(int parentId) {
        Query q = em.createNamedQuery("Catalogs.findByParentId");
        q.setParameter("parentId", parentId);
        return q.getResultList();
    }

    @Override
    public List<Catalogs> getMenuCatalogs() {
        Query q = em.createQuery("SELECT c FROM Catalogs c WHERE c.id != :id AND c.parentId = 0 AND c.status = 'Normal'");
        q.setParameter("id", 11);
        return q.getResultList();
    }
    
    /* Phuong */
    @Override
    public List<Catalogs> getParentCatalogList() {
        Query q = em.createNamedQuery("Catalogs.findByParentId");
        q.setParameter("parentId", 0);        
        return q.getResultList();
    } 
    
  
    @Override
    public List<Catalogs> getActiveCatalogsList() {
        Query q = em.createNamedQuery("Catalogs.findByStatus");
        q.setParameter("status", "Normal");  
        return q.getResultList(); 
    }

    @Override
    public List<Catalogs> getInactiveCatalogsList() {
        Query q = em.createNamedQuery("Catalogs.findByStatus"); 
        q.setParameter("status", "Inactive");  
        return q.getResultList(); 
    }   

    @Override
    public List<Catalogs> getSubCatalogList() {
        Query q = em.createNamedQuery("Catalogs.findBySubParentId");
        q.setParameter("parentId", 0);        
        return q.getResultList();
    }

    @Override
    public boolean checkCatalogName(String catalogName) {
        Query q = em.createNamedQuery("Catalogs.findByName");
        q.setParameter("name", catalogName);        
        return !q.getResultList().isEmpty();
    }

    @Override
    public List<Catalogs> checkCatalogNameNotIn(int catalogId) {
        Query q = em.createQuery("SELECT c FROM Catalogs c WHERE c.id != :id");
        q.setParameter("id", catalogId);
        if(!q.getResultList().isEmpty()){
            return q.getResultList();
        }
        return null;
    }
    
    
    
}
