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
    // ------------------------------------------
   // OPTION 2 — VIEW ALL RESIDENTS
   // ------------------------------------------
   case 2:
                        try {
                            System.out.println("\n[ ALL RESIDENTS ]");
                            System.out.println("==========================================");
                            String result = CRUDOperations.getAllResidents();
                            System.out.println("  Result: " + result);
                        } catch (Exception e) {
                            System.out.println("  [ERROR] Failed to retrieve residents: " + e.getMessage());
                        }
                        break;
      // ------------------------------------------
      // OPTION 3 — SEARCH RESIDENT BY ID
      // ------------------------------------------
                    case 3:
                        try {
                            System.out.println("\n[ SEARCH RESIDENT BY ID ]");
                            int id = InputHelper.getID();
                            System.out.println("==========================================");
                            String result = CRUDOperations.getAllREsidentbyID(id);
                            System.out.println("  Result: " + result);
                        } catch (Exception e) {
                            System.out.println("  [ERROR] Failed to search resident: " + e.getMessage());
                        }
                        break;

    // ------------------------------------------
    // OPTION 4 — UPDATE RESIDENT
    // ------------------------------------------
                    case 4:
                        try {
                            System.out.println("\n[ UPDATE RESIDENT ]");
                            int id = InputHelper.getID();
                            String column = InputHelper.getUpdateChoice();
                            Object newValue = InputHelper.getNewValue(column);
                            String result = CRUDOperations.updateResident(column, newValue, id);
                            System.out.println("\n  Result: " + result);
                        } catch (Exception e) {
                            System.out.println("  [ERROR] Failed to update resident: " + e.getMessage());
                        }
                        break;
    // ------------------------------------------
    // OPTION 5 — DELETE RESIDENT
    // ------------------------------------------
                    case 5:
                        try {
                            System.out.println("\n[ DELETE RESIDENT ]");
                            int id = InputHelper.getID();
                            boolean confirmed = InputHelper.confirm("Are you sure you want to delete Resident ID " + id + "?");
                            if (confirmed) {
                                String result = CRUDOperations.deleteResident(id);
                                System.out.println("\n  Result: " + result);
                            } else {
                                System.out.println("  Deletion cancelled.");
                            }
                        } catch (Exception e) {
                            System.out.println("  [ERROR] Failed to delete resident: " + e.getMessage());
                        }
                        break;
