/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import beans.OrdersFacadeLocal;
import entity.Orders;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Duyet
 */
@ManagedBean
@ViewScoped
public class ViewOrderMB {
    @EJB
    private OrdersFacadeLocal ordersFacade;

    /**
     * Creates a new instance of ViewOrderMB
     */
    public ViewOrderMB() {
    }
    
    /* order management */
    public List<Orders> fetchAllOrderList(){
        return ordersFacade.findAll();
    }
    public List<Orders> fetchWaitingOrderList(){
        return ordersFacade.getOrderListByStatus("Waiting");
    }
    public List<Orders> fetchProgressOrderList(){
        return ordersFacade.getOrderListByStatus("In progess");
    }
    public List<Orders> fetchDeliveryOrderList(){
        return ordersFacade.getOrderListByStatus("Delivery");
    }
    public List<Orders> fetchCompleteOrderList(){
        return ordersFacade.getOrderListByStatus("Delivery Compelete");        
    }
    public List<Orders> fetchCancelOrderList(){
        return ordersFacade.getOrderListByStatus("Cancel");
    }
    
}
