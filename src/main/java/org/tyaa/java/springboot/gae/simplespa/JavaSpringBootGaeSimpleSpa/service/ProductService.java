package org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.dao.CategoryObjectifyDao;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.dao.ProductObjectifyDao;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.dao.predicate.ProductPredicatesBuilder;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.CategoryModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.ProductModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductObjectifyDao productObjectifyDao;

    @Autowired
    private CategoryObjectifyDao categoryObjectifyDao;

    public ResponseModel create(ProductModel productModel) {
        /* Optional<Category> categoryOptional
                = categoryObjectifyDao.findById(productModel.getCategoryId());
        if(categoryOptional.isPresent()){
            Product product =
                Product.builder()
                    .name(productModel.getTitle())
                    .description(productModel.getDescription())
                    .price(productModel.getPrice())
                    .quantity(productModel.getQuantity())
                    .image(productModel.getImage())
                    .category(categoryOptional.get())
                    .build();
            productObjectifyDao.save(product);
            return ResponseModel.builder()
                    .status(ResponseModel.SUCCESS_STATUS)
                    .message(String.format("Product %s Created", product.getName()))
                    .build();
        } else {
            return ResponseModel.builder()
                .status(ResponseModel.FAIL_STATUS)
                .message(String.format("Category #%d Not Found", productModel.getCategoryId()))
                .build();
        } */
        return null;
    }

    public ResponseModel update(ProductModel productModel) {
        /*Optional<Category> categoryOptional
                = categoryObjectifyDao.findById(productModel.getCategoryId());
        if(categoryOptional.isPresent()){
            Product category =
                Product.builder()
                    .id(productModel.getId())
                    .name(productModel.getTitle())
                    .description(productModel.getDescription())
                    .price(productModel.getPrice())
                    .quantity(productModel.getQuantity())
                    .category(categoryOptional.get())
                    .image(productModel.getImage())
                    .build();
            productObjectifyDao.save(category);
            // Demo Logging
            System.out.println(String.format("Category %s Updated", category.getName()));
            return ResponseModel.builder()
                    .status(ResponseModel.SUCCESS_STATUS)
                    .message(String.format("Category %s Updated", category.getName()))
                    .build();
        } else {
            return ResponseModel.builder()
                    .status(ResponseModel.FAIL_STATUS)
                    .message(String.format("Category #%d Not Found", productModel.getCategoryId()))
                    .build();
        }*/
        return null;
    }

    public ResponseModel getAll() {
        /*List<Product> products = productObjectifyDao.findAll(Sort.by("id").descending());
        List<ProductModel> productModels =
            products.stream()
                .map(p ->
                    ProductModel.builder()
                        .id(p.getId())
                        .title(p.getName())
                        .description(p.getDescription())
                        .price(p.getPrice())
                        .quantity(p.getQuantity())
                        .image(p.getImage())
                        .category(
                            CategoryModel.builder()
                                .id(p.getCategory().getId())
                                .name(p.getCategory().getName())
                                .build()
                        )
                        .build()
                )
                .collect(Collectors.toList());
        return ResponseModel.builder()
                .status(ResponseModel.SUCCESS_STATUS)
                .data(productModels)
                .build();*/
        return null;
    }

    public ResponseModel delete(Long id) {
        /*Optional<Product> productOptional = productObjectifyDao.findById(id);
        if (productOptional.isPresent()){
            Product product = productOptional.get();
            productObjectifyDao.delete(product);
            return ResponseModel.builder()
                .status(ResponseModel.SUCCESS_STATUS)
                .message(String.format("Product #%s Deleted", product.getName()))
                .build();
        } else {
            return ResponseModel.builder()
                .status(ResponseModel.FAIL_STATUS)
                .message(String.format("Product #%d Not Found", id))
                .build();
        }*/
        return null;
    }

    public ResponseModel getFiltered(ProductFilterModel filter) {
        List<ProductModel> products =
            productObjectifyDao.getFiltered(filter);
        if(filter.categories != null && filter.categories.size() > 0) {
            products.removeIf((p) -> {
                boolean categoryIdAbsents = true;
                for (Long categoryId : filter.categories) {
                    if(p.getCategoryId().equals(categoryId)) {
                        categoryIdAbsents = false;
                        break;
                    }
                }
                return categoryIdAbsents;
            });
        }
        return ResponseModel.builder()
                .status(ResponseModel.SUCCESS_STATUS)
                .data(products)
                .build();
    }

    // поиск отфильтрованного и отсортированного списка товаров
    // на основе запросов query dsl
    public ResponseModel search(ProductSearchModel searchModel) {
        List<ProductModel> products = null;
        if (searchModel.searchString != null && !searchModel.searchString.isEmpty()) {
            ProductPredicatesBuilder builder = new ProductPredicatesBuilder();
            // разбиение значения http-параметра search
            // на отдельные выражения условий фильтрации
            Pattern pattern = Pattern.compile("([\\w]+?)(:|<|>|<:|>:)([\\w\\]\\[\\,]+?);");
            Matcher matcher = pattern.matcher(searchModel.searchString + ";");
            while (matcher.find()) {
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
            }
            // TODO завершите построение метода фильтра,
            // для этого пропустите через цикл весь список критерий,
            // и для тех, в значении которых не встречается квадратная скобка -
            // примените предикат,
            // иначе - запланируйте фильтрацию по списку идентификаторов категорий прямо
            // здесь: установите флаг "по категориям" в истину,
            // и когда вызовете срабатывание объекта запроса -
            // отфильтруйте его результаты в памяти:

            /* if(filter.categories != null && filter.categories.size() > 0) {
                products.removeIf((p) -> {
                    boolean categoryIdAbsents = true;
                    for (Long categoryId : filter.categories) {
                        if(p.getCategoryId().equals(categoryId)) {
                            categoryIdAbsents = false;
                            break;
                        }
                    }
                    return categoryIdAbsents;
                });
            } */

            /* BooleanExpression expression = builder.build();
            // выполнение sql-запроса к БД
            // с набором условий фильтрации
            // и с указанием имени поля и направления сортировки
            products =
                (List<Product>) productObjectifyDao.findAll(
                    expression,
                    Sort.by(
                        searchModel.sortingDirection,
                        searchModel.orderBy
                    )
                ); */
        } else {
            products =
                productObjectifyDao.findAll(
                    Sort.by(
                        searchModel.sortingDirection,
                        searchModel.orderBy
                    )
                );
        }
        return getResponseModelFromEntities(products);
    }

    /*private ResponseModel getResponseModelFromEntities(List<Product> products) {
        List<ProductModel> productModels =
            products.stream()
                .map((p)->
                    ProductModel.builder()
                        .id(p.getId())
                        .title(p.getName())
                        .description(p.getDescription())
                        .price(p.getPrice())
                        .quantity(p.getQuantity())
                        .image(p.getImage())
                        .category(
                            CategoryModel.builder()
                                .id(p.getCategory().getId())
                                .name(p.getCategory().getName())
                                .build()
                        )
                        .build()
                )
                .collect(Collectors.toList());
        return ResponseModel.builder()
            .status(ResponseModel.SUCCESS_STATUS)
            .data(productModels)
            .build();
    }

    public ResponseModel getProductsPriceBounds() {
        Map<String, Integer> maxndMin = new LinkedHashMap<>();
        maxndMin.put("min", productObjectifyDao.findAll().stream()
                .min((p1, p2) -> p1.getPrice().subtract(p2.getPrice())
                        .toBigInteger().intValue())
                .map(product -> (int)Math.round(product.getPrice().doubleValue())).get());
        maxndMin.put("max", productObjectifyDao.findAll().stream()
                .max((p1, p2) -> p1.getPrice().subtract(p2.getPrice())
                        .toBigInteger().intValue())
                .map(product -> (int)Math.round(product.getPrice().doubleValue())).get());
        return ResponseModel.builder()
                .status(ResponseModel.SUCCESS_STATUS)
                .data(maxndMin)
                .build();
    }*/
}
