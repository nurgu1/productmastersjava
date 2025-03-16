package seven;

import java.util.Scanner;

public class TwitterConsoleApp {
  private static final Scanner scanner = new Scanner(System.in);
  private static final TwitterService twitterService = new TwitterService();

  public static void main(String[] args) {
    new TwitterConsoleApp().run();
  }

  public void run() {
    System.out.print("Введите ваше имя: ");
    String userName = scanner.nextLine().trim();
    User currentUser = new User(userName);
    System.out.println("Добро пожаловать, " + currentUser.getName() + "!");

    twitterService.loadPostsFromFile(); // Загружаем посты при запуске
    twitterService.initializePosts();

    while (true) {
      showMenu();
      int choice = getIntInput();
      switch (choice) {
        case 1 -> createPost(currentUser);
        case 2 -> likePost();
        case 3 -> repostPost();
        case 4 -> twitterService.showAllPosts();
        case 5 -> showPopularPosts();
        case 6 -> twitterService.showUserPosts(currentUser);
        case 7 -> addComment(); // Добавить комментарий
        case 8 -> deletePost(); // Удалить пост
        case 9 -> {
          twitterService.savePostsToFile(); // Сохраняем посты перед выходом
          System.out.println("Выход...");
          return;
        }
        default -> System.out.println("Некорректный ввод. Попробуйте снова.");
      }
    }
  }

  private void createPost(User author) {
    System.out.print("Введите текст поста (макс. 280 символов): ");
    String content = scanner.nextLine().trim();
    twitterService.createPost(author, content);
  }

  private void likePost() {
    System.out.print("Введите ID поста, который хотите лайкнуть: ");
    int postId = getIntInput();
    twitterService.likePost(postId);
  }

  private void repostPost() {
    System.out.print("Введите ID поста, который хотите репостнуть: ");
    int postId = getIntInput();
    twitterService.repostPost(postId);
  }

  private void showPopularPosts() {
    System.out.print("Введите количество популярных постов: ");
    int count = getIntInput();
    twitterService.showPopularPosts(count);
  }

  private void addComment() {
    System.out.print("Введите ID поста, к которому хотите добавить комментарий: ");
    int postId = getIntInput();
    System.out.print("Введите ваш комментарий: ");
    String comment = scanner.nextLine().trim();
    twitterService.addCommentToPost(postId, comment);
  }

  private void deletePost() {
    System.out.print("Введите ID поста, который хотите удалить: ");
    int postId = getIntInput();
    twitterService.deletePost(postId);
  }

  private int getIntInput() {
    int input;
    try {
      input = Integer.parseInt(scanner.nextLine().trim());
    } catch (NumberFormatException e) {
      System.out.println("Некорректный ввод.");
      return -1;
    }
    return input;
  }

  private static void showMenu() {
    System.out.println("\n=== Twitter Console ===");
    System.out.println("1. Написать пост");
    System.out.println("2. Лайкнуть пост");
    System.out.println("3. Сделать репост");
    System.out.println("4. Показать все посты");
    System.out.println("5. Показать популярные посты");
    System.out.println("6. Показать мои посты");
    System.out.println("7. Добавить комментарий");
    System.out.println("8. Удалить пост");
    System.out.println("9. Выход");
    System.out.print("Выберите действие: ");
  }
}