package com.fsse2305.e_commerce_project.service.impl;

import com.fsse2305.e_commerce_project.data.cart_Item.entity.CartItemEntity;
import com.fsse2305.e_commerce_project.data.transaction.entity.TransactionEntity;
import com.fsse2305.e_commerce_project.data.transaction_product.entity.TransactionProductEntity;
import com.fsse2305.e_commerce_project.repository.TransactionProductRepository;
import com.fsse2305.e_commerce_project.service.TransactionProductService;
import org.springframework.stereotype.Service;

@Service
public class TransactionProductServiceImpl implements TransactionProductService {
    private final TransactionProductRepository transactionProductRepository;

    public TransactionProductServiceImpl(TransactionProductRepository transactionProductRepository) {
        this.transactionProductRepository = transactionProductRepository;
    }

    @Override
    public TransactionProductEntity createTransactionProduct(TransactionEntity transactionEntity, CartItemEntity cartItemEntity) {
        TransactionProductEntity transactionProductEntity = new TransactionProductEntity(transactionEntity, cartItemEntity);
        return transactionProductRepository.save(transactionProductEntity);
    }
}
