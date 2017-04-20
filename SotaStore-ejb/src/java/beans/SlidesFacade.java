/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entity.Slides;
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
public class SlidesFacade extends AbstractFacade<Slides> implements SlidesFacadeLocal {
    @PersistenceContext(unitName = "SotaStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SlidesFacade() {
        super(Slides.class);
    }

    @Override
    public List<Slides> getSortedSlideList() {
        Query q = em.createQuery("SELECT s FROM Slides s ORDER BY s.sortOrder ASC");
        return q.getResultList();
    }   
    
    
}
