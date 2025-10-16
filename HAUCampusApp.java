import java.util.*;
import java.io.*;

public class HAUCampusApp {
    public static void main(String[] args) {
        Scanner scanz = new Scanner(System.in);
        CampusNavigator navigator = new CampusNavigator();

        Student[] students = new Student[5];
        int studentCount = 0;

        Map<String, String> buildingMap = new LinkedHashMap<>();
        buildingMap.put("PDC", "PLaza de Corazon Building");
        buildingMap.put("SMH", "St.Martha Hall Building");
        buildingMap.put("SFJ", "San Francisco De Javier Building");
        buildingMap.put("STL", "St.Therese of Liseux Building");
        buildingMap.put("SGH", "St.Gabriel Halll Building");
        buildingMap.put("SRH", "St.Raphael Hall Building");
        buildingMap.put("SMH2", "St.Michael Hall Building");
        buildingMap.put("GGN", "Geronimo G. Nepomuceno Building");
        buildingMap.put("PGN", "Peter G. Nepomuceno Building");
        buildingMap.put("DJDN", "Don Juan D. Nepomuceno Building");
        buildingMap.put("APS", "Archbishop Pedro Santos Building");
        buildingMap.put("MGN", "Mamerto G. Nepomuceno Building");
        buildingMap.put("CHGA", "Chapel of the Holy Guardian Angel");
        buildingMap.put("SJNFC", "Sister Josefina Nepomuceno Formation Center");
        buildingMap.put("SJH", "St.Joseph Hall Building");
        buildingMap.put("SH", "Sacred Heart Building");
        buildingMap.put("CC", "Covered Court");
        buildingMap.put("IHG", "Immaculate Heart Gymnasium/Annex");

        Map<String, String> reverseMap = new HashMap<>();
        for (Map.Entry<String, String> entry : buildingMap.entrySet()) {
            reverseMap.put(entry.getValue().toLowerCase(), entry.getValue());
            reverseMap.put(entry.getKey().toLowerCase(), entry.getValue());
        }

        System.out.println("======================================");
        System.out.println(" Welcome to the HAU Campus Navigator! ");
        System.out.println("======================================\n");

        while (true) {
            System.out.println("\nPlease select a mode:");
            System.out.println("[1] Student");
            System.out.println("[2] Admin");
            System.out.println("[3] Exit Program");
            System.out.print("Enter choice: ");
            String mode = scanz.nextLine();

            if (mode.equals("1")) {
                int studentNumber = 0;
                boolean validNumber = false;
                while (!validNumber) {
                    try {
                        System.out.print("Enter your student number: ");
                        String input = scanz.nextLine();
                        if (!InputValidator.isValidStudentNumber(input)) {
                            System.out.println("Invalid input. Digits only.");
                            continue;
                        }
                        studentNumber = Integer.parseInt(input);
                        validNumber = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter digits only.");
                    }
                }

                String name;
                do {
                    System.out.print("Enter your name: ");
                    name = scanz.nextLine();
                    if (!InputValidator.isValidName(name)) {
                        System.out.println("Invalid input. Name must contain letters only.");
                    }
                } while (!InputValidator.isValidName(name));

                String schoolLevel = null;
                while (schoolLevel == null) {
                    System.out.println("\nSelect your school level:");
                    System.out.println("[1] Elementary");
                    System.out.println("[2] Junior High School");
                    System.out.println("[3] Senior High School");
                    System.out.println("[4] College");
                    System.out.print("Enter choice: ");
                    String level = scanz.nextLine();
                    if (level.equals("1")) schoolLevel = "Elementary";
                    else if (level.equals("2")) schoolLevel = "Junior High School";
                    else if (level.equals("3")) schoolLevel = "Senior High School";
                    else if (level.equals("4")) schoolLevel = "College";
                    else System.out.println("Invalid input. Please select 1–4.");
                }

                String course = "N/A";
                String college = "N/A";
                Student student;
                if (schoolLevel.equalsIgnoreCase("College")) {
                    do {
                        System.out.print("Enter your college (Ex. School of ____): ");
                        college = scanz.nextLine();
                        if (!InputValidator.isValidName(college)) {
                            System.out.println("Invalid input. Please enter a valid college name.");
                        }
                    } while (!InputValidator.isValidName(college));

                    do {
                        System.out.print("Enter your undergraduate course (Ex. Bachelor of _____ or BS__): ");
                        course = scanz.nextLine();
                        if (!InputValidator.isValidCourse(course)) {
                            System.out.println("Invalid input. Please enter a valid course name.");
                        }
                    } while (!InputValidator.isValidCourse(course));

                    student = new CollegeStudent(name, studentNumber, course, schoolLevel, "Unknown", college);
                } else {
                    student = new Student(name, studentNumber, course, schoolLevel, "Unknown");
                }

                String building = null;
                while (building == null) {
                    System.out.println("\nSelect your current building (enter full name or acronym):");
                    for (Map.Entry<String, String> entry : buildingMap.entrySet()) {
                        System.out.println(" - " + entry.getValue() + " [" + entry.getKey() + "]");
                    }
                    System.out.print("Enter choice: ");
                    String input = scanz.nextLine().trim().toLowerCase();
                    building = reverseMap.get(input);
                    if (building == null) {
                        System.out.println("Invalid input. Please enter a valid building name or acronym.");
                    }
                }

                if (student instanceof CollegeStudent) {
                    CollegeStudent cs = (CollegeStudent) student;
                    student = new CollegeStudent(cs.getName(), cs.getStudentNumber(), cs.getCourse(),
                                                 cs.getSchoolLevel(), building, cs.getCollege());
                } else {
                    student = new Student(student.getName(), student.getStudentNumber(),
                                          student.getCourse(), student.getSchoolLevel(), building);
                }

                if (studentCount < students.length) {
                    students[studentCount] = student;
                    studentCount++;
                }

                Canteen canteen = navigator.findCanteen(building);
                if (canteen != null) {
                    System.out.println("\n--- Student Summary Report ---");
                    System.out.println("ID: " + student.getStudentNumber());
                    System.out.println("Name: " + student.getName());
                    System.out.println("Role: " + student.getRole());
                    System.out.println("School Level: " + student.getSchoolLevel());
                    System.out.println("Course: " + student.getCourse());
                    System.out.println("Building: " + student.getBuilding());
                    System.out.println("Nearest Canteen: " + canteen.getName());
                    System.out.println("Stores: " + String.join(", ", canteen.getStores()));

                    ReportGenerator.generateCSV(student, canteen);
                } else {
                    System.out.println("No canteen found for this building.");
                }

            } else if (mode.equals("2")) {
                System.out.print("\nEnter admin passcode: ");
                String passcode = scanz.nextLine();
                if (InputValidator.isValidPasscode(passcode)) {
                    boolean adminRunning = true;
                    while (adminRunning) {
                        System.out.println("\n--- Admin Menu ---");
                        System.out.println("1. View all canteens and stores");
                        System.out.println("2. View all student reports");
                        System.out.println("3. View all students entered");
                        System.out.println("4. Exit admin mode");
                        System.out.print("Enter choice: ");
                        String choice = scanz.nextLine();

                        switch (choice) {
                            case "1":
                                for (String b : navigator.getValidBuildings()) {
                                    Canteen c = navigator.findCanteen(b);
                                    if (c != null) {
                                        System.out.println("\n" + b + " -> " + c.getName());
                                        for (String s : c.getStores()) {
                                            System.out.println(" - " + s);
                                        }
                                    }
                                }
                                break;
                            case "2":
                                try (BufferedReader br = new BufferedReader(new FileReader("canteen_report.csv"))) {
                                    String line;
                                    System.out.println("\n--- Student Reports ---");
                                    while ((line = br.readLine()) != null) {
                                        System.out.println(line);
                                    }
                                } catch (IOException e) {
                                    System.out.println("No reports found.");
                                }
                                break;
                            case "3":
                                System.out.println("\n--- Students in Array ---");
                                for (int i = 0; i < studentCount; i++) {
                                    System.out.println(students[i].getName() + " - " + students[i].getRole());
                                }
                                break;
                            case "4":
                                adminRunning = false;
                                break;
                            default:
                                System.out.println("Invalid input. Please select 1–4.");
                        }
                    }
                } else {
                    System.out.println("Invalid input. Wrong passcode.");
                }

            } else if (mode.equals("3")) {
                System.out.println("\nThank you for using the HAU Campus Navigator. Goodbye!");
                System.exit(0);
            } else {
                System.out.println("Invalid input. Please select 1–3.");
            }
        }
    }
}
