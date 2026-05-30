package somepackage;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class Injector {
    private Properties properties;

    public Injector() {
        properties = new Properties();
        // Загружаем файл config.properties из папки resources
        try (InputStream input = Injector.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Не удалось найти файл config.properties");
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    //метод для динамической подстановки
    public void setProperty(String key, String value) {
        this.properties.setProperty(key, value);
    }
    public <T> T inject(T object) {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                String interfaceName = field.getType().getName();
                String implementationClassName = properties.getProperty(interfaceName);

                if (implementationClassName != null) {
                    try {
                        Class<?> implClass = Class.forName(implementationClassName);
                        Object implInstance = implClass.getDeclaredConstructor().newInstance();

                        field.setAccessible(true); // Разрешаем доступ к private полю
                        field.set(object, implInstance); // Внедряем объект
                    } catch (Exception e) {
                        throw new RuntimeException("Ошибка внедрения зависимости для поля: " + field.getName(), e);
                    }
                }
            }
        }
        return object;
    }
}