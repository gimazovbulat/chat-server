package myApp.dao.repositoriesImpl;

import lombok.NoArgsConstructor;
import lombok.Setter;
import myApp.dao.repositories.UserRolesRepository;
import lombok.AllArgsConstructor;
import myApp.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
public class UserRolesRepositoryImpl implements UserRolesRepository {
    @Setter
    private Connection connection;

    @Override
    public Optional<User.Role> findRole(Integer userId) {
        String sql = "SELECT role FROM javalab_scheme.user_roles WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) return Optional.of(new User.Role(resultSet.getString("role")));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }
}
