package com.fsse2305.e_commerce_project.data.cart_Item.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2305.e_commerce_project.data.cart_Item.domainObject.CartItemDetailData;

import java.math.BigDecimal;

public class CartItemDetailResponseDto {
    @JsonProperty("pid")
    private Integer pid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("cart_quantity")
    private Integer quantity;

    @JsonProperty("stock")
    private Integer stock;

    public CartItemDetailResponseDto(CartItemDetailData cartItemDetailData) {
        this.pid = cartItemDetailData.getProduct().getPid();
        this.name = cartItemDetailData.getProduct().getName();
        this.imageUrl = cartItemDetailData.getProduct().getImageUrl();
        this.price = cartItemDetailData.getProduct().getPrice();
        this.quantity = cartItemDetailData.getQuantity();
        this.stock = cartItemDetailData.getProduct().getStock();
    }

}
