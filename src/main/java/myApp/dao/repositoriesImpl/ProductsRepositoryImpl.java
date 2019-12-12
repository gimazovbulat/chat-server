package myApp.dao.repositoriesImpl;

import lombok.NoArgsConstructor;
import lombok.Setter;
import myApp.dao.RowMappper;
import myApp.dao.repositories.ProductsRepository;
import lombok.AllArgsConstructor;
import myApp.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
public class ProductsRepositoryImpl implements ProductsRepository {
    @Setter
    private Connection connection;

    public ProductsRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    private RowMappper<Product> productMapper = row -> {
        Integer id = row.getInt("id");
        String name = row.getString("name");
        Float price = row.getFloat("price");
        Boolean deleted = row.getBoolean("deleted");
        return new Product(id, name, price, deleted);
    };

    @Override
    public void place(String name, float price) {
        String sql = "INSERT INTO javalab_scheme.products (name, price, deleted) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setFloat(2, price);
            ps.setBoolean(3, false);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) throw new SQLException();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void delete(String productName) {
        String sql = "UPDATE javalab_scheme.products SET deleted = ? WHERE name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setBoolean(1, true);
            ps.setString(2, productName);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) throw new SQLException();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Product> getAll(int size, int page) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM javalab_scheme.products WHERE deleted = false LIMIT ? OFFSET ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, size);
            ps.setInt(2, (page - 1) * size);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) products.add(productMapper.maprow(resultSet));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        if (products.size() != 0) return products;
        return Collections.emptyList();
    }

    @Override
    public Optional<Product> getByName(String name) {
        String sql = "SELECT * FROM  javalab_scheme.products WHERE name = ? AND deleted = false";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) return Optional.of(productMapper.maprow(resultSet));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Product> getById(Integer id) {
        String sql = "SELECT * FROM  javalab_scheme.products WHERE id = ? AND deleted = false";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) return Optional.of(productMapper.maprow(resultSet));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }
}
