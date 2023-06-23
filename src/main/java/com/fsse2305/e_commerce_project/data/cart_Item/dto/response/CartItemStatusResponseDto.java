package com.fsse2305.e_commerce_project.data.cart_Item.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartItemStatusResponseDto {
    @JsonProperty("result")
    private String result;

    public CartItemStatusResponseDto() {
        result = "SUCCESS";
    }
}
