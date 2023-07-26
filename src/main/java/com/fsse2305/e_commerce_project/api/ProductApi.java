package com.fsse2305.e_commerce_project.api;

import com.fsse2305.e_commerce_project.data.product.domainObject.ProductDetailData;
import com.fsse2305.e_commerce_project.data.product.dto.response.AllProductDetailDto;
import com.fsse2305.e_commerce_project.data.product.dto.response.ProductDetailDto;
import com.fsse2305.e_commerce_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/public/product")
@CrossOrigin(origins = "http://localhost:5173/")
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

    @GetMapping("/search/{searchText}")
    public List<AllProductDetailDto> getProductBySearchText(@PathVariable(value = "searchText") String searchText) {
        List<AllProductDetailDto> productDetailDtoList = new ArrayList<>();

        for(ProductDetailData productDetailData : productService.getProductBySearchText(searchText)) {
            productDetailDtoList.add(new AllProductDetailDto(productDetailData));
        }
        return productDetailDtoList;
    }
}
