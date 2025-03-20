package org.example;
import java.util.Arrays;
import java.util.List;
public class Main {
  public static void main(String[] args) {
    List<Person> people = Arrays.asList(
            Person.builder().name("Alice").age(30).city("New York").build(),
            Person.builder().name("Bob").age(25).city("Los Angeles").build(),
            Person.builder().name("Charlie").age(35).city("Chicago").build()
    );
    people.forEach(System.out::println);
  }
}

