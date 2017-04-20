/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entity.ShippingFee;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Duyet
 */
@Local
public interface ShippingFeeFacadeLocal {

    void create(ShippingFee shippingFee);

    void edit(ShippingFee shippingFee);

    void remove(ShippingFee shippingFee);

    ShippingFee find(Object id);

    List<ShippingFee> findAll();

    List<ShippingFee> findRange(int[] range);

    int count();
    
}
