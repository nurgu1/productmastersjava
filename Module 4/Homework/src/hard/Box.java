package hard;

public class Box<T>{
    private T item;

    public Box(T item) {
        this.item= item;
    }

    public void setItem(T item) {
        this.item=item;
    }

    public T getItem() {
        return item;
    }

    public void showType() {
        System.out.println("Type of T:" + item.getClass().getName());
    }
}
class MyData {
    private String data;

    public MyData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MyData: " + data;
    }
}