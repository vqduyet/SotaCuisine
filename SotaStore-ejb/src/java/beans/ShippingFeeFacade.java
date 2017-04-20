/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entity.ShippingFee;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Duyet
 */
@Stateless
public class ShippingFeeFacade extends AbstractFacade<ShippingFee> implements ShippingFeeFacadeLocal {
    @PersistenceContext(unitName = "SotaStore-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ShippingFeeFacade() {
        super(ShippingFee.class);
    }
    
}
