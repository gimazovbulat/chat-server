package myApp.dao.repositories;

import myApp.config.Component;
import myApp.models.Product;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends Component {
    void place(String name, float price);
    void delete(String productName);
    List<Product> getAll(int page, int size);
    Optional<Product> getByName(String name);
    Optional<Product> getById(Integer id);
    void setConnection(Connection connection);

}
