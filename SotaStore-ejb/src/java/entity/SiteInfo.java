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
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "Site_Info", catalog = "Sota_Cafe", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SiteInfo.findAll", query = "SELECT s FROM SiteInfo s"),
    @NamedQuery(name = "SiteInfo.findById", query = "SELECT s FROM SiteInfo s WHERE s.id = :id"),
    @NamedQuery(name = "SiteInfo.findByKey", query = "SELECT s FROM SiteInfo s WHERE s.key = :key"),
    @NamedQuery(name = "SiteInfo.findByCreated", query = "SELECT s FROM SiteInfo s WHERE s.created = :created")})
public class SiteInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Key", nullable = false, length = 255)
    private String key;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Value", length = 2147483647)
    private String value;
    @Column(name = "Created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public SiteInfo() {
    }

    public SiteInfo(Integer id) {
        this.id = id;
    }

    public SiteInfo(Integer id, String key) {
        this.id = id;
        this.key = key;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
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
        if (!(object instanceof SiteInfo)) {
            return false;
        }
        SiteInfo other = (SiteInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SiteInfo[ id=" + id + " ]";
    }
    
}
