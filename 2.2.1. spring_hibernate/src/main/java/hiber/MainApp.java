package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      Car lada = new Car("Lada", 10);
      Car honda = new Car("Honda", 5);
      Car ford = new Car("Ford", 3);
      Car volga = new Car("Volga", 2);

      UserService userService = context.getBean(UserService.class);
      userService.add(new User("User1", "Lastname1", "user1@mail.ru", lada));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", honda));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", ford));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", volga));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.print(user.toString());
      }

      User findUser = userService.findUserByCar("Ford", 3);
      System.out.print(findUser.toString());

      context.close();
   }
}
