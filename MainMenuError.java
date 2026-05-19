import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenuError {

    private static final Scanner scanner = new Scanner(System.in);

    // =====================================================
    // DISPLAY MAIN MENU
    // =====================================================
    public static void showMenu() {
        System.out.println();
        System.out.println("==========================================");
        System.out.println("    BARANGAY RESIDENT RECORD SYSTEM       ");
        System.out.println("==========================================");
        System.out.println("  [1] Register Resident");
        System.out.println("  [2] View All Residents");
        System.out.println("  [3] Search Resident by ID");
        System.out.println("  [4] Update Resident");
        System.out.println("  [5] Delete Resident");
        System.out.println("  [6] Exit");
        System.out.println("==========================================");
        System.out.print("  Enter choice: ");
    }
     // =====================================================
    // MAIN METHOD
    // =====================================================
    public static void main(String[] args) {

        System.out.println("\n  Welcome to the Barangay Resident Record System!");

        while (true) {
            showMenu();

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {

                    // ------------------------------------------
                    // OPTION 1 — ADD NEW RESIDENT
                    // ------------------------------------------
                      case 1:
                        try {
                            System.out.println("\n[ ADD NEW RESIDENT ]");
                            System.out.println("  Note: This will also update the household resident count.");
                            Object[] data = InputHelper.buildResidentData();
                            String result = CRUDOperations.processResidentRegistration(data);
                            System.out.println("\n  Result: " + result);
                        } catch (Exception e) {
                            System.out.println("  [ERROR] Failed to add resident: " + e.getMessage());
                        }
                        break;