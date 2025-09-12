import models.User;

public class TestUser {
    public static void test() {
        // Khởi tạo đối tượng User từ package models
        User user1 = new User("Nguyen Tung Duong", "01/01/1990", "12345", "Admin");
        System.out.println(user1.getInfo());

        User user2 = new User("Tran Hoang Phong", "02/02/1992", "67890", "User");
        System.out.println(user2.getInfo());

        // Test the setInfo method to update the password
        user1.setInfo("54321");
        System.out.println("Updated User1 Info: " + user1.getInfo());
    }
}
