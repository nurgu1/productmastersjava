import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int num = scanner.nextInt();
    List<Integer> numbersList = new ArrayList<>();

    for (int i = 0; i < num; i++) {
      numbersList.add(scanner.nextInt());
    }
    scanner.close();

    System.out.println("First: " + numbersList);

    List<Integer> numbersNew = removeDuplicates(numbersList);

    System.out.println("No dub: " + numbersNew);
  }

  public static List<Integer> removeDuplicates(List<Integer> list) {
    return new ArrayList<>(new LinkedHashSet<>(list));
  }
}
