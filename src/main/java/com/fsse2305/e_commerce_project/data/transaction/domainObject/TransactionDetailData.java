package com.fsse2305.e_commerce_project.data.transaction.domainObject;

import com.fsse2305.e_commerce_project.data.transaction.TransactionStatus;
import com.fsse2305.e_commerce_project.data.transaction.entity.TransactionEntity;
import com.fsse2305.e_commerce_project.data.transaction_product.domainObject.TransactionProductDetailData;
import com.fsse2305.e_commerce_project.data.transaction_product.entity.TransactionProductEntity;
import com.fsse2305.e_commerce_project.data.user.domainObject.UserDetailData;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDetailData {
    private Integer tid;
    private UserDetailData user;
    private LocalDateTime dateTime;
    private TransactionStatus status;
    private BigDecimal total;
    private List<TransactionProductDetailData> transactionProductDetailDataList = new ArrayList<>();

    public TransactionDetailData(TransactionEntity transactionEntity) {
        this.tid = transactionEntity.getTid();
        this.user = new UserDetailData(transactionEntity.getUser());
        this.dateTime = transactionEntity.getDateTime();
        this.status = transactionEntity.getStatus();
        this.total = transactionEntity.getTotal();
        setTransactionProductEntityList(transactionEntity);
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public UserDetailData getUser() {
        return user;
    }

    public void setUser(UserDetailData user) {
        this.user = user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<TransactionProductDetailData> getTransactionProductDetailDataList() {
        return transactionProductDetailDataList;
    }

    public void setTransactionProductDetailDataList(List<TransactionProductDetailData> transactionProductDetailDataList) {
        this.transactionProductDetailDataList = transactionProductDetailDataList;
    }

    public void setTransactionProductEntityList(TransactionEntity transactionEntity) {
        for(TransactionProductEntity transactionProductEntity : transactionEntity.getTransactionProductEntityList()) {
            this.transactionProductDetailDataList.add(new TransactionProductDetailData(transactionProductEntity));
        }
    }
}
