package org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.dao;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;
import com.googlecode.objectify.cmd.Query;
import org.springframework.stereotype.Repository;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.CategoryModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.ProductFilterModel;
import org.tyaa.java.springboot.gae.simplespa.JavaSpringBootGaeSimpleSpa.model.ProductModel;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Repository
public class ProductObjectifyDao extends AbstractDAO<ProductModel> {
    // получение из хранилища объекта типа ProductModel по имени категории
    public ProductModel read(String _title) throws Exception {
        return (ProductModel) ObjectifyService.run(
            (Work) () -> ofy().load().type(ProductModel.class)
                .filter("title", _title) // отобрать только объекты Категория с заданным именем
                .first() // получить только первый из найденных объектов
                .now() // выполнить получение одного найденного объекта немедленно
        );
    }

    public List<ProductModel> getFiltered(ProductFilterModel filter){
        ofy().clear();
        // строим основу запроса на чтение данных их хранилища:
        // объекты типа ProductModel
        Query<ProductModel> query =
            ofy().load().type(ProductModel.class);
        return query.list();
    }
}
