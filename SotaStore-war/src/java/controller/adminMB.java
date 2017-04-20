/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import filter.Util;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import entity.Admin;
import beans.AdminFacadeLocal;
import entity.AdminGroup;
import beans.AdminGroupFacadeLocal;

/**
 *
 * @author Lenovo
 */
@ManagedBean
@SessionScoped
public class adminMB {

    @EJB
    private AdminGroupFacadeLocal adminGroupFacade;

    @EJB
    private AdminFacadeLocal adminFacade;

    /**
     * Creates a new instance of adminMB
     */
    private Admin lAd;
    private String uname, upass, ufullname, uphone, uaddress;
    private String upfullname, upphone, upaddress, upid, upname, upstatus;
    private String uppass, oldpass;
    private Admin selectedAd = new Admin(), editedAd = new Admin();
    private int adGroupId;
    private List<AdminGroup> listAdGr;
    private Admin ad = new Admin();
    private String username, password;
    private List<Admin> list = new ArrayList<Admin>();
    private AdminGroup selectedGr = new AdminGroup();
    private Admin LoginAdmin = new Admin();
    private String oldPassword, newPassword, comfirmPassword;

    public Admin getLoginAdmin() {
        return LoginAdmin;
    }

    public void setLoginAdmin(Admin LoginAdmin) {
        this.LoginAdmin = LoginAdmin;
    }

    public String getOldPassword() {
        return oldPassword;
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

    public String getComfirmPassword() {
        return comfirmPassword;
    }

    public void setComfirmPassword(String comfirmPassword) {
        this.comfirmPassword = comfirmPassword;
    }

    public Admin getlAd() {
        return lAd;
    }

    public void setlAd(Admin lAd) {
        this.lAd = lAd;
    }

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

    public String getUpstatus() {
        return upstatus;
    }

    public void setUpstatus(String upstatus) {
        this.upstatus = upstatus;
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

    public String getUpphone() {
        return upphone;
    }

    public void setUpphone(String upphone) {
        this.upphone = upphone;
    }

    public String getUpaddress() {
        return upaddress;
    }

    public void setUpaddress(String upaddress) {
        this.upaddress = upaddress;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    public String getUfullname() {
        return ufullname;
    }

    public void setUfullname(String ufullname) {
        this.ufullname = ufullname;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUaddress() {
        return uaddress;
    }

    public void setUaddress(String uaddress) {
        this.uaddress = uaddress;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public AdminGroup getSelectedGr() {
        return selectedGr;
    }

    public void setSelectedGr(AdminGroup selectedGr) {
        this.selectedGr = selectedGr;
    }

    public Admin getEditedAd() {
        return editedAd;
    }

    public void setEditedAd(Admin editedAd) {
        this.editedAd = editedAd;
    }

    public int getAdGroupId() {
        return adGroupId;
    }

    public void setAdGroupId(int adGroupId) {
        this.adGroupId = adGroupId;
    }

    public List<AdminGroup> getListAdGr() {
        return listAdGr;
    }

    public Admin getSelectedAd() {
        return selectedAd;
    }

    public void setSelectedAd(Admin selectedAd) {
        this.selectedAd = selectedAd;
    }

    public void setListAdGr(List<AdminGroup> listAdGr) {
        this.listAdGr = listAdGr;
    }
    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Admin getAd() {
        return ad;
    }

    public void setAd(Admin ad) {
        this.ad = ad;
    }

    public List<Admin> getList() {
        return list;
    }

    public void setList(List<Admin> list) {
        this.list = list;
    }

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

    public String show() {
        //list = adminFacade.findAll();
        return "/admin/user/viewEmployees?faces-redirect=true";
    }

    public String login() {
        if (adminFacade.login(username, password)) {
            HttpSession session = Util.getSession();
            session.setAttribute("loginAdmin", adminFacade.findByName(username));

            lAd = adminFacade.findByName(username);

            session.setAttribute("isLogin", "true");

            return "/admin/index?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage("formLoginAd", new FacesMessage("Invalid username or password or you has been banned!"));
            return null;
        }
    }

    public String logout() {
        HttpSession session = Util.getSession();
        session.setAttribute("loginAdmin", null);
        lAd = null;
        return "/admin/login?faces-redirect=true";
    }

    public String displayAddEmployee() {
        ad = new Admin();
        //listAdGr = adminGroupFacade.findNormal();
        init();
        return "/admin/user/addEmployee?faces-redirect=true";
    }

    public void init() {
        uname = "";
        upass = "";
        ufullname = "";
        uphone = "";
        uaddress = "";
    }

    public String addEmployee() {
        if (!(uname.trim().length() >= 1 && uname.trim().length() <= 20)) {
            FacesContext.getCurrentInstance().addMessage("f1:username", new FacesMessage("Username must between 1 and 20 characters!"));
            return null;
        }
        if (adminFacade.isUsernameAvailable(uname)) {
            FacesContext.getCurrentInstance().addMessage("f1:username", new FacesMessage("Username is available, please enter again!"));
            return null;
        }
        if (uname.contains(" ")) {
            FacesContext.getCurrentInstance().addMessage("f1:username", new FacesMessage("Username cannot has blank!"));
            return null;
        }
        if (!(upass.length() >= 6 && upass.length() <= 20)) {
            FacesContext.getCurrentInstance().addMessage("f1:password", new FacesMessage("Password must between 6 and 20 characters!"));
            return null;
        }
        if (!(confirmPassword.equals(upass))) {
            FacesContext.getCurrentInstance().addMessage("f1:confirmPassword", new FacesMessage("Confirm password and password must be same!"));
            return null;
        }
        if (!(ufullname.trim().length() >= 1 && ufullname.trim().length() <= 50)) {
            FacesContext.getCurrentInstance().addMessage("f1:fullname", new FacesMessage("Name must between 1 and 50 characters!"));
            return null;
        }
        if (!(uphone.trim().matches("\\d{1,20}"))) {
            FacesContext.getCurrentInstance().addMessage("f1:phone", new FacesMessage("Phone must contains maximum 20 digits!"));
            return null;
        }
        if (uaddress.trim().length() > 255) {
            FacesContext.getCurrentInstance().addMessage("f1:address", new FacesMessage("Address cannot be more than 255 characters!"));
            return null;
        }
        ad.setUserName(uname.trim());
        ad.setUserPass(upass);
        ad.setFullName(ufullname.trim());
        ad.setPhone(uphone.trim());
        ad.setAddress(uaddress.trim());
        ad.setStatus("Normal");
        ad.setAdmingroupid(adminGroupFacade.find(adGroupId));
        adminFacade.create(ad);
        ad = new Admin();
        init();
        return this.show();
    }

    public String updateStatusGroup() {
        if (selectedGr.getStatus().equalsIgnoreCase("Inactive")) {
            if (adminFacade.isGroupHasMember(selectedGr)) {
                FacesContext.getCurrentInstance().addMessage("formGr:status", new FacesMessage("group has members"));
                return null;
            }
        }
        adminGroupFacade.edit(selectedGr);
        return this.displayViewGroup();
    }

    public String editEmployee() {

        if (!(upfullname.trim().length() >= 1 && upfullname.trim().length() <= 50)) {
            FacesContext.getCurrentInstance().addMessage("f2:fullname", new FacesMessage("Name must between 1 and 50 characters!"));
            return null;
        }
        if (!(upphone.trim().matches("\\d{1,20}"))) {
            FacesContext.getCurrentInstance().addMessage("f2:phone", new FacesMessage("Phone must contains maximum 20 digits!"));
            return null;
        }
        if (upaddress.trim().length() > 255) {
            FacesContext.getCurrentInstance().addMessage("f2:address", new FacesMessage("Address cannot be more than 255 characters!"));
            return null;
        }

        editedAd.setFullName(upfullname.trim());
        editedAd.setPhone(upphone.trim());
        editedAd.setAddress(upaddress.trim());

        editedAd.setAdmingroupid(adminGroupFacade.find(adGroupId));
        adminFacade.edit(editedAd);
        return this.show();
    }

    public String updateStatusEmployee() {
        adminFacade.edit(editedAd);
        return this.show();
    }

    public String resetPasswordEmployee() {
        if (!(uppass.length() >= 6 && uppass.length() <= 20)) {
            FacesContext.getCurrentInstance().addMessage("f3:password", new FacesMessage("Password must between 6 and 20 characters!"));
            return null;
        }
        if (uppass.equals(oldpass)) {
            FacesContext.getCurrentInstance().addMessage("f3:password", new FacesMessage("this is old password!"));
            return null;
        }
        if (!(confirmPassword.equals(uppass))) {
            FacesContext.getCurrentInstance().addMessage("f3:confirmPassword", new FacesMessage("Confirm password and password must be same!"));
            return null;
        }

        editedAd.setUserPass(uppass);
        adminFacade.edit(editedAd);
        return this.show();
    }

    public String displayEditEmployee(int id) {
        HttpSession session = Util.getSession();
        LoginAdmin = (Admin) session.getAttribute("loginAdmin");
        if (LoginAdmin.getId() == id) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("cannot edit yourself!"));
            return null;
        }
        editedAd = adminFacade.find(id);
        adGroupId = editedAd.getAdmingroupid().getId();
        upid = editedAd.getId().toString();
        upname = editedAd.getUserName();
        upfullname = editedAd.getFullName();
        upphone = editedAd.getPhone();
        upaddress = editedAd.getAddress();
        upstatus = editedAd.getStatus();
        return "/admin/user/updateEmployee?faces-redirect=true";
    }

    public String displayUpdateStatus(int id) {
        HttpSession session = Util.getSession();
        LoginAdmin = (Admin) session.getAttribute("loginAdmin");
        if (LoginAdmin.getId() == id) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("cannot update status yourself!"));
            return null;
        }
        editedAd = adminFacade.find(id);
        return "/admin/user/updateStatusEmployee?faces-redirect=true";
    }

    public String displayResetPassword(int id) {
        HttpSession session = Util.getSession();
        LoginAdmin = (Admin) session.getAttribute("loginAdmin");
        if (LoginAdmin.getId() == id) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("cannot reset password yourself!"));
            //showAlert = true;
            return null;
        }
        editedAd = adminFacade.find(id);
        upid = editedAd.getId().toString();
        upname = editedAd.getUserName();
        upfullname = editedAd.getFullName();
        oldpass = editedAd.getUserPass();
        uppass = "";

        confirmPassword = "";
        return "/admin/user/resetPasswordEmployee?faces-redirect=true";
    }

    public String displayUpdateStatusGroup(int id) {
        selectedGr = adminGroupFacade.find(id);
        return "/admin/user/updateStatusGroup?faces-redirect=true";
    }

    public String displayDetail(int id) {
        selectedAd = adminFacade.find(id);
        return "/admin/user/detailEmployee?faces-redirect=true";
    }

    public String displayDetailGroup(int id) {
        selectedGr = adminGroupFacade.find(id);
        return "/admin/user/detailGroup?faces-redirect=true";
    }

    public String displayViewGroup() {
        return "/admin/user/viewGroups?faces-redirect=true";
    }

    public List<AdminGroup> showAdminGroup() {
        return adminGroupFacade.findNormal();
    }

    public List<AdminGroup> showAllAdGr() {
        return adminGroupFacade.findAll();
    }

    public List<Admin> showAll() {
        return adminFacade.findAll();
    }

    public List<Admin> showAdminByGroup() {
        return adminFacade.findByGroup(selectedGr);
    }

    public adminMB() {
    }

    public String gotoAdminChangeProfile() {
        return "/admin/setting/changeProfile?faces-redirect=true";
    }

    public String changeAdminProfile() {
        if (!(getlAd().getFullName().trim().length() >= 4 && getlAd().getFullName().trim().length() <= 50)) {
            FacesContext.getCurrentInstance().addMessage("txtFullName", new FacesMessage("Fullname between 4 and 50 characters"));
            return null;
        }
        if (!(getlAd().getAddress().trim().length() >= 1 && getlAd().getAddress().trim().length() <= 255)) {
            FacesContext.getCurrentInstance().addMessage("txtAddress", new FacesMessage("Address between 1 and 255 characters"));
            return null;
        }
        if (!getlAd().getPhone().trim().matches("[0-9]{10,11}")) {
            FacesContext.getCurrentInstance().addMessage("txtPhone", new FacesMessage("Phone number contains between 10 and 11 digits"));
            return null;
        }
        lAd.setFullName(getlAd().getFullName().trim());
        lAd.setAddress(getlAd().getAddress().trim());
        lAd.setPhone(getlAd().getPhone().trim());
        adminFacade.edit(lAd);
        return "/admin/setting/profile?faces-redirect=true";
    }

    public String changeAdminPassword() {
        if (!oldPassword.equals(lAd.getUserPass())) {
            FacesContext.getCurrentInstance().addMessage("txtAdminOldPassword", new FacesMessage("Current Password is not correct"));
            return null;
        }
        if (newPassword.equals(lAd.getUserPass())) {
            System.out.println("this is oldpass:" + lAd.getUserPass());
            System.out.println("this is newpass:" + newPassword);
            FacesContext.getCurrentInstance().addMessage("txtAdminNewPassword", new FacesMessage("New Password must be different Current Password"));
            return null;
        }
        if (!confirmPassword.equals(newPassword)) {
            FacesContext.getCurrentInstance().addMessage("txtAdminConfirmPassword", new FacesMessage("Confirm Password and New Password must be same"));
            return null;
        } else {
            lAd.setUserPass(newPassword);
        }
        adminFacade.edit(lAd);
        oldPassword = "";
        newPassword = "";
        confirmPassword = "";
        return "/admin/setting/profile?faces-redirect=true";
    }
}
