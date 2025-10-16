// Handles input validation
public class InputValidator {
    public static boolean isValidName(String name) {
        return name.matches("[a-zA-Z ]+");
    }

    public static boolean isValidStudentNumber(String input) {
        return input.matches("\\d+");
    }

    public static boolean isValidPasscode(String input) {
        return input.equals("password123");
    }

    public static boolean isValidCourse(String input) {
        return input.matches("[a-zA-Z0-9 .-]+") && input.trim().length() > 1;
    }
}
