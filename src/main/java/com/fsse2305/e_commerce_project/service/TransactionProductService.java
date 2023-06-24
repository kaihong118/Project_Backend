package com.fsse2305.e_commerce_project.service;

import com.fsse2305.e_commerce_project.data.cart_Item.entity.CartItemEntity;
import com.fsse2305.e_commerce_project.data.transaction.entity.TransactionEntity;
import com.fsse2305.e_commerce_project.data.transaction_product.entity.TransactionProductEntity;

public interface TransactionProductService {
    TransactionProductEntity createTransactionProduct(TransactionEntity transactionEntity, CartItemEntity cartItemEntity);
}
