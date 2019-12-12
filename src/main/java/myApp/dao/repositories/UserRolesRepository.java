package myApp.dao.repositories;

import myApp.config.Component;
import myApp.models.User;

import java.sql.Connection;
import java.util.Optional;

public interface UserRolesRepository extends Component {
    Optional<User.Role> findRole(Integer userId);
    void setConnection(Connection connection);

}
