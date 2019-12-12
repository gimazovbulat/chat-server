package myApp.dao.repositories;

import myApp.config.Component;
import myApp.models.User;

import java.sql.Connection;
import java.util.Optional;

public interface UsersRepository extends Component {
    void purchase(int userId, int productId);
    Optional<User> checkLogin(String login, String password);
    Optional<User> findById(Integer id);
    void setConnection(Connection connection);

}
