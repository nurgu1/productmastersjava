package medium;

public class UserDataCloudDataSource implements DataSource<UserData> {
    @Override
    public UserData getData() {
        return new UserData(3, "Alima", "alma@gmail.com");
    }




}
