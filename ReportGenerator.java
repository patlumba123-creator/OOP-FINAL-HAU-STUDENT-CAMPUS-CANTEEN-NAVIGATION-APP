import java.io.FileWriter;
import java.io.IOException;


public class ReportGenerator {
    public static void generateCSV(Student student, Canteen canteen) {
        try (FileWriter writer = new FileWriter("canteen_report.csv", true)) {
            writer.append(student.getStudentNumber() + ",")
                  .append(student.getName() + ",")
                  .append(student.getSchoolLevel() + ",")
                  .append(student.getCourse() + ",")
                  .append(student.getBuilding() + ",")
                  .append(canteen.getName() + ",")
                  .append(String.join(" | ", canteen.getStores()))
                  .append("\n");
            System.out.println("Report saved to canteen_report.csv");
        } catch (IOException e) {
            System.out.println("Error writing CSV: " + e.getMessage());
        }
    }
}


