package com.fsse2305.e_commerce_project.service.impl;

import com.fsse2305.e_commerce_project.data.product.domainObject.ProductDetailData;
import com.fsse2305.e_commerce_project.data.product.entity.ProductEntity;
import com.fsse2305.e_commerce_project.data.transaction.entity.TransactionEntity;
import com.fsse2305.e_commerce_project.data.transaction_product.entity.TransactionProductEntity;
import com.fsse2305.e_commerce_project.exception.product.ProductNotFoundException;
import com.fsse2305.e_commerce_project.exception.product.UpdateStockException;
import com.fsse2305.e_commerce_project.repository.ProductRepository;
import com.fsse2305.e_commerce_project.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    Logger logger = LoggerFactory.getLogger(ProductServiceImp.class);
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDetailData> getAllProduct() {
        List<ProductDetailData> productDetailDataList = new ArrayList<>();

        for(ProductEntity productEntity : productRepository.findAll()) {
            productDetailDataList.add(new ProductDetailData(productEntity));
        }
        return productDetailDataList;
    }

    @Override
    public ProductDetailData getProductByPid(Integer pid) {
        try {
            return new ProductDetailData(findProductByPid(pid));
        }
        catch(ProductNotFoundException ex) {
            logger.warn("Get Product By Pid API: Product Not Found " + pid);
            throw ex;
        }
    }

    @Override
    public ProductEntity findProductByPid(Integer pid) {
        ProductEntity productEntity = productRepository.findProductByPid(pid);

        if(productEntity == null) {
            throw new ProductNotFoundException();
        }
        return productEntity;
    }

    @Override
    public void updateProductStock(TransactionEntity transactionEntity) {
        for(TransactionProductEntity transactionProductEntity : transactionEntity.getTransactionProductEntityList()) {
            if(!findProductByPid(transactionProductEntity.getPid()).setStockByTransaction(transactionProductEntity.getQuantity())) {
                throw new UpdateStockException();
            }
        }
    }
}
