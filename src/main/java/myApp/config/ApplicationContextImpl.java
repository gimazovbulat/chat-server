package myApp.config;


import org.reflections.Reflections;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ApplicationContextImpl implements ApplicationContext {
    private Map<Class, Object> components = new HashMap<>();
    private Connection connection;

    @Override
    public Connection getConnection() {
        return connection;
    }

    public void createComponents() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.println("---------------------");
        System.out.println("creating components");
        System.out.println("---------------------");

        Reflections reflections = new Reflections("myApp");
        Set<Class<? extends Component>> classes = reflections.getSubTypesOf(Component.class);
        for (Class clazz : classes) {
            if (!clazz.isInterface()) {
                Object o = Class.forName(clazz.getPackage().getName() + "." + clazz.getSimpleName()).newInstance();
                components.putIfAbsent(clazz, o);
                System.out.println("class " + clazz + ", " + "object " + o);
            }
        }
        System.out.println("---------------------");
        System.out.println("finished creating components");
        System.out.println("---------------------");

    }

    public <T> T getComponent(Class<T> componentType) throws ClassNotFoundException, IllegalAccessException {
        T t = null;
        for (Map.Entry<Class, Object> entry : components.entrySet()) {
            for (Class cl : entry.getKey().getInterfaces()) {
                if (cl.equals(componentType) || componentType.equals(entry.getKey())) {
                    t = (T) entry.getValue();
                    for (Field field : entry.getValue().getClass().getDeclaredFields()) {
                        Class fieldClass = Class.forName(field.getType().getPackage().getName() + "." + field.getType().getSimpleName());
                        System.out.println(fieldClass.getSimpleName());

                        for (Class fieldClInt : fieldClass.getInterfaces()) {
                            if (isSubComponent(fieldClInt)) {
                                field.setAccessible(true);
                                field.set(entry.getValue(), getComponent(fieldClass));
                            }
                        }
                    }
                }
            }
        }
        return t;
    }

    private boolean isSubComponent(Class clazz) {
        if (clazz.getSimpleName().equals("Component")) return true;
        while (clazz.getInterfaces().length != 0) {
            for (Class clazzz : clazz.getInterfaces()) {
                if (clazzz.getSimpleName().equals("Component")) {
                    return true;
                }
                isSubComponent(clazzz);
            }
        }
        return false;
    }


    public void createConnection(String properties) {
        try {
            Map<String, String> dbProperties = readDbProperties(properties);
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(e);
            }
            connection = DriverManager.getConnection(
                    dbProperties.get("url"),
                    dbProperties.get("username"),
                    dbProperties.get("password"));
        } catch (IOException | SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private Map<String, String> readDbProperties(String path) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(path));
        String username = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        String url = properties.getProperty("db.url");
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        map.put("url", url);
        return map;
    }

}
