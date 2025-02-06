package task;

import user.User;

public class TaskBuilder {
  protected String title;
  protected String description;
  protected boolean done;
  protected User creator;

  public TaskBuilder setTitle(String title) {
    this.title = title;
    return this;
  }

  public TaskBuilder setDescription(String description) {
    this.description = description;
    return this;
  }

  public TaskBuilder setDone(boolean done) {
    this.done = done;
    return this;
  }

  public TaskBuilder setCreator(User creator) {
    this.creator = creator;
    return this;
  }

  public Task build() {
    return new Task(title, description, done, creator);
  }
}
