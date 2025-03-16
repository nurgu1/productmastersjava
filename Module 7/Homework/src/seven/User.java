package seven;

import java.io.Serializable; // Add this import
import java.util.Objects;

public class User implements Serializable { // Implement Serializable
  private static final long serialVersionUID = 1L; // Add a serialVersionUID
  private final int id; // Уникальный ID пользователя
  private final String name; // Имя пользователя
  private static int nextId = 1; // Статическая переменная для генерации уникальных ID

  // Конструктор
  public User(String name) {
    this.id = nextId++;
    this.name = name;
  }


  // Получить имя пользователя
  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return String.format("User{id=%d, name='%s'}", id, name);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return id == user.id && name.equals(user.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}