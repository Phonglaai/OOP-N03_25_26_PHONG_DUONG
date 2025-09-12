public class User implements PeopleInterface {
    // Member variables
    private String fullName;
    private String dateOfBirth;
    private String identity;
    private String userRole;

    // Constructor (optional)
    public User(String fullName, String dateOfBirth, String identity, String userRole) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.identity = identity;
        this.userRole = userRole;
    }

    // Implement the setInfo method
    @Override
    public void setInfo(String identity) {
        this.identity = identity;
    }

    // Implement the getInfo method
    @Override
    public String getInfo() {
        return "Name: " + this.fullName + "\nDOB: " + this.dateOfBirth + "\nIdentity: " + this.identity + "\nRole: " + this.userRole;
    }

    // Getter and Setter for userRole (optional)
    public String getUserRole() {
        return this.userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
