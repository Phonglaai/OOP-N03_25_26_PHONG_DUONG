public class Main {
 public static void main(String[] args) {
        Repository<Student> studentRepo = new Repository<>();
        Repository<Course> courseRepo = new Repository<>();
        Repository<Address> addressRepo = new Repository<>();

        // CREATE
        Student s1 = new Student(1, "Nguyen Van A", "a@gmail.com", "0123456789", "2000-01-01", "Male", "Nguyen Van B", "Tran Thi C");
        studentRepo.create(s1.getStudentId(), s1);

        Course c1 = new Course(101, "Java Basics", "Intro to Java", "Programming", "3 months", "OOP, Syntax");
        courseRepo.create(c1.getCourseId(), c1);

        Address ad1 = new Address(201, "123 Street", "Hanoi", "HN", "Hoan Kiem", "100000", "Home");
        addressRepo.create(ad1.getAddressId(), ad1);

        // READ
        System.out.println("== Read Student ==");
        System.out.println(studentRepo.read(1));

        // UPDATE
        s1.setName("Nguyen Van A Updated");
        studentRepo.update(1, s1);

        System.out.println("== After Update ==");
        System.out.println(studentRepo.read(1));

        // DELETE
        studentRepo.delete(1);
        System.out.println("== After Delete ==");
        System.out.println(studentRepo.read(1)); // null
    }
}
