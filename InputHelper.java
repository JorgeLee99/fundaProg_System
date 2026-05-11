import java.util.Scanner;
import java.time.LocalDate;

public class InputHelper {

    private static final Scanner scanner = new Scanner(System.in);

    
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

    // ============================================
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

    // ===========================================
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

    // ==========================================
    // NAME VALIDATION
    // ==================================
    public static String getName(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.matches("[a-zA-Z .'-]{1,50}")) {
                return input;
            }

            System.out.println("  [!] Letters only. Max 50 characters.");
        }
    }

    // ==============================================
    // OPTIONAL MIDDLE NAME
    // ===========================================
    public static String getMiddleName() {
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

    // ==========================================
    // BIRTHDATE (REAL DATE CHECK)
    // =============================================
    public static String getBirthdate() {
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

    // =============================================
    // GENDER
    // ==========================================
    public static String getGender() {
        System.out.println("  Gender:");
        System.out.println("    [1] Male");
        System.out.println("    [2] Female");
        System.out.println("    [3] Other");

        int choice = readChoice("  Enter choice: ", 1, 3);

        if (choice == 1) return "Male";
        if (choice == 2) return "Female";
        return "Other";
    }

    // ================================================
    // CIVIL STATUS
    // ===============================================
    public static String getCivilStatus() {
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
    // =================================================
    public static String getContactNumber() {
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
    // ==========================
    // VOTER STATUS
    // ============

       public static int getIsVoter() {
        System.out.println("  Is Voter?");
        System.out.println("    [1] Yes");
        System.out.println("    [2] No");

        int choice = readChoice("  Enter choice: ", 1, 2);
        return (choice == 1) ? 1 : 0;
    }
// =========================
// Household ID
// ===============================
    public static Integer getHouseholdID() {
    while (true) {
        System.out.print("  Household ID (press Enter to skip): ");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) return null;

        try {
            int id = Integer.parseInt(input);
            if (id > 0) return id;
        } catch (Exception e) {}

        System.out.println("  [!] Numbers only please.");
    }
}
// ===========================
// RESIDENT ID
// =========================
    public static int getID() {
    while (true) {
        System.out.print("  Enter Resident ID: ");
        String input = scanner.nextLine().trim();

        try {
            int id = Integer.parseInt(input);
            if (id > 0) return id;
        } catch (Exception e) {}

        System.out.println("  [!] ID must be a positive number.");
    }
}

// ============================
// OBJECT ARRAY?
// ===================================
    public static Object[] buildResidentData() {

    System.out.println("\n--- Enter Resident Details ---");

    String firstName    = getName("  First Name: ");
    String lastName     = getName("  Last Name: ");
    String middleName   = getMiddleName();
    String birthdate    = getBirthdate();
    String gender       = getGender();
    String civilStatus  = getCivilStatus();
    String contactNo    = getContactNumber();
    int    isVoter      = getIsVoter();
    Integer HouseholdID = getHouseholdID();

    return new Object[]{
        firstName,
        lastName,
        middleName,
        birthdate,
        gender,
        civilStatus,
        contactNo,
        isVoter,
        HouseholdId
    };
}

// =============================================
// COLUMN UPDATE
// =============================================
    public static String getUpdateChoice() {

    System.out.println("\nWhat do you want to update?");
    System.out.println("[1] First Name");
    System.out.println("[2] Last Name");
    System.out.println("[3] Birthdate");
    System.out.println("[4] Gender");
    System.out.println("[5] Civil Status");
    System.out.println("[6] Contact Number");

    int choice = readChoice("Enter choice: ", 1, 6);

    if (choice == 1) return "first_name";
    if (choice == 2) return "last_name";
    if (choice == 3) return "birthdate";
    if (choice == 4) return "gender";
    if (choice == 5) return "civil_status";
    return "contact_number";
}
// =====================================================
// GET NEW VALUE BASED ON COLUMN (FOR UPDATE)
// =====================================================
public static Object getNewValue(String column) {

    if (column.equals("first_name")) {
        return getName("New First Name: ");
    }

    if (column.equals("last_name")) {
        return getName("New Last Name: ");
    }

    if (column.equals("birthdate")) {
        return getBirthdate();
    }

    if (column.equals("gender")) {
        return getGender();
    }

    if (column.equals("civil_status")) {
        return getCivilStatus();
    }

    if (column.equals("contact_number")) {
        return getContactNumber();
    }

    return null;
}
    // ===================================================
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
