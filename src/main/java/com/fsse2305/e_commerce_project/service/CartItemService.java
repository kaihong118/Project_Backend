package com.fsse2305.e_commerce_project.service;

import com.fsse2305.e_commerce_project.data.cart_Item.domainObject.CartItemDetailData;
import com.fsse2305.e_commerce_project.data.cart_Item.entity.CartItemEntity;
import com.fsse2305.e_commerce_project.data.product.entity.ProductEntity;
import com.fsse2305.e_commerce_project.data.user.domainObject.FirebaseUserData;
import com.fsse2305.e_commerce_project.data.user.entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartItemService {
    CartItemDetailData AddCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);

    List<CartItemDetailData> getCartItemByUser(FirebaseUserData firebaseUserData);

    @Transactional
    CartItemDetailData updateCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);

    CartItemDetailData deleteCartItem(FirebaseUserData firebaseUserData, Integer pid);

    List<CartItemEntity> findCartItemByUser(UserEntity userEntity);

    boolean checkStock(ProductEntity productEntity, Integer quantity);

    void emptyCartItem(UserEntity userEntity);
}
