package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) {
    List<Person> people = Arrays.asList(
            Person.builder().name("Alice").age(30).city("Almaty").build(),
            Person.builder().name("Bob").age(25).city("Astana").build(),
            Person.builder().name("Charlie").age(17).city("Almaty").build(),
            Person.builder().name("David").age(19).city("Shymkent").build(),
            Person.builder().name("Eva").age(20).city("Almaty").build()
    );

    // 1. Фильтрация списка (люди старше 18 лет)
    List<Person> adults = people.stream()
            .filter(person -> person.getAge() > 18)
            .collect(Collectors.toList());
    System.out.println("Взрослые: " + adults);

    // 2. Вычисление среднего возраста
    OptionalDouble averageAge = people.stream()
            .mapToInt(Person::getAge)
            .average();
    System.out.println("Средний возраст: " + (averageAge.isPresent() ? averageAge.getAsDouble() : "Нет данных"));

    // 3. Вывести список людей из Алматы
    List<String> almatyPeople = people.stream()
            .filter(person -> "Almaty".equals(person.getCity()))
            .map(Person::getName)
            .collect(Collectors.toList());
    System.out.println("Люди из Алматы: " + almatyPeople);

    // 4. Преобразование списка в Map<String, Integer> (имя → возраст)
    Map<String, Integer> nameToAgeMap = people.stream()
            .collect(Collectors.toMap(Person::getName, Person::getAge));
    System.out.println("Карта имя → возраст: " + nameToAgeMap);
  }
}
