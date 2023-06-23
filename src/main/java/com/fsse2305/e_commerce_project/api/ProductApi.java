package com.fsse2305.e_commerce_project.api;

import com.fsse2305.e_commerce_project.data.product.domainObject.ProductDetailData;
import com.fsse2305.e_commerce_project.data.product.dto.response.AllProductDetailDto;
import com.fsse2305.e_commerce_project.data.product.dto.response.ProductDetailDto;
import com.fsse2305.e_commerce_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/public/product")
public class ProductApi {
    private final ProductService productService;

    @Autowired
    public ProductApi(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<AllProductDetailDto> getAllProduct() {
        List<AllProductDetailDto> productDetailDtoList = new ArrayList<>();

        for(ProductDetailData productDetailData : productService.getAllProduct()) {
            productDetailDtoList.add(new AllProductDetailDto(productDetailData));
        }
        return productDetailDtoList;
    }

    @GetMapping("/{id}")
    public ProductDetailDto getProductByPid(@PathVariable(value = "id") Integer pid) {
        return new ProductDetailDto(productService.getProductByPid(pid));
    }
}
