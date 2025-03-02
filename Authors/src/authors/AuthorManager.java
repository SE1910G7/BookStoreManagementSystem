package authors;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Scanner;

public class AuthorManager {
    static Scanner scanner = new Scanner(System.in);
    static List<Authors> authorsList = new ArrayList<>();
    static String filePath = "authors.txt";

    public static void main(String[] args) {
        loadAuthorsFromFile();
        int choice;
        do {
            System.out.println("**************************************");
            System.out.println("!! AUTHOR !!");
            System.out.println("1. Add Author");
            System.out.println("2. Display Authors");
            System.out.println("3. Sort by Author Name");
            System.out.println("4. Search Author by Name");
            System.out.println("5. Exit");
            System.out.println("Enter Option 1-5: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addAuthor();
                    break;
                case 2:
                    displayAuthors();
                    break;
                case 3:
                    sortAuthorsByName();
                    break;
                case 4:
                    searchAuthorByName();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    saveAuthorsToFile();
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 5);
    }

    static void addAuthor() {
        System.out.print("Enter Author ID: ");
        String authorID = scanner.nextLine();
        System.out.print("Enter Author Full Name: ");
        String fullName = scanner.nextLine();

        authorsList.add(new Authors(authorID, fullName));
        System.out.println("Author added successfully!");
    }

    static void displayAuthors() {
        if (authorsList.isEmpty()) {
            System.out.println("No authors available.");
            return;
        }
        System.out.println("**************************************");
        System.out.println("!! AUTHORS LIST !!");
        System.out.printf("%-10s %-30s\n", "Author ID", "Full Name");
        System.out.println("-------------------------------------------------------");
        authorsList.forEach((author) -> {
            System.out.printf("%-10s %-30s\n", author.getAuthorID(), author.getFullName());
        });
    }

    static void sortAuthorsByName() {
        authorsList.sort(Comparator.comparing(Authors::getFullName));
        System.out.println("Authors sorted by name!");
        displayAuthors();
    }

    static void searchAuthorByName() {
        System.out.print("Enter Author Name to search: ");
        String searchName = scanner.nextLine();
        boolean found = false;
        for (Authors author : authorsList) {
            if (author.getFullName().equalsIgnoreCase(searchName)) {
                System.out.printf("Found - Author ID: %s, Full Name: %s\n", author.getAuthorID(), author.getFullName());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Author not found.");
        }
    }

    // Lưu danh sách tác giả vào file (Text format)
    static void saveAuthorsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Authors author : authorsList) {
                writer.write(author.getAuthorID() + "," + author.getFullName());
                writer.newLine(); // Thêm dòng mới sau mỗi tác giả
            }
            System.out.println("Authors saved to file!");
        } catch (IOException e) {
            System.err.println("Error saving authors to file: " + e.getMessage());
        }
    }

    // Đọc danh sách tác giả từ file (Text format)
    static void loadAuthorsFromFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found. Starting with an empty list.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String authorID = parts[0].trim(); // Loại bỏ khoảng trắng thừa
                    String fullName = parts[1].trim(); // Loại bỏ khoảng trắng thừa
                    authorsList.add(new Authors(authorID, fullName));
                } else {
                    System.err.println("Invalid line in authors file: " + line);
                }
            }
            System.out.println("Authors loaded from file!");
        } catch (IOException e) {
            System.err.println("Error loading authors from file: " + e.getMessage());
        }
    }
}