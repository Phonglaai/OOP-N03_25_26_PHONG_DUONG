public class Course {
    private int courseId;
    private String courseName;
    private String description;
    private String courseType;
    private String courseDuration;
    private String topics;

    public Course(int courseId, String courseName, String description,
                  String courseType, String courseDuration, String topics) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
        this.courseType = courseType;
        this.courseDuration = courseDuration;
        this.topics = topics;
    }

    // Getter & Setter
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCourseType() { return courseType; }
    public void setCourseType(String courseType) { this.courseType = courseType; }

    public String getCourseDuration() { return courseDuration; }
    public void setCourseDuration(String courseDuration) { this.courseDuration = courseDuration; }

    public String getTopics() { return topics; }
    public void setTopics(String topics) { this.topics = topics; }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + courseId +
                ", name='" + courseName + '\'' +
                ", type='" + courseType + '\'' +
                ", duration='" + courseDuration + '\'' +
                ", topics='" + topics + '\'' +
                '}';
    }
}
