

import java.util.Scanner;
 
// ============================================================

 
public class InputValidation {
 
    static Scanner scanner = new Scanner(System.in);
 
    
    //
  
    public static String readString(String prompt) {
        String input = "";
 
        while (input.isEmpty()) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
 
            if (input.isEmpty()) {
                System.out.println("  [!] This field cannot be empty. Try again.");
            }
        }
 
        return input;
    }

    // this part
    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
 
            try {
                int number = Integer.parseInt(input);
 
                if (number <= 0) {
                    System.out.println("  [!] Please enter a positive number.");
                } else {
                    return number;
                }
 
            } catch (NumberFormatException e) {
                System.out.println("  [!] Invalid input. Numbers only please.");
            }
        }
    }
    // part 3
    public static int readChoice(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
 
            try {
                int choice = Integer.parseInt(input);
 
                if (choice < min || choice > max) {
                    System.out.println("  [!] Enter a number from " + min + " to " + max + " only.");
                } else {
                    return choice;
                }
 
            } catch (NumberFormatException e) {
                System.out.println("  [!] Invalid input. Numbers only please.");
            }
        }

    // 4
    public static String readName(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
 
            if (input.isEmpty()) {
                System.out.println("  [!] Name cannot be empty.");
 
            } else if (input.length() > 50) {
                System.out.println("  [!] Name is too long. Max 50 characters only.");
 
            } else if (!input.matches("[a-zA-Z ]+")) {
                System.out.println("  [!] Name must contain letters only.");
 
            } else {
                return input;
            }
        }
    }

    public static String readMiddleName() {
        System.out.print("  Middle Name (press Enter to skip): ");
        String input = scanner.nextLine().trim();
 
        if (input.isEmpty()) {
            return null; // null is saved to DB since middle_name DEFAULT NULL
        }
 
        while (!input.matches("[a-zA-Z ]+") || input.length() > 50) {
            System.out.println("  [!] Letters only. Max 50 characters.");
            System.out.print("  Middle Name (press Enter to skip): ");
            input = scanner.nextLine().trim();
 
            if (input.isEmpty()) return null;
        }
 
        return input;
    }

    // part 6 birthdate
    public static String readBirthdate() {
        while (true) {
            System.out.print("  Birthdate (YYYY-MM-DD): ");
            String input = scanner.nextLine().trim();
 
            if (!input.matches("\\d{4}-\\d{2}-\\d{2}")) {
                System.out.println("  [!] Wrong format. Use YYYY-MM-DD. Example: 1995-06-15");
                continue;
            }
 
            // Split and check each part
            String[] parts = input.split("-");
            int year  = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day   = Integer.parseInt(parts[2]);
 
            if (year < 1900 || year > 2025) {
                System.out.println("  [!] Year must be between 1900 and 2025.");
            } else if (month < 1 || month > 12) {
                System.out.println("  [!] Month must be 01 to 12.");
            } else if (day < 1 || day > 31) {
                System.out.println("  [!] Day must be 01 to 31.");
            } else {
                return input;
            }
        }
    }

    //Gender

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

    // Civl status

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

 // contact number

 public static String readContactNumber() {
        while (true) {
            System.out.print("  Contact Number (09XXXXXXXXX, press Enter to skip): ");
            String input = scanner.nextLine().trim();
 
            if (input.isEmpty()) {
                return null; // Optional field, null is OK in DB
            }
 
            if (!input.matches("09\\d{9}")) {
                System.out.println("  [!] Must start with 09 and be 11 digits. Example: 09171234567");
            } else {
                return input;
            }
        }
    }

 // voter status
 public static int readVoterStatus() {
        System.out.println("  Is Voter?");
        System.out.println("    [1] Yes");
        System.out.println("    [2] No");
 
        int choice = readChoice("  Enter choice: ", 1, 2);
        return (choice == 1) ? 1 : 0;
    }
 
    // Household ID

    public static Integer readHouseholdId() {
        while (true) {
            System.out.print("  Household ID (press Enter to skip): ");
            String input = scanner.nextLine().trim();
 
            if (input.isEmpty()) {
                return null; // Optional, resident may not belong to a household yet
            }
 
            try {
                int id = Integer.parseInt(input);
 
                if (id <= 0) {
                    System.out.println("  [!] Household ID must be a positive number.");
                } else {
                    return id;
                }
 
            } catch (NumberFormatException e) {
                System.out.println("  [!] Numbers only please.");
            }
        }
    }

    // Yes or no confirmation

    public static boolean confirm(String message) {
        while (true) {
            System.out.print("  " + message + " (Y/N): ");
            String input = scanner.nextLine().trim().toUpperCase();
 
            if (input.equals("Y")) return true;
            if (input.equals("N")) return false;
 
            System.out.println("  [!] Type Y for Yes or N for No only.");
        }
    }

    // collect all data

    public static Object[] collectResidentInput() {
        System.out.println("\n  --- Enter Resident Details ---");
 
        String firstName    = readName("  First Name: ");
        String lastName     = readName("  Last Name: ");
        String middleName   = readMiddleName();
        String birthdate    = readBirthdate();
        String gender       = readGender();
        String civilStatus  = readCivilStatus();
        String contactNo    = readContactNumber();
        int    isVoter      = readVoterStatus();
        Integer householdId = readHouseholdId();
 
        return new Object[]{
            firstName,
            lastName,
            middleName,
            birthdate,
            gender,
            civilStatus,
            contactNo,
            isVoter,
            householdId
        };
    }
 
}