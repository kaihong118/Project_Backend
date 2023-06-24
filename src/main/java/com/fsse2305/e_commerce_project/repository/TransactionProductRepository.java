package com.fsse2305.e_commerce_project.repository;

import com.fsse2305.e_commerce_project.data.transaction_product.entity.TransactionProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionProductRepository extends CrudRepository<TransactionProductEntity, Integer> {
}
