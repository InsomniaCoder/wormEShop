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
@Table(name = "PRODUCT_STOCK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductStock.findAll", query = "SELECT p FROM ProductStock p"),
    @NamedQuery(name = "ProductStock.findById", query = "SELECT p FROM ProductStock p WHERE p.id = :id"),
    @NamedQuery(name = "ProductStock.findByProductId", query = "SELECT p FROM ProductStock p WHERE p.productId = :productId")})
public class ProductStock implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PRODUCT_ID")
    private Integer productId;

    public ProductStock() {
    }

    public ProductStock(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
        if (!(object instanceof ProductStock)) {
            return false;
        }
        ProductStock other = (ProductStock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wormshop.ejb.ProductStock[ id=" + id + " ]";
    }
    
}
