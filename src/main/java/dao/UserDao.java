package dao;

import models.Auto;
import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;
import java.util.List;
/**
 * Далее нам понадобится класс UserDAO.
 * (По-хорошему, программировать нужно через интерфейсы — создать интерфейс UserDAO и отдельно его реализацию UserDAOImpl, но для сокращения объема кода я опущу это.
 *
 * Не делайте так в реальных проектах!).
 *
 * DAO (data access object) — один из наиболее распространенных паттернов проектирования, "Доступ к данным".
 * Его смысл прост — создать в приложении слой, который отвечает только за доступ к данным, и больше ни за что.
 * Достать данные из БД, обновить данные, удалить данные — и все.
 * Почитайте про DAO подробнее, в работе будете пользоваться ими постоянно.
 * Что же умеет наш класс UserDao?
 * Собственно, как и все DAO, он умеет только работать с данными.
 * Найти юзера по id, обновить его данные, удалить его, вытащить из БД список всех юзеров или сохранить в БД нового юзера — вот весь его функционал.
 */
public class UserDao{
    public User findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    public void save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public Auto findAutoById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.close();
        return session.get(Auto.class, id);
    }

    public List<User> findAll() {
        List<User> users = (List<User>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User").list();
        return users;
    }
}
/*
Методы UserDao похожи друг на друга.
В большинстве из них мы получаем объект Session (сессия соединения с нашей БД) с помощью
нашей Фабрики Сессий, создаем в рамках этой сессии одиночную транзакцию, выполняем необходимые преобразования
данных, сохраняем результат транзакции в БД и закрываем сессию. Сами методы тоже, как видите, довольно просты.

Именно DAO — "сердце" нашего приложения. Однако, мы не будем создавать DAO напрямую и вызывать его методы в нашем методе main().

Вся логика будет перемещена в класс UserService.
 */