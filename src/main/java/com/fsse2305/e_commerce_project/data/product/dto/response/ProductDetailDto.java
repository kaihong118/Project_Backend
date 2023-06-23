package com.fsse2305.e_commerce_project.data.product.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2305.e_commerce_project.data.product.domainObject.ProductDetailData;

import java.math.BigDecimal;

public class ProductDetailDto {
    @JsonProperty("pid")
    private Integer pid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("stock")
    private Integer stock;

    public ProductDetailDto(ProductDetailData productDetailData) {
        this.pid = productDetailData.getPid();
        this.name = productDetailData.getName();
        this.description = productDetailData.getDescription();
        this.imageUrl = productDetailData.getImageUrl();
        this.price = productDetailData.getPrice();
        this.stock = productDetailData.getStock();
    }
}
