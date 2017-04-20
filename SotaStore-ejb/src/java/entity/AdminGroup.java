/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Duyet
 */
@Entity
@Table(name = "Admin_Group", catalog = "Sota_Cafe", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdminGroup.findAll", query = "SELECT a FROM AdminGroup a"),
    @NamedQuery(name = "AdminGroup.findById", query = "SELECT a FROM AdminGroup a WHERE a.id = :id"),
    @NamedQuery(name = "AdminGroup.findByName", query = "SELECT a FROM AdminGroup a WHERE a.name = :name"),
    @NamedQuery(name = "AdminGroup.findBySortOrder", query = "SELECT a FROM AdminGroup a WHERE a.sortOrder = :sortOrder"),
    @NamedQuery(name = "AdminGroup.findByStatus", query = "SELECT a FROM AdminGroup a WHERE a.status = :status"),
    @NamedQuery(name = "AdminGroup.findByCreated", query = "SELECT a FROM AdminGroup a WHERE a.created = :created")})
public class AdminGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "Name", nullable = false, length = 150)
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Sort_Order", nullable = false)
    private int sortOrder;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "Permissions", nullable = false, length = 2147483647)
    private String permissions;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Status", nullable = false, length = 10)
    private String status;
    @Column(name = "Created", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admingroupid")
    private Collection<Admin> adminCollection;

    public AdminGroup() {
    }

    public AdminGroup(Integer id) {
        this.id = id;
    }

    public AdminGroup(Integer id, String name, int sortOrder, String permissions, String status) {
        this.id = id;
        this.name = name;
        this.sortOrder = sortOrder;
        this.permissions = permissions;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
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

    @XmlTransient
    public Collection<Admin> getAdminCollection() {
        return adminCollection;
    }

    public void setAdminCollection(Collection<Admin> adminCollection) {
        this.adminCollection = adminCollection;
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
        if (!(object instanceof AdminGroup)) {
            return false;
        }
        AdminGroup other = (AdminGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AdminGroup[ id=" + id + " ]";
    }
    
}
