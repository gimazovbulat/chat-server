package myApp.config;

import java.sql.Connection;

public interface ApplicationContext {
    void createComponents() throws ClassNotFoundException, IllegalAccessException, InstantiationException;

    <T> T getComponent(Class<T> componentType) throws ClassNotFoundException, IllegalAccessException;

    void createConnection(String properties);
    Connection getConnection();
}
