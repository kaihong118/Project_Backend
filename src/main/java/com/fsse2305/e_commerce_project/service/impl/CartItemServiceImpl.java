package com.fsse2305.e_commerce_project.service.impl;

import com.fsse2305.e_commerce_project.data.cart_Item.domainObject.CartItemDetailData;
import com.fsse2305.e_commerce_project.data.cart_Item.entity.CartItemEntity;
import com.fsse2305.e_commerce_project.data.product.entity.ProductEntity;
import com.fsse2305.e_commerce_project.data.user.domainObject.FirebaseUserData;
import com.fsse2305.e_commerce_project.data.user.entity.UserEntity;
import com.fsse2305.e_commerce_project.exception.cartItem.CartItemNotFoundException;
import com.fsse2305.e_commerce_project.exception.cartItem.CartItemQuantityException;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
            Optional<CartItemEntity> optionalCartItemEntity = cartItemRepository.findCartItemByUserUidAndProductPid(currentUser.getUid(), pid);

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

    @Override
    public List<CartItemDetailData> getCartItemByUser(FirebaseUserData firebaseUserData) {
        UserEntity currentUser = getUserEntity(firebaseUserData);
        List<CartItemDetailData> cartItemDetailDataList = new ArrayList<>();

        for(CartItemEntity cartItemEntity : findCartItemByUser(currentUser)) {
            cartItemDetailDataList.add(new CartItemDetailData(cartItemEntity));
        }
        return cartItemDetailDataList;
    }

    @Override
    @Transactional
    public CartItemDetailData updateCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity) {
        UserEntity currentUser = getUserEntity(firebaseUserData);
        Optional<CartItemEntity> optionalCartItemEntity = cartItemRepository.findCartItemByUserUidAndProductPid(currentUser.getUid(), pid);

        if(optionalCartItemEntity.isEmpty()) {
            logger.warn("Update Cart Item API: Cart Item Not Found " + pid);
            throw new CartItemNotFoundException();
        }
        else {
            CartItemEntity cartItemEntity = optionalCartItemEntity.get();
            if(quantity < 0) {
                logger.warn("Update Cart Item Quantity API: Quantity is less than 0 " + quantity);
                throw new CartItemQuantityException();
            }
            if(quantity == 0) {
                cartItemRepository.delete(cartItemEntity);
                cartItemEntity.setQuantity(0);
                return new CartItemDetailData(cartItemEntity);
            }
            if(checkStock(cartItemEntity.getProduct(), quantity)) {
                cartItemEntity.setQuantity(quantity);
                cartItemRepository.save(cartItemEntity);
                return new CartItemDetailData(cartItemEntity);
            }
            else {
                logger.warn("Update Cart Item API: No Stock Available " + quantity);
                throw new NoStockException();
            }
        }
    }

    @Override
    @Transactional
    public CartItemDetailData deleteCartItem(FirebaseUserData firebaseUserData, Integer pid) {
        UserEntity currentUser = getUserEntity(firebaseUserData);
        Optional<CartItemEntity> optionalCartItemEntity = cartItemRepository.findCartItemByUserUidAndProductPid(currentUser.getUid(), pid);

        if(optionalCartItemEntity.isEmpty()) {
            logger.warn("Delete Cart Item API: Cart Item Not Found " + pid);
            throw new CartItemNotFoundException();
        }
        CartItemEntity cartItemEntity = optionalCartItemEntity.get();
        cartItemRepository.delete(cartItemEntity);
        return new CartItemDetailData(cartItemEntity);
    }

    @Override
    public List<CartItemEntity> findCartItemByUser(UserEntity userEntity) {
        return cartItemRepository.findCartItemByUserUid(userEntity.getUid());
    }

    @Override
    public boolean checkStock(ProductEntity productEntity, Integer quantity) {
        return quantity <= productEntity.getStock() && productEntity.getStock() != 0;
    }

    @Override
    public void emptyCartItemByUser(UserEntity userEntity) {
        cartItemRepository.deleteAll(findCartItemByUser(userEntity));
    }

    public UserEntity getUserEntity(FirebaseUserData firebaseUserData) {
        return userService.getUserByFirebaseUserDetail(firebaseUserData);
    }
}
