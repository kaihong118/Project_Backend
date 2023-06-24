package com.fsse2305.e_commerce_project.repository;

import com.fsse2305.e_commerce_project.data.transaction.entity.TransactionEntity;
import com.fsse2305.e_commerce_project.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
    TransactionEntity findTransactionByUserAndTid(UserEntity userEntity, Integer tid);
}
