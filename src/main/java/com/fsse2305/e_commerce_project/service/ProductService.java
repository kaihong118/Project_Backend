package com.fsse2305.e_commerce_project.service;

import com.fsse2305.e_commerce_project.data.product.domainObject.ProductDetailData;

import java.util.List;

public interface ProductService {
    List<ProductDetailData> getAllProduct();

    ProductDetailData getProductByPid(Integer pid);
}
