/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entity.Orders;
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
public class OrdersFacade extends AbstractFacade<Orders> implements OrdersFacadeLocal {
    @PersistenceContext(unitName = "SotaStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdersFacade() {
        super(Orders.class);
    }

    @Override
    public List<Orders> getAllOrders() {
        Query q = em.createNamedQuery("Orders.findAll");
        return q.getResultList();
    }

    @Override
    public List<Orders> getOrderListByStatus(String status) {
        Query q = em.createNamedQuery("Orders.findByStatus");
        q.setParameter("status", status);
        return q.getResultList();
    }
    
    @Override
    public List<Orders> showOrderByUser(int customerId) {
        Query q = em.createQuery("SELECT o FROM Orders o WHERE o.usersId.id = :id");
        q.setParameter("id", customerId);
        return q.getResultList();
    }
    
    
    
}
