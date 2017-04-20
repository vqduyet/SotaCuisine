/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import entity.Orders;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Duyet
 */
@Local
public interface OrdersFacadeLocal {

    void create(Orders orders);

    void edit(Orders orders);

    void remove(Orders orders);

    Orders find(Object id);

    List<Orders> findAll();

    List<Orders> findRange(int[] range);

    int count();

    List<Orders> getAllOrders();

    List<Orders> getOrderListByStatus(String status);
	
	List<Orders> showOrderByUser(int customerId);
    
}
