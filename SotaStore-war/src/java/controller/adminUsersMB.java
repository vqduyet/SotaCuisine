/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Users;
import beans.UsersFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Lenovo
 */
@ManagedBean
@SessionScoped
public class adminUsersMB {

    @EJB
    private UsersFacadeLocal usersFacade;

    /**
     * Creates a new instance of adminUsersMB
     */
    private Users selectedCus = new Users();
    private String confirmPassword, oldpass;
    private String uppass, upid, upname, upfullname;

    public String getOldpass() {
        return oldpass;
    }

    public void setOldpass(String oldpass) {
        this.oldpass = oldpass;
    }

    public String getUppass() {
        return uppass;
    }

    public void setUppass(String uppass) {
        this.uppass = uppass;
    }

    public String getUpid() {
        return upid;
    }

    public void setUpid(String upid) {
        this.upid = upid;
    }

    public String getUpname() {
        return upname;
    }

    public void setUpname(String upname) {
        this.upname = upname;
    }

    public String getUpfullname() {
        return upfullname;
    }

    public void setUpfullname(String upfullname) {
        this.upfullname = upfullname;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Users getSelectedCus() {
        return selectedCus;
    }

    public void setSelectedCus(Users selectedCus) {
        this.selectedCus = selectedCus;
    }

    public String displayDetail(int id) {
        selectedCus = usersFacade.find(id);
        return "/admin/user/detailCustomer?faces-redirect=true";
    }

    public String displayUpdateStatusCustomer(int id) {
        selectedCus = usersFacade.find(id);
        return "/admin/user/updateStatusCustomer?faces-redirect=true";
    }

    public String displayShow() {
        return "/admin/user/viewCustomers?faces-redirect=true";
    }

    public String displayResetPasswordCustomer(int id) {

        selectedCus = usersFacade.find(id);
        upid = selectedCus.getId().toString();
        upname = selectedCus.getUserName();
        upfullname = selectedCus.getFullName();
        uppass = "";
        oldpass = selectedCus.getUserPass();
        confirmPassword = "";

        return "/admin/user/resetPasswordCustomer?faces-redirect=true";
    }

    public String resetPasswordCutomer() {
        if (!(uppass.length() >= 6 && uppass.length() <= 20)) {
            FacesContext.getCurrentInstance().addMessage("formResetCus:password", new FacesMessage("Password must between 6 to 20 characters!"));
            return null;
        }
        if (uppass.equals(oldpass)){
            FacesContext.getCurrentInstance().addMessage("formResetCus:password", new FacesMessage("this is old password!"));
            return null;
        }
        if (!(confirmPassword.equals(uppass))) {
            FacesContext.getCurrentInstance().addMessage("formResetCus:confirmPassword", new FacesMessage("Confirm password and password must be same!"));
            return null;
        }
        selectedCus.setUserPass(uppass);
        usersFacade.edit(selectedCus);
        return this.displayShow();
    }

    public String updateStatusCustomer() {
        usersFacade.edit(selectedCus);
        return this.displayShow();
    }

    public List<Users> showAll() {
        return usersFacade.findAll();
    }

    public adminUsersMB() {
    }
    
    /* get list of new member today */

}
