package com.fsse2305.e_commerce_project.service;

import com.fsse2305.e_commerce_project.data.cart_Item.domainObject.CartItemDetailData;
import com.fsse2305.e_commerce_project.data.user.domainObject.FirebaseUserData;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartItemService {
    CartItemDetailData AddCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);

    List<CartItemDetailData> getCartItemByUser(FirebaseUserData firebaseUserData);

    @Transactional
    CartItemDetailData updateCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);
}
