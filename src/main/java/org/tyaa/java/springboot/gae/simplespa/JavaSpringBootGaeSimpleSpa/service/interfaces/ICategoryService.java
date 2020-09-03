package org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.service.interfaces;

import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.CategoryModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.ResponseModel;

public interface ICategoryService {
    ResponseModel create(CategoryModel categoryModel);
    ResponseModel update(CategoryModel categoryModel);
    ResponseModel getAll();
    ResponseModel delete(Long id) throws IllegalAccessException, InstantiationException;
}
