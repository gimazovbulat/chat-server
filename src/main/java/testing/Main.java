package testing;


import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        TestClass testClass = new TestClass();
        Field[] fields = testClass.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getClass().getSimpleName());
            System.out.println(field.getType().toString().substring(10));

         /*   Class clazz = Class.forName(field.getType().toString().substring(10));
            System.out.println(clazz.getCanonicalName());
            Component componentClazz = (Component) clazz.newInstance();
            testClass.setTestClass2((TestInt2)componentClazz);
            System.out.println(testClass.toString());*/


        }
    }
}
