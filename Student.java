// Student extends Person (Inheritance)
public class Student extends Person {
    private int studentNumber;
    private String course;
    private String schoolLevel;
    private String building;

    public Student(String name, int studentNumber, String course, String schoolLevel, String building) {
        super(name); // call Person constructor
        this.studentNumber = studentNumber;
        this.course = course;
        this.schoolLevel = schoolLevel;
        this.building = building;
    }

    public int getStudentNumber() { return studentNumber; }
    public String getCourse() { return course; }
    public String getSchoolLevel() { return schoolLevel; }
    public String getBuilding() { return building; }

    // Override getRole() → Polymorphism
    @Override
    public String getRole() {
        return "Student";
    }
}
