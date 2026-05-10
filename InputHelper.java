import java.util.Scanner;
import java.time.LocalDate;

public class InputHelper {

    private static final Scanner scanner = new Scanner(System.in);

    // =====================================================
    // READ NON-EMPTY STRING
    // =====================================================
    public static String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("  [!] This field cannot be empty.");
        }
    }

    // =====================================================
    // READ POSITIVE INTEGER
    // =====================================================
    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                int num = Integer.parseInt(input);
                if (num > 0) {
                    return num;
                }
            } catch (Exception e) {}

            System.out.println("  [!] Enter a positive number.");
        }
    }

    // =====================================================
    // READ CHOICE IN RANGE
    // =====================================================
    public static int readChoice(String prompt, int min, int max) {
        while (true) {
            int choice = readInt(prompt);

            if (choice >= min && choice <= max) {
                return choice;
            }

            System.out.println("  [!] Choose from " + min + " to " + max + " only.");
        }
    }

    // =====================================================
    // NAME VALIDATION
    // =====================================================
    public static String readName(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.matches("[a-zA-Z .'-]{1,50}")) {
                return input;
            }

            System.out.println("  [!] Letters only. Max 50 characters.");
        }
    }

    // =====================================================
    // OPTIONAL MIDDLE NAME
    // =====================================================
    public static String readMiddleName() {
        System.out.print("  Middle Name (press Enter to skip): ");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) return null;

        while (!input.matches("[a-zA-Z .'-]{1,50}")) {
            System.out.println("  [!] Letters only. Max 50 characters.");
            System.out.print("  Middle Name (press Enter to skip): ");
            input = scanner.nextLine().trim();

            if (input.isEmpty()) return null;
        }

        return input;
    }

    // =====================================================
    // BIRTHDATE (REAL DATE CHECK)
    // =====================================================
    public static String readBirthdate() {
        while (true) {
            System.out.print("  Birthdate (YYYY-MM-DD): ");
            String input = scanner.nextLine().trim();

            try {
                LocalDate date = LocalDate.parse(input);

                if (date.getYear() >= 1900 && !date.isAfter(LocalDate.now())) {
                    return input;
                }

            } catch (Exception e) {}

            System.out.println("  [!] Invalid date. Example: 1995-06-15");
        }
    }

    // =====================================================
    // GENDER
    // =====================================================
    public static String readGender() {
        System.out.println("  Gender:");
        System.out.println("    [1] Male");
        System.out.println("    [2] Female");
        System.out.println("    [3] Other");

        int choice = readChoice("  Enter choice: ", 1, 3);

        if (choice == 1) return "Male";
        if (choice == 2) return "Female";
        return "Other";
    }

    // =====================================================
    // CIVIL STATUS
    // =====================================================
    public static String readCivilStatus() {
        System.out.println("  Civil Status:");
        System.out.println("    [1] Single");
        System.out.println("    [2] Married");
        System.out.println("    [3] Widowed");

        int choice = readChoice("  Enter choice: ", 1, 3);

        if (choice == 1) return "Single";
        if (choice == 2) return "Married";
        return "Widowed";
    }

    // =====================================================
    // CONTACT NUMBER (OPTIONAL)
    // =====================================================
    public static String readContactNumber() {
        while (true) {
            System.out.print("  Contact Number (09XXXXXXXXX, press Enter to skip): ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) return null;

            if (input.matches("09\\d{9}")) {
                return input;
            }

            System.out.println("  [!] Must be 11 digits starting with 09.");
        }
    }

    // =====================================================
    // YES / NO CONFIRM
    // =====================================================
    public static boolean confirm(String message) {
        while (true) {
            System.out.print("  " + message + " (Y/N): ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("Y")) return true;
            if (input.equals("N")) return false;

            System.out.println("  [!] Type Y or N only.");
        }
    }
}
