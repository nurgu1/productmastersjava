import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberList {
  private List<Integer> numbers;

  public NumberList(List<Integer> numbers) {
    this.numbers = new ArrayList<>(numbers);
  }
  public int findMin() {
    return Collections.min(numbers);
  }

  public int findMax() {
    return Collections.max(numbers);
  }


  public List<Integer> sortAscending() {
    List<Integer> sortedList = new ArrayList<>(numbers);
    Collections.sort(sortedList);
    return sortedList;
  }

  public List<Integer> sortDescending() {
    List<Integer> sortedList = new ArrayList<>(numbers);
    sortedList.sort(Collections.reverseOrder());
    return sortedList;
  }

  public boolean containsElement(int element) {
    return numbers.contains(element);
  }

  public List<Integer> filterGreaterThan(int threshold) {
    List<Integer> filteredList = new ArrayList<>();
    for (int num : numbers) {
      if (num > threshold) {
        filteredList.add(num);
      }
    }
    return filteredList;
  }

  public int sumAll() {
    int sum = 0;
    for (int num : numbers) {
      sum += num;
    }
    return sum;
  }

  public static void main(String[] args) {
    List<Integer> nums = List.of(5, 2, 8, 1, 9, 3);
    NumberList numberList = new NumberList(nums);

    System.out.println("Минимум: " + numberList.findMin());
    System.out.println("Максимум: " + numberList.findMax());
    System.out.println("Сортировка по возрастанию: " + numberList.sortAscending());
    System.out.println("Сортировка по убыванию: " + numberList.sortDescending());
    System.out.println("Содержит 8? " + numberList.containsElement(8));
    System.out.println("Числа больше 4: " + numberList.filterGreaterThan(4));
    System.out.println("Сумма всех чисел: " + numberList.sumAll());
  }
}
