package myApp.dao.repositoriesImpl;

import lombok.NoArgsConstructor;
import lombok.Setter;
import myApp.dao.RowMappper;
import myApp.dao.repositories.UsersRepository;
import myApp.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@NoArgsConstructor
public class UsersRepositoryImpl implements UsersRepository {
    @Setter
    private Connection connection;
    private PasswordEncoder encoder;

    public UsersRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    private RowMappper<User> rowMappper = row -> {
        Integer id = row.getInt("id");
        String email = row.getString("email");
        String password = row.getString("password");
        String nickname = row.getString("nickname");
        User.Role role = new User.Role(row.getString("role"));
        return new User(email, password, id, nickname, role);
    };

    public Optional<User> checkLogin(String login, String password) {
        String sql = "SELECT * FROM javalab_scheme.users2 u2 JOIN javalab_scheme.user_roles r " +
                "ON r.user_id = u2.id WHERE email = ?";
        encoder = new BCryptPasswordEncoder();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, login);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                User user = rowMappper.maprow(resultSet);
                if (encoder.matches(password, user.getPassword()))
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }

    public Optional<User> findById(Integer id) {
        String sql = "SELECT * FROM javalab_scheme.users2 WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                return Optional.of(rowMappper.maprow(resultSet));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }
    @Override
    public void purchase(int userId, int productId) {
        String sql = "INSERT INTO javalab_scheme.purchases (user_id, product_id) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) throw new SQLException();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

}
