package com.fsse2305.e_commerce_project.data.transaction_product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2305.e_commerce_project.data.product.dto.response.ProductDetailDto;
import com.fsse2305.e_commerce_project.data.transaction_product.domainObject.TransactionProductDetailData;

import java.math.BigDecimal;

public class TransactionProductDetailDto {
    @JsonProperty("tpid")
    private Integer tpid;

    @JsonProperty("product")
    private ProductDetailDto product;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("subtotal")
    private BigDecimal subtotal;

    public TransactionProductDetailDto(TransactionProductDetailData transactionProductDetailData) {
        this.tpid = transactionProductDetailData.getTpid();
        this.product = new ProductDetailDto(transactionProductDetailData);
        this.quantity = transactionProductDetailData.getQuantity();
        this.subtotal = transactionProductDetailData.getSubtotal();
    }
}
