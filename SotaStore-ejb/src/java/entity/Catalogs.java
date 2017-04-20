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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "Catalogs", catalog = "Sota_Cafe", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Catalogs.findAll", query = "SELECT c FROM Catalogs c"),
    @NamedQuery(name = "Catalogs.findById", query = "SELECT c FROM Catalogs c WHERE c.id = :id"),
    @NamedQuery(name = "Catalogs.findByName", query = "SELECT c FROM Catalogs c WHERE c.name = :name"),
    @NamedQuery(name = "Catalogs.findByIcons", query = "SELECT c FROM Catalogs c WHERE c.icons = :icons"),
    @NamedQuery(name = "Catalogs.findByParentId", query = "SELECT c FROM Catalogs c WHERE c.parentId = :parentId"),
    @NamedQuery(name = "Catalogs.findBySubParentId", query = "SELECT c FROM Catalogs c WHERE c.parentId != :parentId AND c.status ='Normal'" ),
    @NamedQuery(name = "Catalogs.findBySortOrder", query = "SELECT c FROM Catalogs c WHERE c.sortOrder = :sortOrder"),
    @NamedQuery(name = "Catalogs.findByStatus", query = "SELECT c FROM Catalogs c WHERE c.status = :status"),
    @NamedQuery(name = "Catalogs.findByCreated", query = "SELECT c FROM Catalogs c WHERE c.created = :created")})
public class Catalogs implements Serializable {
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
    @Size(max = 150)
    @Column(name = "Icons", length = 150)
    private String icons;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Parent_Id", nullable = false)
    private int parentId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Sort_Order", nullable = false)
    private int sortOrder;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Status", nullable = false, length = 10)
    private String status;
    @Column(name = "Created", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @OneToMany(mappedBy = "catalogId")
    private Collection<Products> productsCollection;

    public Catalogs() {
    }

    public Catalogs(Integer id) {
        this.id = id;
    }

    public Catalogs(Integer id, String name, int parentId, int sortOrder, String status) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.sortOrder = sortOrder;
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

    public String getIcons() {
        return icons;
    }

    public void setIcons(String icons) {
        this.icons = icons;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
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
    public Collection<Products> getProductsCollection() {
        return productsCollection;
    }

    public void setProductsCollection(Collection<Products> productsCollection) {
        this.productsCollection = productsCollection;
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
        if (!(object instanceof Catalogs)) {
            return false;
        }
        Catalogs other = (Catalogs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Catalogs[ id=" + id + " ]";
    }
    
}
