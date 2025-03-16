package seven;

import java.io.*;
import java.util.*;

public class TwitterService {
  private final List<Post> posts = new ArrayList<>();
  private static final String FILE_NAME = "posts.ser";

  // Сохранить посты в файл
  public void savePostsToFile() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
      oos.writeObject(posts);
      System.out.println("Посты сохранены в файл.");
    } catch (IOException e) {
      System.out.println("Ошибка при сохранении постов: " + e.getMessage());
    }
  }

  // Загрузить посты из файла
  @SuppressWarnings("unchecked")
  public void loadPostsFromFile() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
      posts.clear();
      posts.addAll((List<Post>) ois.readObject());
      System.out.println("Посты загружены из файла.");
    } catch (IOException | ClassNotFoundException e) {
      System.out.println("Ошибка при загрузке постов: " + e.getMessage());
    }
  }

  // Инициализация стартовых постов
  public void initializePosts() {
    if (posts.isEmpty()) { // Добавляем посты только если список пуст
      posts.add(new Post(new User("Alice"), "Привет, мир!"));
      posts.add(new Post(new User("Bob"), "Сегодня отличный день!"));
      posts.add(new Post(new User("Charlie"), "Люблю программировать на Java."));
      posts.add(new Post(new User("Иван") , "Всем привет! Это мой первый твит."));
      System.out.println("Добавлены стартовые посты.");
    }
  }

  // Создать пост
  public void createPost(User author, String content) {
    Post post = new Post(author, content);
    posts.add(post);
    System.out.println("Пост добавлен!");
  }

  // Лайкнуть пост
  public void likePost(int postId) {
    posts.stream()
            .filter(post -> post.getId() == postId)
            .findFirst()
            .ifPresentOrElse(
                    Post::like,
                    () -> System.out.println("Пост с ID " + postId + " не найден.")
            );
  }

  // Сделать репост
  public void repostPost(int postId) {
    posts.stream()
            .filter(post -> post.getId() == postId)
            .findFirst()
            .ifPresentOrElse(
                    Post::repost,
                    () -> System.out.println("Пост с ID " + postId + " не найден.")
            );
  }

  // Показать все посты
  public void showAllPosts() {
    System.out.println("Все посты:");
    posts.stream()
            .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
            .forEach(System.out::println);
  }

  // Показать популярные посты
  public void showPopularPosts(int count) {
    System.out.println("Популярные посты:");
    posts.stream()
            .sorted(Comparator.comparing(Post::getLikes).reversed())
            .limit(count)
            .forEach(System.out::println);
  }

  // Показать посты текущего пользователя
  public void showUserPosts(User user) {
    System.out.println("Мои посты:");
    posts.stream()
            .filter(post -> post.getAuthor().equals(user))
            .forEach(System.out::println);
  }

  // Добавить комментарий к посту
  public void addCommentToPost(int postId, String comment) {
    posts.stream()
            .filter(post -> post.getId() == postId)
            .findFirst()
            .ifPresentOrElse(
                    post -> post.addComment(comment),
                    () -> System.out.println("Пост с ID " + postId + " не найден.")
            );
  }

  // Удалить пост
  public void deletePost(int postId) {
    if (posts.removeIf(post -> post.getId() == postId)) {
      System.out.println("Пост с ID " + postId + " удален.");
    } else {
      System.out.println("Пост с ID " + postId + " не найден.");
    }
  }
}