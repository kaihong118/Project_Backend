package com.fsse2305.e_commerce_project.service.impl;

import com.fsse2305.e_commerce_project.data.cart_Item.entity.CartItemEntity;
import com.fsse2305.e_commerce_project.data.transaction.domainObject.TransactionDetailData;
import com.fsse2305.e_commerce_project.data.transaction.entity.TransactionEntity;
import com.fsse2305.e_commerce_project.data.transaction_product.entity.TransactionProductEntity;
import com.fsse2305.e_commerce_project.data.user.domainObject.FirebaseUserData;
import com.fsse2305.e_commerce_project.data.user.entity.UserEntity;
import com.fsse2305.e_commerce_project.exception.cartItem.NoStockException;
import com.fsse2305.e_commerce_project.exception.transaction.CreateTransactionException;
import com.fsse2305.e_commerce_project.repository.TransactionRepository;
import com.fsse2305.e_commerce_project.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
    private final UserService userService;
    private final CartItemService cartItemService;
    private final TransactionProductService transactionProductService;
    private final ProductService productService;
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(UserService userService,
                                  CartItemService cartItemService,
                                  TransactionProductService transactionProductService,
                                  ProductService productService,
                                  TransactionRepository transactionRepository) {
        this.userService = userService;
        this.cartItemService = cartItemService;
        this.transactionProductService = transactionProductService;
        this.productService = productService;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionDetailData createTransaction(FirebaseUserData firebaseUserData) {
        UserEntity currentUser = getUserEntity(firebaseUserData);
        List<CartItemEntity> cartItemEntityList = cartItemService.findCartItemByUser(currentUser);

        if(cartItemEntityList.isEmpty()) {
            logger.warn("Create Transaction API: Cart Is Empty");
            throw new CreateTransactionException();
        }

        for(CartItemEntity cartItemEntity : cartItemEntityList) {
            if(!cartItemService.checkStock(cartItemEntity.getProduct(), cartItemEntity.getQuantity())) {
                logger.warn("Create Transaction API: No Stock Available " + cartItemEntity.getProduct().getPid());
                throw new NoStockException();
            }
        }

        TransactionEntity transactionEntity = new TransactionEntity(currentUser, cartItemEntityList);
        transactionRepository.save(transactionEntity);

        for(CartItemEntity cartItemEntity : cartItemEntityList) {
            TransactionProductEntity transactionProductEntity = transactionProductService.createTransactionProduct(transactionEntity, cartItemEntity);
            transactionEntity.setTotal(transactionProductEntity);
            transactionEntity.getTransactionProductEntityList().add(transactionProductEntity);
        }

        transactionRepository.save(transactionEntity);
        return new TransactionDetailData(transactionEntity);
    }

    public UserEntity getUserEntity(FirebaseUserData firebaseUserData) {
        return userService.getUserByFirebaseUserDetail(firebaseUserData);
    }
}
