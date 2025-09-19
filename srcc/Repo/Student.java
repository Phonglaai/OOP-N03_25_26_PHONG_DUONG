import java.util.ArrayList;
import java.util.List;

public class Student {
    private int studentId;
    private String name;
    private String email;
    private String mobileNumber;
    private String dob;
    private String gender;
    private String fatherName;
    private String motherName;
    private List<Address> addresses = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();

    public Student(int studentId, String name, String email, String mobileNumber,
                   String dob, String gender, String fatherName, String motherName) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.dob = dob;
        this.gender = gender;
        this.fatherName = fatherName;
        this.motherName = motherName;
    }

    // Getter & Setter
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getFatherName() { return fatherName; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }

    public String getMotherName() { return motherName; }
    public void setMotherName(String motherName) { this.motherName = motherName; }

    public List<Address> getAddresses() { return addresses; }
    public List<Course> getCourses() { return courses; }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + studentId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobileNumber + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
