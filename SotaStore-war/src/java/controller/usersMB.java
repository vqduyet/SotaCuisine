/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.OrderDetailFacadeLocal;
import beans.OrdersFacadeLocal;
import beans.ShippingFeeFacadeLocal;
import beans.UsersFacadeLocal;
import entity.OrderDetail;
import entity.Orders;
import entity.ShippingFee;
import entity.Users;
import filter.Util;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tqkha
 */
@ManagedBean
@SessionScoped
public class usersMB {
    @EJB
    private OrderDetailFacadeLocal orderDetailFacade;
    @EJB
    private OrdersFacadeLocal ordersFacade;

    @EJB
    private UsersFacadeLocal usersFacade;
    @EJB
    private ShippingFeeFacadeLocal shippingFeeFacade;
    //Register
    private int districtId;
    private String input_confirm;
    private Users registeringGuest = new Users();
    private String rdDistrict = "hochiminh";
    //User
    private Users loginUsers = null;

    //bien tam de lay gia tri cua district cua login member
    private Integer loginDistrictId;

    private String path = "";

    private Orders selectedUserOrder;
    private List<OrderDetail> selectedUserOrderDetailList;

    public Orders getSelectedUserOrder() {
        return selectedUserOrder;
    }

    public void setSelectedUserOrder(Orders selectedUserOrder) {
        this.selectedUserOrder = selectedUserOrder;
    }

    public List<OrderDetail> getSelectedUserOrderDetailList() {
        return selectedUserOrderDetailList;
    }

    public void setSelectedUserOrderDetailList(List<OrderDetail> selectedUserOrderDetailList) {
        this.selectedUserOrderDetailList = selectedUserOrderDetailList;
    }
    
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getLoginDistrictId() {
        return loginDistrictId;
    }

    public void setLoginDistrictId(Integer loginDistrictId) {
        this.loginDistrictId = loginDistrictId;
    }

    public Users getLoginUsers() {
        return loginUsers;
    }

    public void setLoginUsers(Users loginUsers) {
        this.loginUsers = loginUsers;
    }
    //Password
    private String oldPassword = "", newPassword = "", confirmPassword = "";

    public String getOldPassword() {
        return oldPassword;
    }

    public String getInput_confirm() {
        return input_confirm;
    }

    public void setInput_confirm(String input_confirm) {
        this.input_confirm = input_confirm;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    //Login
    private String username, password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //getter setter Register  
    public String getRdDistrict() {
        return rdDistrict;
    }

    public void setRdDistrict(String rdDistrict) {
        this.rdDistrict = rdDistrict;
    }

    public Users getRegisteringGuest() {
        return registeringGuest;
    }

    public void setRegisteringGuest(Users registeringGuest) {
        this.registeringGuest = registeringGuest;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    /**
     * Creates a new instance of usersMB
     */
    public usersMB() {
    }

    public String gotoLogin() {
        //return to current page
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        path = viewId + "?faces-redirect=true";
        return "/login?faces-redirect=true";
    }

    public String checkLogin() {
        if (usersFacade.verifyUser(username, password)) {
            //get member
            HttpSession session = Util.getSession();
            session.setAttribute("loginUser", usersFacade.getUserByUsername(username));
            loginUsers = usersFacade.getUserByUsername(username);
            if (loginUsers.getDistrict() == null) {
                loginDistrictId = null;
            } else {
                loginDistrictId = loginUsers.getDistrict().getId();
            }
            //return to current page
            return path;
        } else {
            FacesContext.getCurrentInstance().addMessage("formLogin", new FacesMessage("Invalid username, password or you has been banned"));
            //remain at login page
            return null;
        }
    }

    public List<ShippingFee> districtList() {
        return shippingFeeFacade.findAll();
    }

    public String addUser() {
        if (usersFacade.isUsernameAvailable(registeringGuest.getUserName())) {
            FacesContext.getCurrentInstance().addMessage("txtUserName", new FacesMessage("Username is existed, please input another Username"));
            return null;
        }
        if (!registeringGuest.getUserName().matches("^[a-z0-9]{4,20}$")) {
            FacesContext.getCurrentInstance().addMessage("txtUserName", new FacesMessage("Username contain between 4 and 20 characters and digits"));
            return null;
        }
        if (registeringGuest.getUserPass().length() <6 || registeringGuest.getUserPass().length() > 20) {
            FacesContext.getCurrentInstance().addMessage("txtPassword", new FacesMessage("Password must between 6 and 20 characters"));
            return null;
        }
        if (!input_confirm.equals(registeringGuest.getUserPass())) {
            FacesContext.getCurrentInstance().addMessage("txtConfirm", new FacesMessage("Confirm Password and Password must be same"));
            return null;
        }
        if (!(registeringGuest.getFullName().trim().length() >= 4 && registeringGuest.getFullName().trim().length() <= 50)) {
            FacesContext.getCurrentInstance().addMessage("txtFullName", new FacesMessage("Fullname between 4 and 50 characters"));
            return null;
        }
        if (registeringGuest.getAddress().trim().length() <= 1 || registeringGuest.getAddress().trim().length() > 255) {
            FacesContext.getCurrentInstance().addMessage("txtFullName", new FacesMessage("Address contain between 1 and 255 characters"));
            return null;
        }
        if (!registeringGuest.getEmail().matches("[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]")) {
            FacesContext.getCurrentInstance().addMessage("txtEmail", new FacesMessage("Please input email type xxx@xxx.xxx"));
            return null;
        }
        if (!registeringGuest.getPhone().matches("[0-9]{10,11}")) {
            FacesContext.getCurrentInstance().addMessage("txtPhone", new FacesMessage("Phone must be number between 10 and 11 digits"));
            return null;
        }
        if (rdDistrict.equals("hochiminh")) {
            //System.out.println("This is Distric value: " + districtId);
            /*
            if (districtId != 0) {
                FacesContext.getCurrentInstance().addMessage("cbDistrict", new FacesMessage("Please choose district"));
                return null;
            } else {
                registeringGuest.setDistrict(shippingFeeFacade.find(districtId));
            }
            */
            registeringGuest.setDistrict(shippingFeeFacade.find(districtId));
        } else {
            registeringGuest.setDistrict(null);
        }
        //System.out.println("this is district id: " + districtId);
        //System.out.println("this is radDistrict: " + rdDistrict);

        registeringGuest.setFullName(registeringGuest.getFullName().trim());
        registeringGuest.setAddress(registeringGuest.getAddress().trim());
        registeringGuest.setStatus("Normal");
        registeringGuest.setCreated(new Date());
        usersFacade.create(registeringGuest);
        
        //set lai cac bien register
        registeringGuest = new Users();
        rdDistrict = "hochiminh";
        districtId = 0;
        //chuyen trang
        return "/login?faces-redirect=true";
    }

    public String changeProfile() {
        if (!(loginUsers.getFullName().trim().length() >= 4 && loginUsers.getFullName().trim().length() <= 50)) {
            FacesContext.getCurrentInstance().addMessage("txtFullName", new FacesMessage("Fullname between 4 and 50 characters"));
            return null;
        }
        if (!(loginUsers.getEmail().matches("[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]"))) {
            FacesContext.getCurrentInstance().addMessage("txtEmail", new FacesMessage("Please input email type xxx@xxx.xxx"));
            return null;
        }
        if (!(loginUsers.getPhone().matches("[0-9]{10,11}"))) {
            FacesContext.getCurrentInstance().addMessage("txtPhone", new FacesMessage("Phone must be number between 10 and 11 digits"));
            return null;
        }
        if (rdDistrict.equals("hochiminh")) {
            //System.out.println("This is Distric value: " + districtId);            
            if (loginDistrictId != 0) {
                loginUsers.setDistrict(shippingFeeFacade.find(loginDistrictId));
            } else {
                FacesContext.getCurrentInstance().addMessage("cbDistrict", new FacesMessage("Please choose district!"));
                return null;
            }
        } else {
            loginUsers.setDistrict(null);
        }
        loginUsers.setFullName(loginUsers.getFullName().trim());
        usersFacade.edit(loginUsers);
        return "/member/profile?faces-redirect=true";
    }

    public String changePassword() {
        if (!oldPassword.equals(loginUsers.getUserPass())) {
            FacesContext.getCurrentInstance().addMessage("txtOldPassword", new FacesMessage("Current Password is not correct"));
            return null;
        }
        if (newPassword.equals(loginUsers.getUserPass())) {
            FacesContext.getCurrentInstance().addMessage("txtNewPassword", new FacesMessage("New Password must be different Current Password"));
            return null;
        }
        if (!confirmPassword.equals(newPassword)) {
            FacesContext.getCurrentInstance().addMessage("txtConfirmPassword", new FacesMessage("Confirm Password must be same New Password"));
            return null;
        } else {
            loginUsers.setUserPass(newPassword);
        }
        usersFacade.edit(loginUsers);
        oldPassword = "";
        newPassword = "";
        confirmPassword = "";
        return "/member/profile?faces-redirect=true";
    }

    public String gotoProfile() {
        return "/member/profile?faces-redirect=true";
    }
    public String gotoChangeProfile() {
        rdDistrict = (loginUsers.getDistrict() == null) ? "other" : "hochiminh";
        loginDistrictId = (rdDistrict.equals("other")) ? 0 : loginUsers.getDistrict().getId();
        return "/member/changeProfile?faces-redirect=true";
    }

    public String logout() {
        loginUsers = null;
        return "/index?faces-redirect=true";
    }

    //lay order cuar login user
    public List<Orders> fetchOrderByUser() {
        return ordersFacade.showOrderByUser(loginUsers.getId());
    }
    
    
    
    //view detail order
    public String gotoUserOrderDetail(int id){
        selectedUserOrder = ordersFacade.find(id);
        selectedUserOrderDetailList = orderDetailFacade.getOrderDetailByOrderId(id);
        return "/member/order-detail?faces-redirect=true";
    }
}
