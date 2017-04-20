/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entity.AdminGroup;
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
public class AdminGroupFacade extends AbstractFacade<AdminGroup> implements AdminGroupFacadeLocal {
    @PersistenceContext(unitName = "SotaStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdminGroupFacade() {
        super(AdminGroup.class);
    }

    @Override
    public List<AdminGroup> findNormal() {
        Query q = em.createNamedQuery("AdminGroup.findByStatus");
        q.setParameter("status", "Normal");
        return q.getResultList();
    }
    
}
