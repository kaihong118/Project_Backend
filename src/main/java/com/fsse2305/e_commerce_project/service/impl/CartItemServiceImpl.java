package com.fsse2305.e_commerce_project.service.impl;

import com.fsse2305.e_commerce_project.data.cart_Item.domainObject.CartItemDetailData;
import com.fsse2305.e_commerce_project.data.cart_Item.entity.CartItemEntity;
import com.fsse2305.e_commerce_project.data.product.entity.ProductEntity;
import com.fsse2305.e_commerce_project.data.user.domainObject.FirebaseUserData;
import com.fsse2305.e_commerce_project.data.user.entity.UserEntity;
import com.fsse2305.e_commerce_project.exception.cartItem.NoStockException;
import com.fsse2305.e_commerce_project.exception.product.ProductNotFoundException;
import com.fsse2305.e_commerce_project.repository.CartItemRepository;
import com.fsse2305.e_commerce_project.service.CartItemService;
import com.fsse2305.e_commerce_project.service.ProductService;
import com.fsse2305.e_commerce_project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    Logger logger = LoggerFactory.getLogger(CartItemServiceImpl.class);
    private final CartItemRepository cartItemRepository;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository, UserService userService, ProductService productService) {
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public CartItemDetailData AddCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity) {
        try {
            UserEntity currentUser = getUserEntity(firebaseUserData);
            ProductEntity addItem = productService.findProductByPid(pid);
            Optional<CartItemEntity> optionalCartItemEntity = cartItemRepository.findCartItemByUserAndProductPid(currentUser, pid);

            if(optionalCartItemEntity.isEmpty()) {
                if(checkStock(addItem, quantity)) {
                    CartItemEntity cartItemEntity = cartItemRepository.save(new CartItemEntity(addItem, currentUser, quantity));
                    return new CartItemDetailData(cartItemEntity);
                }
                else {
                    logger.warn("Add Cart Item API: No Stock Available " + quantity);
                    throw new NoStockException();
                }
            }
            else {
                CartItemEntity cartItemEntity = optionalCartItemEntity.get();
                if(checkStock(cartItemEntity.getProduct(), (cartItemEntity.getQuantity()) + quantity)) {
                    cartItemEntity.setQuantity(cartItemEntity.getQuantity() + quantity);
                    return new CartItemDetailData(cartItemRepository.save(cartItemEntity));
                }
                else {
                    logger.warn("Add Cart Item API: No Stock Available " + quantity);
                    throw new NoStockException();
                }
            }
        }
        catch(ProductNotFoundException ex) {
            logger.warn("Add Cart Item API: Product Not Found " + pid);
            throw ex;
        }
    }

    public boolean checkStock(ProductEntity productEntity, Integer quantity) {
        return quantity <= productEntity.getStock() && productEntity.getStock() != 0;
    }

    public UserEntity getUserEntity(FirebaseUserData firebaseUserData) {
        return userService.getUserByFirebaseUserDetail(firebaseUserData);
    }
}
