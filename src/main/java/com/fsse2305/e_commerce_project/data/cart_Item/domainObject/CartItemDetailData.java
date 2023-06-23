package com.fsse2305.e_commerce_project.data.cart_Item.domainObject;

import com.fsse2305.e_commerce_project.data.cart_Item.entity.CartItemEntity;
import com.fsse2305.e_commerce_project.data.product.domainObject.ProductDetailData;
import com.fsse2305.e_commerce_project.data.user.domainObject.UserDetailData;

public class CartItemDetailData {
    private Integer cid;
    private ProductDetailData product;
    private UserDetailData user;
    private Integer quantity;

    public CartItemDetailData(CartItemEntity cartItemEntity) {
        this.cid = cartItemEntity.getCid();
        this.product = new ProductDetailData(cartItemEntity.getProduct());
        this.user = new UserDetailData(cartItemEntity.getUser());
        this.quantity = cartItemEntity.getQuantity();
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public ProductDetailData getProduct() {
        return product;
    }

    public void setProduct(ProductDetailData product) {
        this.product = product;
    }

    public UserDetailData getUser() {
        return user;
    }

    public void setUser(UserDetailData user) {
        this.user = user;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
