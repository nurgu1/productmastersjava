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

    // 3. Вывод людей из Алматы
    List<String> almatyPeople = people.stream()
            .filter(person -> "Almaty".equals(person.getCity()))
            .map(Person::getName)
            .collect(Collectors.toList());
    System.out.println("Люди из Алматы: " + almatyPeople);

    // 4. Преобразование в Map<String, Integer> (имя → возраст)
    Map<String, Integer> nameToAgeMap = people.stream()
            .collect(Collectors.toMap(Person::getName, Person::getAge));
    System.out.println("Карта имя → возраст: " + nameToAgeMap);

    // 5. Сортировка по возрасту (ТОП-3 самых старших)
    System.out.println("ТОП-3 самых старших:");
    people.stream()
            .sorted((p1, p2) -> Integer.compare(p2.getAge(), p1.getAge())) // Сортировка по убыванию
            .limit(3) // ТОП-3
            .forEach(System.out::println);

    // 6. Создание компаний
    List<Company> companies = Arrays.asList(
            Company.builder()
                    .name("TechCorp")
                    .employees(Arrays.asList(
                            Person.builder().name("Alice").age(30).city("Almaty").build(),
                            Person.builder().name("Bob").age(25).city("Astana").build()
                    ))
                    .build(),
            Company.builder()
                    .name("HealthPlus")
                    .employees(Arrays.asList(
                            Person.builder().name("Charlie").age(17).city("Almaty").build(),
                            Person.builder().name("David").age(19).city("Shymkent").build(),
                            Person.builder().name("Eva").age(20).city("Almaty").build()
                    ))
                    .build()
    );

    // 7. Фильтрация сотрудников компаний (оставляем тех, кто старше 25)
    companies.forEach(company ->
            company.setEmployees(
                    company.getEmployees().stream()
                            .filter(person -> person.getAge() > 25)
                            .toList()
            )
    );

    // 8. Средний возраст сотрудников в каждой компании → Map<String, Double>
    Map<String, Double> averageAgeByCompany = companies.stream()
            .collect(Collectors.toMap(
                    Company::getName,
                    company -> company.getEmployees().stream()
                            .mapToInt(Person::getAge)
                            .average()
                            .orElse(0.0)
            ));

    System.out.println("Средний возраст по компаниям: " + averageAgeByCompany);
  }
}
