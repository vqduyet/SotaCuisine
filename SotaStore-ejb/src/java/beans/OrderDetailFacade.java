/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entity.OrderDetail;
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
public class OrderDetailFacade extends AbstractFacade<OrderDetail> implements OrderDetailFacadeLocal {
    @PersistenceContext(unitName = "SotaStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderDetailFacade() {
        super(OrderDetail.class);
    }
    
    @Override
    public List<OrderDetail> getOrderDetailByOrderId(int orderId) {        
        Query q = em.createNamedQuery("OrderDetail.findByOrderId");
        q.setParameter("orderId", orderId);
        return q.getResultList();
    }
    
}
