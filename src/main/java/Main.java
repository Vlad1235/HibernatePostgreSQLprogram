import models.Auto;
import models.User;
import services.UserService;

import java.sql.SQLException;
/**
 * создадим в методе main() пользователя, машины для него, свяжем их друг с другом и сохраним в БД.
 */
public class Main {

    public static void main(String[] args) throws SQLException {
            UserService userService = new UserService();
            User user = new User("Masha", 26);
            userService.saveUser(user);
            Auto ferrari = new Auto("Ferrari", "red");
            ferrari.setUser(user);
            user.addAuto(ferrari);
            Auto ford = new Auto("Ford", "black");
            ford.setUser(user);
            user.addAuto(ford);
            User user2 = new User("Dasha",25);
            userService.saveUser(user2);
            Auto mitsubishi = new Auto("Misubishi","blue");
            mitsubishi.setUser(user2);
            user.addAuto(mitsubishi);
            userService.updateUser(user);
            user.setName("Sasha");
            userService.updateUser(user);
            userService.deleteUser(user);


    }
}
