package com.fsse2305.e_commerce_project.data.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionStatusDto {
    @JsonProperty("result")
    private String result;

    public TransactionStatusDto() {
        this.result = "SUCCESS";
    }
}
