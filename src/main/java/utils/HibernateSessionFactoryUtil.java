package utils;

import models.Auto;
import models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
После создания модели данных пора научить нашу программу выполнять с этими данными операции в БД.
Начнем с утилитного класса HibernateSessionFactoryUtil.
У него всего одна задача — создавать для нашего приложения фабрику сессий для работы с БД (привет, паттерн "Фабрика!").
Больше он ничего не умеет.
 В этом классе мы создаем новый объект конфигураций Configuration, и передаем ему те классы, которые он должен воспринимать как сущности — User и Auto.
 Обратите внимание на метод configuration.getProperties().
 Properties — это параметры для работы hibernate, указанные в специальном файле hibernate.cfg.xml.
 */
public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure(); //Hibernate.cfg.xml зачитывается здесь
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Auto.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}
