package medium;

public class UserData {
    private final int id;
    private final String name;
    private final String email;


    public UserData(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String toString() { return "medium.hw.UserData: id = " + id + ", user name = " + name + ", user email = " + email; }
}
