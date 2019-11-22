package services;

import dao.UserDao;
import models.Auto;
import models.User;

import java.util.List;

/**
 * Именно DAO — "сердце" нашего приложения. Однако, мы не будем создавать DAO напрямую и вызывать его методы в нашем методе main().
 *
 * Вся логика будет перемещена в класс UserService.
 *
 */
public class UserService {
    private UserDao usersDao = new UserDao();

    public UserService() {
    }

    public User findUser(int id) {
        return usersDao.findById(id);
    }

    public void saveUser(User user) {
        usersDao.save(user);
    }

    public void deleteUser(User user) {
        usersDao.delete(user);
    }

    public void updateUser(User user) {
        usersDao.update(user);
    }

    public List<User> findAllUsers() {
        return usersDao.findAll();
    }

    public Auto findAutoById(int id) {
        return usersDao.findAutoById(id);
    }

}
/*
Service — слой данных в приложении, отвечающий за выполнение бизнес-логики.
Если ваша программа должна выполнить какую-то бизнес-логику — она делает это через сервисы. Сервис содержит внутри себя UserDao, и в своих методах вызывает методы DAO.
Это может показаться вам дублированием функций (почему бы просто не вызывать методы из dao-объекта), но
при большом количестве объектов и сложной логике разбиение приложения на слои дает огромные преимущества (это good practice,
запомните эту информацию на будущее и почитайте про "слои приложения").
У нас-то в сервисе логика простая, а в реальных проектах методы сервисов будут содержать куда больше одной строки кода:)

Теперь у нас есть все нужно для работы приложения!
 */