package com.fsse2305.e_commerce_project.api;

import com.fsse2305.e_commerce_project.data.cart_Item.domainObject.CartItemDetailData;
import com.fsse2305.e_commerce_project.data.cart_Item.dto.response.CartItemStatusDto;
import com.fsse2305.e_commerce_project.service.CartItemService;
import com.fsse2305.e_commerce_project.uility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartItemApi {
    private CartItemService cartItemService;

    @Autowired
    public CartItemApi(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PutMapping("/{pid}/{quantity}")
    public CartItemStatusDto addCartItem(JwtAuthenticationToken jwtToken,
                                         @PathVariable(value = "pid") Integer pid,
                                         @PathVariable(value = "quantity") Integer quantity) {
        CartItemDetailData cartItemDetailData = cartItemService.AddCartItem(JwtUtil.getFirebaseUserData(jwtToken), pid, quantity);
        return new CartItemStatusDto();
    }
}
