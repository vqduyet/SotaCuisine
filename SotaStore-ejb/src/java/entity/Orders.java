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
@Table(name = "Orders", catalog = "Sota_Cafe", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
    @NamedQuery(name = "Orders.findById", query = "SELECT o FROM Orders o WHERE o.id = :id"),
    @NamedQuery(name = "Orders.findByUsersName", query = "SELECT o FROM Orders o WHERE o.usersName = :usersName"),
    @NamedQuery(name = "Orders.findByUsersPhone", query = "SELECT o FROM Orders o WHERE o.usersPhone = :usersPhone"),
    @NamedQuery(name = "Orders.findByShippingAddress", query = "SELECT o FROM Orders o WHERE o.shippingAddress = :shippingAddress"),
    @NamedQuery(name = "Orders.findByShippingFee", query = "SELECT o FROM Orders o WHERE o.shippingFee = :shippingFee"),
    @NamedQuery(name = "Orders.findByScore", query = "SELECT o FROM Orders o WHERE o.score = :score"),
    @NamedQuery(name = "Orders.findByQuantity", query = "SELECT o FROM Orders o WHERE o.quantity = :quantity"),
    @NamedQuery(name = "Orders.findByAmount", query = "SELECT o FROM Orders o WHERE o.amount = :amount"),
    @NamedQuery(name = "Orders.findByStatus", query = "SELECT o FROM Orders o WHERE o.status = :status"),
    @NamedQuery(name = "Orders.findByCreated", query = "SELECT o FROM Orders o WHERE o.created = :created")})
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Users_Name", nullable = false, length = 50)
    private String usersName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Users_Phone", nullable = false, length = 20)
    private String usersPhone;
    @Size(max = 200)
    @Column(name = "Shipping_Address", length = 200)
    private String shippingAddress;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "Shipping_Fee", nullable = false, precision = 19, scale = 4)
    private BigDecimal shippingFee;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Score", nullable = false)
    private int score;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Quantity", nullable = false)
    private int quantity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Note", length = 2147483647)
    private String note;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Status", nullable = false, length = 20)
    private String status;
    @Column(name = "Created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
    private Collection<OrderDetail> orderDetailCollection;
    @JoinColumn(name = "Users_Id", referencedColumnName = "Id")
    @ManyToOne
    private Users usersId;
    @JoinColumn(name = "District", referencedColumnName = "Id", nullable = false)
    @ManyToOne(optional = false)
    private ShippingFee district;

    public Orders() {
    }

    public Orders(Integer id) {
        this.id = id;
    }

    public Orders(Integer id, String usersName, String usersPhone, BigDecimal shippingFee, int score, int quantity, BigDecimal amount, String status) {
        this.id = id;
        this.usersName = usersName;
        this.usersPhone = usersPhone;
        this.shippingFee = shippingFee;
        this.score = score;
        this.quantity = quantity;
        this.amount = amount;
        this.status = status;
    }

    public Orders(Integer id, String usersName, String usersPhone, String shippingAddress, BigDecimal shippingFee, int score, int quantity, BigDecimal amount, String note, String status, Date created) {
        this.id = id;
        this.usersName = usersName;
        this.usersPhone = usersPhone;
        this.shippingAddress = shippingAddress;
        this.shippingFee = shippingFee;
        this.score = score;
        this.quantity = quantity;
        this.amount = amount;
        this.note = note;
        this.status = status;
        this.created = created;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    public String getUsersPhone() {
        return usersPhone;
    }

    public void setUsersPhone(String usersPhone) {
        this.usersPhone = usersPhone;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
    public Collection<OrderDetail> getOrderDetailCollection() {
        return orderDetailCollection;
    }

    public void setOrderDetailCollection(Collection<OrderDetail> orderDetailCollection) {
        this.orderDetailCollection = orderDetailCollection;
    }

    public Users getUsersId() {
        return usersId;
    }

    public void setUsersId(Users usersId) {
        this.usersId = usersId;
    }

    public ShippingFee getDistrict() {
        return district;
    }

    public void setDistrict(ShippingFee district) {
        this.district = district;
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
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Orders[ id=" + id + " ]";
    }
    
}
