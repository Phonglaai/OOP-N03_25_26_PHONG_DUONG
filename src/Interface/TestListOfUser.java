public class TestListOfUser {
    public static void main(String[] args) {
        // Create ListOfUser object
        ListOfUser listOfUsers = new ListOfUser();

        // Create and add users
        User user1 = new User("Nguyen Tung Duong", "01/01/1990", "12345", "Admin");
        User user2 = new User("Tran Hoang Phong", "02/02/1992", "67890", "User");
        listOfUsers.addUser(user1);
        listOfUsers.addUser(user2);

        // Retrieve and print all users
        for (User user : listOfUsers.getAllUsers()) {
            System.out.println(user.getInfo());
        }

        // Search for a user by identity
        User foundUser = listOfUsers.findUserByIdentity("67890");
        if (foundUser != null) {
            System.out.println("Found User: " + foundUser.getInfo());
        } else {
            System.out.println("User not found");
        }
    }
}
