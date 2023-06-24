package com.fsse2305.e_commerce_project.data.transaction_product.entity;

import com.fsse2305.e_commerce_project.data.cart_Item.entity.CartItemEntity;
import com.fsse2305.e_commerce_project.data.transaction.entity.TransactionEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "transaction_product")
public class TransactionProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tpid;

    @ManyToOne
    @JoinColumn(name = "tid", nullable = false)
    private TransactionEntity transaction;

    @Column(nullable = false)
    private Integer pid;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal subtotal;

    public TransactionProductEntity() {
    }

    public TransactionProductEntity(TransactionEntity transactionEntity, CartItemEntity cartItemEntity) {
        this.transaction = transactionEntity;
        this.pid = cartItemEntity.getProduct().getPid();
        this.name = cartItemEntity.getProduct().getName();
        this.description = cartItemEntity.getProduct().getDescription();
        this.imageUrl = cartItemEntity.getProduct().getImageUrl();
        this.price = cartItemEntity.getProduct().getPrice();
        this.stock = cartItemEntity.getProduct().getStock();
        this.quantity = cartItemEntity.getQuantity();
        setSubtotal();
    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public TransactionEntity getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionEntity transaction) {
        this.transaction = transaction;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public void setSubtotal() {
        this.subtotal = this.price.multiply(BigDecimal.valueOf(this.quantity));
    }
}
