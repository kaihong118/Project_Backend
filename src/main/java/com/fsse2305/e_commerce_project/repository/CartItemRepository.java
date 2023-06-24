package com.fsse2305.e_commerce_project.repository;

import com.fsse2305.e_commerce_project.data.cart_Item.entity.CartItemEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends CrudRepository<CartItemEntity, Integer> {
    @Query(value = "SELECT * FROM cart_item WHERE uid = ?1 AND pid = ?2", nativeQuery = true)
    Optional<CartItemEntity> findCartItemByUserUidAndProductPid(Integer uid, Integer pid);

    @Query(value = "SELECT * FROM cart_item WHERE uid = ?1", nativeQuery = true)
    List<CartItemEntity> findCartItemByUserUid(Integer uid);
}

