package services;

import me.xdrop.jrand.JRand;
import me.xdrop.jrand.generators.basics.BoolGenerator;
import me.xdrop.jrand.generators.person.FirstnameGenerator;
import me.xdrop.jrand.generators.person.LastnameGenerator;
import models.task.Task;
import models.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DatabaseSeeder {
  public void seed() {
    DatabaseAccess dba = DatabaseAccess.getInstance();
    List<User> users = generateUsers(10);
    users.forEach(dba::addUser);

    List<Task> tasks = generateTasks(20, users);
    tasks.forEach(dba::addTask);
  }

  private List<User> generateUsers(int number) {
    FirstnameGenerator firstnameGenerator = JRand.firstname();
    LastnameGenerator lastnameGenerator = JRand.lastname();
    List<User> users = new ArrayList<>();

    for (int i = 0; i < number; i++) {
      User user = new User(firstnameGenerator.gen(), lastnameGenerator.gen());
      users.add(user);
    }

    return users;
  }

  private List<Task> generateTasks(int number, List<User> users) {
    var wordGenerator = JRand.word();
    var paragraphGenerator = JRand.paragraph();
    BoolGenerator boolGenerator = JRand.bool();
    List<Task> tasks = new ArrayList<>();

    for (int i = 0; i < number; i++) {
      Task t = new Task(
          wordGenerator.gen(),
          paragraphGenerator.gen(),
          boolGenerator.likelihood(50).gen(),
          users.get(new Random().nextInt(users.size()))
      );

      tasks.add(t);
    }

    return tasks;
  }
}
