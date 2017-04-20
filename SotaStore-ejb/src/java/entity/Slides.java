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
@Table(name = "Slides", catalog = "Sota_Cafe", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Slides.findAll", query = "SELECT s FROM Slides s"),
    @NamedQuery(name = "Slides.findById", query = "SELECT s FROM Slides s WHERE s.id = :id"),
    @NamedQuery(name = "Slides.findByName", query = "SELECT s FROM Slides s WHERE s.name = :name"),
    @NamedQuery(name = "Slides.findByImageName", query = "SELECT s FROM Slides s WHERE s.imageName = :imageName"),
    @NamedQuery(name = "Slides.findByImageLink", query = "SELECT s FROM Slides s WHERE s.imageLink = :imageLink"),
    @NamedQuery(name = "Slides.findByLink", query = "SELECT s FROM Slides s WHERE s.link = :link"),
    @NamedQuery(name = "Slides.findByInfo", query = "SELECT s FROM Slides s WHERE s.info = :info"),
    @NamedQuery(name = "Slides.findBySortOrder", query = "SELECT s FROM Slides s WHERE s.sortOrder = :sortOrder"),
    @NamedQuery(name = "Slides.findByCreated", query = "SELECT s FROM Slides s WHERE s.created = :created")})
public class Slides implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Name", nullable = false, length = 100)
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Image_Name", nullable = false, length = 100)
    private String imageName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Image_Link", nullable = false, length = 255)
    private String imageLink;
    @Size(max = 100)
    @Column(name = "Link", length = 100)
    private String link;
    @Size(max = 100)
    @Column(name = "Info", length = 100)
    private String info;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Sort_Order", nullable = false)
    private int sortOrder;
    @Column(name = "Created", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public Slides() {
    }

    public Slides(Integer id) {
        this.id = id;
    }

    public Slides(Integer id, String name, String imageName, String imageLink, int sortOrder) {
        this.id = id;
        this.name = name;
        this.imageName = imageName;
        this.imageLink = imageLink;
        this.sortOrder = sortOrder;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
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
        if (!(object instanceof Slides)) {
            return false;
        }
        Slides other = (Slides) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Slides[ id=" + id + " ]";
    }
    
}
