package com.fsse2305.e_commerce_project.data.transaction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2305.e_commerce_project.data.transaction.TransactionStatus;
import com.fsse2305.e_commerce_project.data.transaction.domainObject.TransactionDetailData;
import com.fsse2305.e_commerce_project.data.transaction_product.domainObject.TransactionProductDetailData;
import com.fsse2305.e_commerce_project.data.transaction_product.dto.TransactionProductDetailDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDetailDto {
    @JsonProperty("tid")
    private Integer tid;

    @JsonProperty("buyer_uid")
    private Integer uid;

    @JsonProperty("datetime")
    @JsonFormat(pattern = "yyyyMMdd'T'HH:mm:ss")
    private LocalDateTime dateTime;

    @JsonProperty("status")
    private TransactionStatus status;

    @JsonProperty("total")
    private BigDecimal total;

    @JsonProperty("items")
    private List<TransactionProductDetailDto> transactionProductDetailDataList = new ArrayList<>();

    public TransactionDetailDto(TransactionDetailData transactionDetailData) {
        this.tid = transactionDetailData.getTid();
        this.uid = transactionDetailData.getUser().getUid();
        this.dateTime = transactionDetailData.getDateTime();
        this.status = transactionDetailData.getStatus();
        this.total = transactionDetailData.getTotal();
        setTransactionProductDetailDataList(transactionDetailData);
    }

    public void setTransactionProductDetailDataList(TransactionDetailData transactionDetailData) {
        for(TransactionProductDetailData transactionProductDetailData : transactionDetailData.getTransactionProductDetailDataList()) {
            transactionProductDetailDataList.add(new TransactionProductDetailDto(transactionProductDetailData));
        }
    }
}
