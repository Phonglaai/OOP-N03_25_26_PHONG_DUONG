import java.util.ArrayList;
import java.util.List;
import models.User;

public class ListOfUser {
    // Store Users in a List
    private List<User> users = new ArrayList<>();

    // Method to add a user to the list
    public void addUser(User user) {
        users.add(user);
    }

    // Method to retrieve all users
    public List<User> getAllUsers() {
        return users;
    }

    // Method to search for a user by identity
    public User findUserByIdentity(String identity) {
        for (User user : users) {
            if (user.getInfo().contains(identity)) {
                return user;
            }
        }
        return null; // If no user is found
    }
}
