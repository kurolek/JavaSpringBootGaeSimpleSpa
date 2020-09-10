package org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.dao;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;
import org.springframework.stereotype.Repository;

import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.ProductModel;



import static com.googlecode.objectify.ObjectifyService.ofy;

@Repository
public class ProductDao extends AbstractDAO<ProductModel> {
    // получение из хранилища объекта типа ProductModel по имени категории

    public ProductModel read(String _title) throws Exception {
        return (ProductModel) ObjectifyService.run(
                (Work) () -> ofy().load().type(ProductModel.class)
                        .filter("title", _title) // отобрать только объекты Категория с заданным именем
                        .first() // получить только первый из найденных объектов
                        .now() // выполнить получение одного найденного объекта немедленно
        );
    }


}
