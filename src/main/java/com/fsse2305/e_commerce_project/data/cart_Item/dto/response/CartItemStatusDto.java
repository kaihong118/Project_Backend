package com.fsse2305.e_commerce_project.data.cart_Item.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartItemStatusDto {
    @JsonProperty("result")
    private String result;

    public CartItemStatusDto() {
        result = "SUCCESS";
    }
}
