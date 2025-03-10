package six.medium;
import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) {
    String filePath = "words.txt";

    try {
      BufferedReader reader = new BufferedReader(new FileReader(filePath));
      String line;
      HashSet<String> uniqueWords = new HashSet<>();
      HashMap<String, Integer> wordFrequency = new HashMap<>();

      while ((line = reader.readLine()) != null) {
        String[] words = line.split("\\W+");

        for (String word : words) {
          if (!word.isEmpty()) {
            word = word.toLowerCase();
            uniqueWords.add(word);
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
          }
        }
      }

      reader.close();

      System.out.println("Уникальные слова и их частота:");
      for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
        System.out.println(entry.getKey() + ": " + entry.getValue());
      }

    } catch (IOException e) {
      System.out.println("Ошибка при чтении файла: " + e.getMessage());
    }
  }
}
