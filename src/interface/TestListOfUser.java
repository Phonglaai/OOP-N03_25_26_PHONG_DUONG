import models.User;
import java.util.List;

public class TestListOfUser {
    public static void test() {
        // Khởi tạo đối tượng ListOfUser
        ListOfUser listOfUser = new ListOfUser();

        // Tạo một số người dùng
        User user1 = new User("Nguyen Tung Duong", "01/01/1990", "12345", "Admin");
        User user2 = new User("Tran Hoang Phong", "02/02/1992", "67890", "User");

        // Thêm người dùng vào danh sách
        listOfUser.addUser(user1);
        listOfUser.addUser(user2);

        // In ra danh sách tất cả người dùng
        List<User> users = listOfUser.getAllUsers();
        System.out.println("All Users:");
        for (User user : users) {
            System.out.println(user.getInfo());
        }

        // Tìm kiếm người dùng theo identity
        System.out.println("Search for user with name 'Nguyen Tung Duong':");
        User foundUser = listOfUser.findUserByIdentity("Nguyen Tung Duong");
        if (foundUser != null) {
            System.out.println("User found: " + foundUser.getInfo());
        } else {
            System.out.println("User not found.");
        }
    }
}
