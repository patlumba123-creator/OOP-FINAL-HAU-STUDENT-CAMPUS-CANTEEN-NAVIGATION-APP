// Multi-level inheritance: Person → Student → CollegeStudent
public class CollegeStudent extends Student {
    private String college;

    public CollegeStudent(String name, int studentNumber, String course, String schoolLevel, String building, String college) {
        super(name, studentNumber, course, schoolLevel, building);
        this.college = college;
    }

    public String getCollege() { return college; }

    // Override getRole() again → Polymorphism
    @Override
    public String getRole() {
        return "College Student";
    }
}
