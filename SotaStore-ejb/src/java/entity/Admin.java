/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Duyet
 */
@Entity
@Table(name = "Admin", catalog = "Sota_Cafe", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Admin.findAll", query = "SELECT a FROM Admin a"),
    @NamedQuery(name = "Admin.findById", query = "SELECT a FROM Admin a WHERE a.id = :id"),
    @NamedQuery(name = "Admin.findByUserName", query = "SELECT a FROM Admin a WHERE a.userName = :userName"),
    @NamedQuery(name = "Admin.findByUserPass", query = "SELECT a FROM Admin a WHERE a.userPass = :userPass"),
    @NamedQuery(name = "Admin.findByFullName", query = "SELECT a FROM Admin a WHERE a.fullName = :fullName"),
    @NamedQuery(name = "Admin.findByAddress", query = "SELECT a FROM Admin a WHERE a.address = :address"),
    @NamedQuery(name = "Admin.findByPhone", query = "SELECT a FROM Admin a WHERE a.phone = :phone"),
    @NamedQuery(name = "Admin.findByStatus", query = "SELECT a FROM Admin a WHERE a.status = :status"),
    @NamedQuery(name = "Admin.findByCreated", query = "SELECT a FROM Admin a WHERE a.created = :created")})
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)    
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "UserName", nullable = false, length = 20)
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "UserPass", nullable = false, length = 20)
    private String userPass;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "FullName", nullable = false, length = 50)
    private String fullName;
    @Size(max = 255)
    @Column(name = "Address", length = 255)
    private String address;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "Phone", length = 20)
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Status", nullable = false, length = 10)
    private String status;
    @Column(name = "Created", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @JoinColumn(name = "Admin_group_id", referencedColumnName = "Id", nullable = false)
    @ManyToOne(optional = false)
    private AdminGroup admingroupid;

    public Admin() {
    }

    public Admin(Integer id) {
        this.id = id;
    }

    public Admin(Integer id, String userName, String userPass, String fullName, String status) {
        this.id = id;
        this.userName = userName;
        this.userPass = userPass;
        this.fullName = fullName;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public AdminGroup getAdmingroupid() {
        return admingroupid;
    }

    public void setAdmingroupid(AdminGroup admingroupid) {
        this.admingroupid = admingroupid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Admin)) {
            return false;
        }
        Admin other = (Admin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Admin[ id=" + id + " ]";
    }
    
}
