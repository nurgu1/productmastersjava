package six.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    SafeList<String> list = new SafeList<>();

    list.add("apple");
    list.add("banana");
    list.add(null); // Не добавляется
    list.add("apple"); // Не добавляется

    System.out.println(list.get(0)); // apple
    System.out.println(list.get(5)); // null (не ошибка!)

    System.out.println("Размер списка: " + list.size()); // 2
    System.out.println("Содержит 'banana'? " + list.contains("banana")); // true
    System.out.println("Содержит 'apple'? " + list.contains("apple")); // true
    System.out.println("Содержит 'orange'? " + list.contains("orange")); // false

    list.add("orange");
    System.out.println("Содержит 'orange'? " + list.contains("orange")); // true
    System.out.println("Размер списка после добавления 'orange': " + list.size()); // 3

    ArrayList<Integer> numbers = new ArrayList<>();
    numbers.add(1);
    numbers.add(2);
    numbers.add(3);
    numbers.add(1);
    numbers.add(2);
    numbers.add(4);

    System.out.println("Исходный список: " + numbers);
    ArrayList<Integer> uniqueNumbers = removeDuplicates(numbers);
    System.out.println("Список без дубликатов: " + uniqueNumbers);
  }

  public static ArrayList<Integer> removeDuplicates(ArrayList<Integer> list) {
    // Using a HashSet to remove duplicates
    HashSet<Integer> set = new HashSet<>(list);
    return new ArrayList<>(set);
  }
}
