package com.fsse2305.e_commerce_project.repository;

import com.fsse2305.e_commerce_project.data.transaction.entity.TransactionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {
    @Query(value = "SELECT * FROM transaction WHERE buyer_uid = ?1 AND tid = ?2", nativeQuery = true)
    TransactionEntity findTransactionByUserUidAndTid(Integer uid, Integer tid);
}
