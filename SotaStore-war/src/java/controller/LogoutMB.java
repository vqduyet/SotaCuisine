/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import filter.Util;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import model.ShippingInfo;

/**
 *
 * @author Duyet
 */
@ManagedBean
@SessionScoped
public class LogoutMB {   
    
    //inject cart managed bean
    @ManagedProperty(value="#{cartMB}")
    private CartMB cartMB;

    public CartMB getCartMB() {
        return cartMB;
    }

    public void setCartMB(CartMB cartMB) {
        this.cartMB = cartMB;
    }
    
    
    
    /**
     * Creates a new instance of LogoutMB
     */
    public LogoutMB() {
    }
    
    public String doLogout(){
        HttpSession session = Util.getSession();
        session.setAttribute("loginUser", null);
        cartMB.setShippingInfo(new ShippingInfo());
        cartMB.setScoreAdded(0);
        cartMB.setScoreApplied(0);
        cartMB.setShippingCheck(false);
        cartMB.getMemberMB().setLoginUsers(null);
        return "/index?faces-redirect=true";
    }
    
}
