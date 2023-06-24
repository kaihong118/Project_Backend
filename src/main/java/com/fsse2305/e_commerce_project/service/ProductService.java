package com.fsse2305.e_commerce_project.service;

import com.fsse2305.e_commerce_project.data.product.domainObject.ProductDetailData;
import com.fsse2305.e_commerce_project.data.product.entity.ProductEntity;
import com.fsse2305.e_commerce_project.data.transaction.entity.TransactionEntity;

import java.util.List;

public interface ProductService {
    List<ProductDetailData> getAllProduct();

    ProductDetailData getProductByPid(Integer pid);

    ProductEntity findProductByPid(Integer pid);

    void updateProductStock(TransactionEntity transactionEntity);
}
