import java.util.*;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        List<Student> students = new ArrayList<>();
        students.add(new Student("Niranjan", 1741078, "B.Tech-ICT"));
        students.add(new Student("Sourabh", 1741086, "B.Tech-ICT"));
        students.add(new Student("Khushi", 1741052, "B.Tech-ICT"));

        boolean running = true;
        while (running) {
            System.out.println("\n.....................................");
            System.out.println("1. Librarian Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            System.out.println(".....................................");
            System.out.print("Enter Your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                sc.next();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> librarianMenu(sc, library);
                case 2 -> userMenu(sc, library, students);
                case 3 -> running = false;
                default -> System.out.println("Invalid choice!");
            }
        }
        sc.close();
    }

    private static void librarianMenu(Scanner sc, Library library) {
        System.out.print("Enter UserId: ");
        String id = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        if (!(id.equals("librarian") && password.equals("password"))) {
            System.out.println("Invalid login credentials!");
            return;
        }

        boolean librarianLoggedIn = true;
        while (librarianLoggedIn) {
            System.out.println("\n--- Librarian Menu ---");
            System.out.println("1. Add book");
            System.out.println("2. Delete book");
            System.out.println("3. Update book");
            System.out.println("4. Print Books Details");
            System.out.println("5. Print Books in-order");
            System.out.println("6. Print tree");
            System.out.println("7. Exit");
            System.out.print("Enter Your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                sc.next();
                continue;
            }

            int librarianChoice = sc.nextInt();
            sc.nextLine();

            switch (librarianChoice) {
                case 1 -> {
                    System.out.print("Enter name of book: ");
                    String bookName = sc.nextLine();
                    System.out.print("Enter quantity of book: ");
                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid quantity!");
                        sc.next();
                        continue;
                    }
                    int quantity = sc.nextInt();
                    sc.nextLine();
                    library.addBook(bookName, quantity);
                }
                case 2 -> {
                    System.out.print("Enter name of book: ");
                    String bookName = sc.nextLine();
                    library.deleteBook(bookName);
                }
                case 3 -> {
                    System.out.print("Enter name of book: ");
                    String bookName = sc.nextLine();
                    System.out.print("Enter quantity of book: ");
                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid quantity!");
                        sc.next();
                        continue;
                    }
                    int quantity = sc.nextInt();
                    sc.nextLine();
                    library.updateBook(bookName, quantity);
                }
                case 4 -> library.printBookDetails();
                case 5 -> library.printInorder();
                case 6 -> library.printTree();
                case 7 -> librarianLoggedIn = false;
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void userMenu(Scanner sc, Library library, List<Student> students) {
        System.out.print("Enter UserId: ");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid UserId!");
            sc.next();
            return;
        }

        int userId = sc.nextInt();
        sc.nextLine();

        for (Student student : students) {
            if (student.id_no == userId) {
                boolean userLoggedIn = true;
                while (userLoggedIn) {
                    System.out.println("\n--- User Menu ---");
                    System.out.println("1. Issue book");
                    System.out.println("2. Return book");
                    System.out.println("3. Exit");
                    System.out.print("Enter Your choice: ");

                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid choice!");
                        sc.next();
                        continue;
                    }

                    int userChoice = sc.nextInt();
                    sc.nextLine();

                    switch (userChoice) {
                        case 1 -> {
                            System.out.print("Enter name of book: ");
                            String bookName = sc.nextLine();
                            library.issueBook(student, bookName);
                        }
                        case 2 -> {
                            System.out.print("Enter name of book: ");
                            String bookName = sc.nextLine();
                            library.returnBook(student, bookName);
                        }
                        case 3 -> userLoggedIn = false;
                        default -> System.out.println("Invalid choice!");
                    }
                }
                return;
            }
        }
        System.out.println("User not found!");
    }
}
