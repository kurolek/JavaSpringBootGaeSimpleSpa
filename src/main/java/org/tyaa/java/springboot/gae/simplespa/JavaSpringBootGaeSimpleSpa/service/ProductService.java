package org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.dao.ProductDao;


import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.ProductModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.*;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.service.interfaces.IProductService;




@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductDao productDao;


    @Override
    public ResponseModel create(ProductModel productModel) {
        productDao.create(productModel);
        return ResponseModel.builder()
                .status(ResponseModel.SUCCESS_STATUS)
                .message(String.format("Product %s Created", productModel.getName()))
                .build();

    }

    public ResponseModel update(ProductModel productModel) {
        productDao.update(productModel);
        return ResponseModel.builder()
                .status(ResponseModel.SUCCESS_STATUS)
                .message(String.format("Category %s Updated", productModel.getName()))
                .build();
    }

    @Override
    public ResponseModel getAll() {
        return ResponseModel.builder()
                .status(ResponseModel.SUCCESS_STATUS)
                .data(productDao.read())
                .build();
    }

    public ResponseModel delete(Long id) throws IllegalAccessException, InstantiationException {
        ProductModel productModel = productDao.read(id);
        if (productModel != null){
            productDao.delete(productModel.getId());
            return ResponseModel.builder()
                    .status(ResponseModel.SUCCESS_STATUS)
                    .message(String.format("Product #%s Deleted", productModel.getName()))
                    .build();
        } else {
            return ResponseModel.builder()
                    .status(ResponseModel.FAIL_STATUS)
                    .message(String.format("Product #%d Not Found", id))
                    .build();
        }
    }
}
