package com.fsse2305.e_commerce_project.repository;

import com.fsse2305.e_commerce_project.data.product.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    ProductEntity findProductByPid(Integer pid);
}
