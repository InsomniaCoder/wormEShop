/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wormshop.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tanat
 */
@Entity
@Table(name = "CUSTOMER_CREDIT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerCredit.findAll", query = "SELECT c FROM CustomerCredit c"),
    @NamedQuery(name = "CustomerCredit.findById", query = "SELECT c FROM CustomerCredit c WHERE c.id = :id"),
    @NamedQuery(name = "CustomerCredit.findByCustId", query = "SELECT c FROM CustomerCredit c WHERE c.custId = :custId"),
    @NamedQuery(name = "CustomerCredit.findByCredit", query = "SELECT c FROM CustomerCredit c WHERE c.credit = :credit")})
public class CustomerCredit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CUST_ID")
    private Integer custId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CREDIT")
    private Double credit;

    public CustomerCredit() {
    }

    public CustomerCredit(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
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
        if (!(object instanceof CustomerCredit)) {
            return false;
        }
        CustomerCredit other = (CustomerCredit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wormshop.ejb.CustomerCredit[ id=" + id + " ]";
    }
    
}
