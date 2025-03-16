package seven;

public class User {
  private final int id;
  private final String name;
  private static int nextId = 1;

  public User(String name) {
    this.id = nextId++;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }
}