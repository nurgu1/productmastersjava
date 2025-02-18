package hard;


public class Main {
  public static void main(String[] args) {

    Box<String> stringBox = new Box<>("Hello, Generics!");
    System.out.println("Stored: " + stringBox.getItem());
    stringBox.showType();

    Box<MyData> dataBox = new Box<>(new MyData("Data Data Data"));
    System.out.println("Stored: " + dataBox.getItem());
    dataBox.showType();
  }
}