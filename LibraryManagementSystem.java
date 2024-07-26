import java.util.InputMismatchException;
import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nPersonal Library Management System");
            System.out.println("1. Add a book");
            System.out.println("2. Read pages from a book");
            System.out.println("3. Display all books");
            System.out.println("4. Total pages read");
            System.out.println("5. Total pages in library");
            System.out.println("6. Pages read per day");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter book title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter book author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter total pages: ");
                        int totalPages = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        library.addBook(new Book(title, author, totalPages));
                        System.out.println("Book added successfully!");
                        break;

                    case 2:
                        System.out.print("Enter book title: ");
                        String readTitle = scanner.nextLine();
                        System.out.print("Enter pages read: ");
                        int pagesRead = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        library.readBook(readTitle, pagesRead);
                        break;

                    case 3:
                        library.displayBooks();
                        break;

                    case 4:
                        System.out.println("Total pages read: " + library.getTotalPagesRead());
                        break;

                    case 5:
                        System.out.println("Total pages in library: " + library.getTotalPagesInLibrary());
                        break;

                    case 6:
                        System.out.print("Enter number of days: ");
                        int days = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.println("Pages read per day: " + library.getPagesReadPerDay(days));
                        break;

                    case 7:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume the invalid input
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
