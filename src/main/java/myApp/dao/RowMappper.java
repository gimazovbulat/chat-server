package myApp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMappper<T> {
    T maprow(ResultSet row) throws SQLException;
}
