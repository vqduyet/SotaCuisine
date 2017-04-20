/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import entity.Contact;
import beans.ContactFacadeLocal;
import java.util.Date;

/**
 *
 * @author Lenovo
 */
@ManagedBean
@SessionScoped
public class contactMB {

    @EJB
    private ContactFacadeLocal contactFacade;
    private String conName = "", conPhone = "", conEmail = "", conContent = "", conAddress = "";
    private Contact addContact = new Contact();
    private Contact selectedContact = new Contact();

    public String getConName() {
        return conName;
    }

    public void setConName(String conName) {
        this.conName = conName;
    }

    public String getConPhone() {
        return conPhone;
    }

    public void setConPhone(String conPhone) {
        this.conPhone = conPhone;
    }

    public String getConEmail() {
        return conEmail;
    }

    public void setConEmail(String conEmail) {
        this.conEmail = conEmail;
    }

    public String getConContent() {
        return conContent;
    }

    public void setConContent(String conContent) {
        this.conContent = conContent;
    }

    public String getConAddress() {
        return conAddress;
    }

    public void setConAddress(String conAddress) {
        this.conAddress = conAddress;
    }

    public Contact getSelectedContact() {
        return selectedContact;
    }

    public void setSelectedContact(Contact selectedContact) {
        this.selectedContact = selectedContact;
    }

    public Contact getAddContact() {
        return addContact;
    }

    public void setAddContact(Contact addContact) {
        this.addContact = addContact;
    }

    public String insertContact() {
        //addContact.setUsersId(null);
        if (!(conName.trim().length() >= 1 && conName.trim().length() <= 100)) {
            FacesContext.getCurrentInstance().addMessage("formContact:name", new FacesMessage("Name must between 1 and 100 characters!"));
            return null;
        }
        if (!(conEmail.trim().length() >= 1 && conEmail.trim().length() <= 50)) {
            FacesContext.getCurrentInstance().addMessage("formContact:email", new FacesMessage("Email must between 1 and 50 characters!"));
            return null;
        }
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(conEmail.trim());
        if (!(m.matches())) {
            FacesContext.getCurrentInstance().addMessage("formContact:email", new FacesMessage("Wrong email format!"));
            return null;
        }
        if (!conPhone.trim().matches("\\d{1,20}")) {
            FacesContext.getCurrentInstance().addMessage("formContact:phone", new FacesMessage("Phone must contains maximum 20 digits!"));
            return null;
        }
        if (!(conAddress.trim().length() >= 1 && conAddress.trim().length() <= 255)) {
            FacesContext.getCurrentInstance().addMessage("formContact:address", new FacesMessage("Address must between 1 and 255 characters!"));
            return null;
        }
        if (!(conContent.trim().length() >= 1 && conContent.trim().length() <= 255)) {
            FacesContext.getCurrentInstance().addMessage("formContact:content", new FacesMessage("Content cannot be blank and more than 255 characters"));
            return null;
        }
        addContact.setName(conName);
        addContact.setAddress(conAddress);
        addContact.setEmail(conEmail);
        addContact.setPhone(conPhone);
        addContact.setContent(conContent);
        
        addContact.setStatus("Waiting");
        addContact.setCreated(new Date());
        contactFacade.create(addContact);
        return "index?faces-redirect=true";
    }

    public String displayContact() {
        addContact = new Contact();
        return "contact";
    }

    public String displayViewContacts() {
        return "/admin/user/viewContacts?faces-redirect=true";
    }

    public String displayDetailContact(int id) {
        selectedContact = contactFacade.find(id);
        return "/admin/user/detailContact?faces-redirect=true";
    }

    public String updateStatusContact() {
        contactFacade.edit(selectedContact);
        return this.displayViewContacts();
    }

    public List<Contact> showWaiting() {
        return contactFacade.findWaiting();
    }

    public List<Contact> showInProgress() {
        return contactFacade.findInProgress();
    }

    public List<Contact> showCompleted() {
        return contactFacade.findCompleted();
    }

    public contactMB() {
    }
    
    /* show all contact ticket */
    public List<Contact> showAllContacts(){
        return contactFacade.findAll();
    }

}
