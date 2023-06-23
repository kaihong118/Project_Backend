package com.fsse2305.e_commerce_project.data.product.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2305.e_commerce_project.data.product.domainObject.ProductDetailData;

import java.math.BigDecimal;

public class AllProductDetailDto {
    @JsonProperty("pid")
    private Integer pid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("has_stock")
    private boolean hasStock;

    public AllProductDetailDto(ProductDetailData productDetailData) {
        this.pid = productDetailData.getPid();
        this.name = productDetailData.getName();
        this.imageUrl = productDetailData.getImageUrl();
        this.price = productDetailData.getPrice();
        this.hasStock = productDetailData.isHasStock();
    }
}
