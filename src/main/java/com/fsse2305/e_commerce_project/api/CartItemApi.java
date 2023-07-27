package com.fsse2305.e_commerce_project.api;

import com.fsse2305.e_commerce_project.config.EnvConfig;
import com.fsse2305.e_commerce_project.data.cart_Item.domainObject.CartItemDetailData;
import com.fsse2305.e_commerce_project.data.cart_Item.dto.response.CartItemDetailResponseDto;
import com.fsse2305.e_commerce_project.data.cart_Item.dto.response.CartItemStatusResponseDto;
import com.fsse2305.e_commerce_project.data.user.domainObject.FirebaseUserData;
import com.fsse2305.e_commerce_project.service.CartItemService;
import com.fsse2305.e_commerce_project.uility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = {EnvConfig.devConfig, EnvConfig.prodConfig})
public class CartItemApi {
    private CartItemService cartItemService;

    @Autowired
    public CartItemApi(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PutMapping("/{pid}/{quantity}")
    public CartItemStatusResponseDto addCartItem(JwtAuthenticationToken jwtToken,
                                                 @PathVariable(value = "pid") Integer pid,
                                                 @PathVariable(value = "quantity") Integer quantity) {
        cartItemService.AddCartItem(JwtUtil.getFirebaseUserData(jwtToken), pid, quantity);
        return new CartItemStatusResponseDto();
    }

    @GetMapping()
    public List<CartItemDetailResponseDto> getCartItemByUser(JwtAuthenticationToken jwtToken) {
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        List<CartItemDetailResponseDto> cartItemDetailResponseDtoList = new ArrayList<>();

        for(CartItemDetailData cartItemDetailData : cartItemService.getCartItemByUser(firebaseUserData)) {
            cartItemDetailResponseDtoList.add(new CartItemDetailResponseDto(cartItemDetailData));
        }
        return cartItemDetailResponseDtoList;
    }

    @PatchMapping("/{pid}/{quantity}")
    public CartItemDetailResponseDto updateCartItem(JwtAuthenticationToken jwtToken,
                                                    @PathVariable(value = "pid") Integer pid,
                                                    @PathVariable(value = "quantity") Integer quantity) {
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        return new CartItemDetailResponseDto(cartItemService.updateCartItem(firebaseUserData, pid, quantity));
    }

    @DeleteMapping("/{pid}")
    public CartItemStatusResponseDto deleteCartItem(JwtAuthenticationToken jwtToken,
                                                    @PathVariable(value = "pid") Integer pid) {
        cartItemService.deleteCartItem(JwtUtil.getFirebaseUserData(jwtToken), pid);
        return new CartItemStatusResponseDto();
    }
}
