package com.fsse2305.e_commerce_project.service;

import com.fsse2305.e_commerce_project.data.transaction.domainObject.TransactionDetailData;
import com.fsse2305.e_commerce_project.data.user.domainObject.FirebaseUserData;

public interface TransactionService {
    TransactionDetailData createTransaction(FirebaseUserData firebaseUserData);

    TransactionDetailData getTransactionByTid(FirebaseUserData firebaseUserData, Integer tid);

    TransactionDetailData updateTransactionStatusByTid(FirebaseUserData firebaseUserData, Integer tid);

    TransactionDetailData completeTransactionByTid(FirebaseUserData firebaseUserData, Integer tid);
}
