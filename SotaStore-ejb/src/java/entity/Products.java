/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "Products", catalog = "Sota_Cafe", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p"),
    @NamedQuery(name = "Products.findById", query = "SELECT p FROM Products p WHERE p.id = :id"),
    @NamedQuery(name = "Products.findByName", query = "SELECT p FROM Products p WHERE p.name = :name"),
    @NamedQuery(name = "Products.findByPrice", query = "SELECT p FROM Products p WHERE p.price = :price"),
    @NamedQuery(name = "Products.findByShortDescriptions", query = "SELECT p FROM Products p WHERE p.shortDescriptions = :shortDescriptions"),
    @NamedQuery(name = "Products.findByDiscount", query = "SELECT p FROM Products p WHERE p.discount = :discount"),
    @NamedQuery(name = "Products.findByImageLink", query = "SELECT p FROM Products p WHERE p.imageLink = :imageLink"),
    @NamedQuery(name = "Products.findByViewNo", query = "SELECT p FROM Products p WHERE p.viewNo = :viewNo"),
    @NamedQuery(name = "Products.findByBought", query = "SELECT p FROM Products p WHERE p.bought = :bought"),
    @NamedQuery(name = "Products.findByRateTotal", query = "SELECT p FROM Products p WHERE p.rateTotal = :rateTotal"),
    @NamedQuery(name = "Products.findByRateCount", query = "SELECT p FROM Products p WHERE p.rateCount = :rateCount"),
    @NamedQuery(name = "Products.findByStatus", query = "SELECT p FROM Products p WHERE p.status = :status"),
    @NamedQuery(name = "Products.findByCreated", query = "SELECT p FROM Products p WHERE p.created = :created")})
public class Products implements Serializable {
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "Price", nullable = false, precision = 19, scale = 4)
    private BigDecimal price;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Decription", length = 2147483647)
    private String decription;
    @Size(max = 150)
    @Column(name = "Short_Descriptions", length = 150)
    private String shortDescriptions;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Discount", nullable = false, precision = 19, scale = 4)
    private BigDecimal discount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Image_Link", nullable = false, length = 255)
    private String imageLink;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Image_Link_Detail", length = 2147483647)
    private String imageLinkDetail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "View_No", nullable = false)
    private int viewNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Bought", nullable = false)
    private int bought;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Rate_Total", nullable = false)
    private int rateTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Rate_Count", nullable = false)
    private int rateCount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Status", nullable = false, length = 10)
    private String status;
    @Column(name = "Created", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @JoinColumn(name = "CatalogId", referencedColumnName = "Id")
    @ManyToOne
    private Catalogs catalogId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "products")
    private Collection<OrderDetail> orderDetailCollection;

    public Products() {
    }

    public Products(Integer id) {
        this.id = id;
    }

    public Products(Integer id, String name, BigDecimal price, BigDecimal discount, String imageLink, int viewNo, int bought, int rateTotal, int rateCount, String status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.imageLink = imageLink;
        this.viewNo = viewNo;
        this.bought = bought;
        this.rateTotal = rateTotal;
        this.rateCount = rateCount;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getShortDescriptions() {
        return shortDescriptions;
    }

    public void setShortDescriptions(String shortDescriptions) {
        this.shortDescriptions = shortDescriptions;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getImageLinkDetail() {
        return imageLinkDetail;
    }

    public void setImageLinkDetail(String imageLinkDetail) {
        this.imageLinkDetail = imageLinkDetail;
    }

    public int getViewNo() {
        return viewNo;
    }

    public void setViewNo(int viewNo) {
        this.viewNo = viewNo;
    }

    public int getBought() {
        return bought;
    }

    public void setBought(int bought) {
        this.bought = bought;
    }

    public int getRateTotal() {
        return rateTotal;
    }

    public void setRateTotal(int rateTotal) {
        this.rateTotal = rateTotal;
    }

    public int getRateCount() {
        return rateCount;
    }

    public void setRateCount(int rateCount) {
        this.rateCount = rateCount;
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

    public Catalogs getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Catalogs catalogId) {
        this.catalogId = catalogId;
    }

    @XmlTransient
    public Collection<OrderDetail> getOrderDetailCollection() {
        return orderDetailCollection;
    }

    public void setOrderDetailCollection(Collection<OrderDetail> orderDetailCollection) {
        this.orderDetailCollection = orderDetailCollection;
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
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Products[ id=" + id + " ]";
    }
    
}
