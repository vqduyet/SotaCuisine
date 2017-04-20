/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import beans.OrderDetailFacadeLocal;
import beans.OrdersFacadeLocal;
import beans.ProductsFacadeLocal;
import beans.ShippingFeeFacadeLocal;
import beans.UsersFacadeLocal;
import entity.OrderDetail;
import entity.Orders;
import entity.ShippingFee;
import entity.Users;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Duyet
 */
@ManagedBean
@SessionScoped
public class OrderMB {
    @EJB
    private ProductsFacadeLocal productsFacade;
    @EJB
    private UsersFacadeLocal usersFacade;
    @EJB
    private OrderDetailFacadeLocal orderDetailFacade;
    @EJB
    private OrdersFacadeLocal ordersFacade;
    @EJB
    
    
    private ShippingFeeFacadeLocal shippingFeeFacade;
    private Orders selectedOrder;
    private List<OrderDetail> selectedOrderDetail = new ArrayList<OrderDetail>();
    private int editDistrictId;
    private boolean renderButton = true;

    public boolean isRenderButton() {
        return renderButton;
    }

    public void setRenderButton(boolean renderButton) {
        this.renderButton = renderButton;
    }
    
    
    public List<OrderDetail> getSelectedOrderDetail() {
        return selectedOrderDetail;
    }

    public void setSelectedOrderDetail(List<OrderDetail> selectedOrderDetail) {
        this.selectedOrderDetail = selectedOrderDetail;
    }

    public int getEditDistrictId() {
        return editDistrictId;
    }

    public void setEditDistrictId(int editDistrictId) {
        this.editDistrictId = editDistrictId;
    }
    
    public Orders getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(Orders selectedOrder) {
        this.selectedOrder = selectedOrder;
    }
    
    
    private ShippingFee selectedShippingFee;    
    
    public ShippingFee getSelectedShippingFee() {
        return selectedShippingFee;
    }

    public void setSelectedShippingFee(ShippingFee selectedShippingFee) {
        this.selectedShippingFee = selectedShippingFee;
    }
    
    
    /**
     * Creates a new instance of OrderMB
     */
    public OrderMB() {
    }
    
    /* order management */
    public List<Orders> fetchAllOrderList(){
        return ordersFacade.findAll();
    }
    
    public String gotoOrderDetail(int id){        
        selectedOrder = ordersFacade.find(id);
        selectedOrderDetail = orderDetailFacade.getOrderDetailByOrderId(selectedOrder.getId());        
        editDistrictId = selectedOrder.getDistrict().getId();
        if(selectedOrder.getStatus().equals("Cancel") || selectedOrder.getStatus().equals("Delivery Compelete")){
            renderButton = false;
        }
        else{
            renderButton = true;
        }
        return "/admin/order/order-detail?faces-redirect=true";        
    }    
    
    public void proceedOrder(int id, String status){
        Orders proceedOrder = ordersFacade.find(id);        
        if(status.equals("Delivery Compelete")){
            if(proceedOrder.getUsersId() != null){
                // cap nhat diem cong
                int scoreAdded = (proceedOrder.getAmount().divide(new BigDecimal("100000"))).intValue();
                Users memberBuyer = usersFacade.find(proceedOrder.getUsersId().getId());
                memberBuyer.setScore(memberBuyer.getScore()+scoreAdded);
                usersFacade.edit(memberBuyer);
            }
            //cap nhat so luong mua cua tung mat hang
            List<OrderDetail> proceedOrderDetail = orderDetailFacade.getOrderDetailByOrderId(id);
            for(OrderDetail item : proceedOrderDetail){
                item.getProducts().setBought(item.getProducts().getBought() + item.getQuantity());
                productsFacade.edit(item.getProducts());
            }
        }
        //cap nhat order status
        proceedOrder.setStatus(status);        
        ordersFacade.edit(proceedOrder);
        
    }
    
    //edit order
    public void updateOrder(){
        //kiem tra district id co doi khong
        if(editDistrictId != selectedOrder.getDistrict().getId()){
            selectedOrder.setDistrict(shippingFeeFacade.find(editDistrictId));
            selectedOrder.setShippingFee(shippingFeeFacade.find(editDistrictId).getFee());            
        }
        // neu status = delivery complete
        if(selectedOrder.getStatus().equals("Delivery Compelete")){
            if(selectedOrder.getUsersId() != null){
                // cap nhat diem cong
                    int scoreAdded = (selectedOrder.getAmount().divide(new BigDecimal("100000"))).intValue();
                    Users memberBuyer = usersFacade.find(selectedOrder.getUsersId().getId());
                    memberBuyer.setScore(memberBuyer.getScore()+scoreAdded);
                    usersFacade.edit(memberBuyer);
            }
            //cap nhat so luong mua cua tung mat hang                
            for(OrderDetail item : selectedOrderDetail){
                item.getProducts().setBought(item.getProducts().getBought() + item.getQuantity());
                productsFacade.edit(item.getProducts());
            }
        }
        ordersFacade.edit(selectedOrder);
        if(selectedOrder.getStatus().equals("Cancel") || selectedOrder.getStatus().equals("Delivery Compelete")){
            renderButton = false;
        }
        else{
            renderButton = true;
        }
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Update order successfully."));
    }
    
    
    /* shipping fee management */
    public List<ShippingFee> fetchAllShippingList(){
        return shippingFeeFacade.findAll();
    }
    
    public String gotoEditFee(int id){
        selectedShippingFee = shippingFeeFacade.find(id);
        return "/admin/order/shipping-fee-edit?faces-redirect=true";
    }
    
    public void updateFee(){
        shippingFeeFacade.edit(selectedShippingFee);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Update shipping fee successfully."));
    }
    
}
