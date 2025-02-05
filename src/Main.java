import task.Task;

import java.util.ArrayList;

public class Main {
  public static void main (String[] args) {
    ArrayList<Task> tasks = new ArrayList<>();

    User steph = new User(1, "Steph");
    User hubert = new User(2, "Hubert");

    Task clean = new Task(
        1,
        "Laver la maison",
        "Laver la maison, c'est à dire passer l'aspirateur et la serpillères",
        false,
        1
    );

    Task cleanCar = new Task(
        2,
        "Laver la voiture",
        "Laver la voiture en allant à l'éléphant bleu",
        true,
        1
    );

    Task shopping = new Task(
        3,
        "Faire les courses",
        "Aller à Cora pour faire les courses. Voici la liste des aliments à acheter : \n  Pomme,\n  Dinde,\n  Pâtes," +
            "\n  Riz,\n  Pesto,\n  Pepsi,\n  Monster",
        true,
        2
    );

    tasks.add(clean);
    tasks.add(cleanCar);
    tasks.add(shopping);


    getTasks(tasks);
  }

  // LISTING TASKS
  public static void getTasks (ArrayList<Task> tasks) {
    for (Task task : tasks) {
      System.out.println(task + "\n");
    }
  }
}