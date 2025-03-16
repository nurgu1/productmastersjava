package seven;

import java.util.*;

public class TwitterService {
  private final List<Post> posts = new ArrayList<>();

  public void initializePosts() {
    posts.add(new Post(new User("Alice"), "Привет, мир!"));
    posts.add(new Post(new User("Bob"), "Сегодня отличный день!"));
    posts.add(new Post(new User("Charlie"), "Люблю программировать на Java."));
    System.out.println("Добавлены стартовые посты.");
  }

  public void createPost(User author, String content) {
    Post post = new Post(author, content);
    posts.add(post);
    System.out.println("Пост добавлен!");
  }

  public void likePost(int postId) {
    posts.stream()
            .filter(post -> post.getId() == postId)
            .findFirst()
            .ifPresentOrElse(
                    Post::like,
                    () -> System.out.println("Пост с ID " + postId + " не найден.")
            );
  }

  public void repostPost(int postId) {
    posts.stream()
            .filter(post -> post.getId() == postId)
            .findFirst()
            .ifPresentOrElse(
                    Post::repost,
                    () -> System.out.println("Пост с ID " + postId + " не найден.")
            );
  }

  public void showAllPosts() {
    System.out.println("Все посты:");
    posts.stream()
            .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
            .forEach(System.out::println);
  }

  public void showPopularPosts(int count) {
    System.out.println("Популярные посты:");
    posts.stream()
            .sorted(Comparator.comparing(Post::getLikes).reversed())
            .limit(count)
            .forEach(System.out::println);
  }

  public void showUserPosts(User user) {
    System.out.println("Мои посты:");
    posts.stream()
            .filter(post -> post.getAuthor().equals(user))
            .forEach(System.out::println);
  }
}