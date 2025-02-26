package five.medium;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
  public static void main(String[] args) {
    List<DayOfWeek> days = new ArrayList<>();
    for (DayOfWeek day: DayOfWeek.values()) {
      days.add(day);
    }

    System.out.println("Days of the week:");
    for (DayOfWeek day: days) {
      System.out.println(day);
    }

    System.out.println("\nWeekends:");
    for (DayOfWeek day: days) {
      System.out.println(day+"is a Weekend? Answer: " + isWeekend(day));
    }
  }
  public static boolean isWeekend(DayOfWeek day) {
    return day ==DayOfWeek.SATURRDAY || day==DayOfWeek.SUNDAY;
  }
}