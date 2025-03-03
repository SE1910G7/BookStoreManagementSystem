package Services;

import Interfaces.IAuthors;
import bookstoremanagementsystem.models.Authors;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 *
 * @author DELL
 */
public final class AuthorManager implements IAuthors {
    private final List<Authors> authorsList = new ArrayList<>();
    private final String filePath = "authors.txt";
    private final String logFilePath = "log.txt";
    private final int pageSize = 5; // Số lượng tác giả trên mỗi trang

    public AuthorManager() {
        loadAuthorsFromFile();
        if (authorsList.isEmpty()) {
            System.out.println("No authors found in file. Please add new authors.");
        }
    }

    @Override
    public void addAuthor(String authorID, String fullName) {
        authorsList.add(new Authors(authorID, fullName));
        System.out.println("Author added successfully!");
        logChange("ADD", authorID, fullName);
        saveAuthorsToFile();
    }

    @Override
    public void displayAuthors() {
        int totalAuthors = authorsList.size();
        if (totalAuthors == 0) {
            System.out.println("No authors available.");
            return;
        }

        int totalPages = (int) Math.ceil((double) totalAuthors / pageSize);
        Scanner scanner = new Scanner(System.in);
        int currentPage = 1;

        while (true) {
            System.out.println("**************************************");
            System.out.println("!! AUTHORS LIST !! (Page " + currentPage + " of " + totalPages + ")");
            System.out.printf("%-10s %-30s\n", "Author ID", "Full Name");
            System.out.println("-------------------------------------------------------");

            int startIndex = (currentPage - 1) * pageSize;
            int endIndex = Math.min(startIndex + pageSize, totalAuthors);

            for (int i = startIndex; i < endIndex; i++) {
                Authors author = authorsList.get(i);
                System.out.printf("%-10s %-30s\n", author.getAuthorID(), author.getFullName());
            }

            System.out.println("-------------------------------------------------------");
            System.out.println("N: Next Page, P: Previous Page, Q: Quit");
            System.out.print("Enter option: ");
            String option = scanner.nextLine().toUpperCase();

            switch (option) {
                case "N":
                    if (currentPage < totalPages) {
                        currentPage++;
                    } else {
                        System.out.println("This is the last page.");
                    }
                    break;
                case "P":
                    if (currentPage > 1) {
                        currentPage--;
                    } else {
                        System.out.println("This is the first page.");
                    }
                    break;
                case "Q":
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    @Override
    public void sortAuthors() {
        authorsList.sort(Comparator.comparing(Authors::getFullName));
        System.out.println("Authors sorted by name!");
        displayAuthors();
    }

    @Override
    public void searchAuthor(String searchInput) {
        boolean found = false;
        for (Authors author : authorsList) {
            if (author.getFullName().equalsIgnoreCase(searchInput) || author.getAuthorID().equalsIgnoreCase(searchInput)) {
                System.out.printf("Found - Author ID: %s, Full Name: %s\n", author.getAuthorID(), author.getFullName());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Author not found.");
        }
    }

    @Override
    public void deleteAuthor(String searchInput) {
        boolean found = false;
        Iterator<Authors> iterator = authorsList.iterator();
        while (iterator.hasNext()) {
            Authors author = iterator.next();
            if (author.getFullName().equalsIgnoreCase(searchInput) || author.getAuthorID().equalsIgnoreCase(searchInput)) {
                String authorID = author.getAuthorID();
                String fullName = author.getFullName();
                iterator.remove();
                System.out.printf("Deleted - Author ID: %s, Full Name: %s\n", authorID, fullName);
                found = true;
                logChange("DELETE", authorID, fullName);
            }
        }
        if (!found) {
            System.out.println("Author not found.");
        } else {
            saveAuthorsToFile();
        }
    }

    @Override
    public void saveAuthorsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Authors author : authorsList) {
                writer.write(author.getAuthorID() + "," + author.getFullName());
                writer.newLine();
            }
            System.out.println("Authors saved to file!");
        } catch (IOException e) {
            System.err.println("Error saving authors to file: " + e.getMessage());
        }
    }

    @Override
    public void loadAuthorsFromFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found: " + filePath + ". Create new on save");
            return;
        }
        if (file.length() == 0) {
            System.out.println("File " + filePath + " Empty. No authors loaded.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    authorsList.add(new Authors(parts[0].trim(), parts[1].trim()));
                } else {
                    System.err.println("Invalid line in file " + filePath + ": " + line);
                }
            }
            System.out.println("Authors loaded from file!");
        } catch (IOException e) {
            System.err.println("Error loading authors from file: " + e.getMessage());
        }
    }

    @Override
    public void editAuthor(String searchInput, String newAuthorID, String newFullName) {
        boolean found = false;
        for (Authors author : authorsList) {
            if (author.getFullName().equalsIgnoreCase(searchInput) || author.getAuthorID().equalsIgnoreCase(searchInput)) {
                String oldAuthorID = author.getAuthorID();
                String oldFullName = author.getFullName();
                author.setAuthorID(newAuthorID);
                author.setFullName(newFullName);
                System.out.printf("Updated - Author ID: %s, Full Name: %s to Author ID: %s, Full Name: %s\n", oldAuthorID, oldFullName, newAuthorID, newFullName);
                found = true;
                logChange("EDIT", oldAuthorID, oldFullName, newAuthorID, newFullName);
                break; // Chỉ sửa một tác giả
            }
        }
        if (!found) {
            System.out.println("Author not found.");
        } else {
            saveAuthorsToFile();
        }
    }

    private void logChange(String action, String oldAuthorID, String oldFullName, String newAuthorID, String newFullName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String timestamp = formatter.format(date);

            String logMessage = String.format("%s - %s: Author ID: %s, Full Name: %s -> Author ID: %s, Full Name: %s%n",
                    timestamp, action, oldAuthorID, oldFullName, newAuthorID, newFullName);
            writer.write(logMessage);
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    private void logChange(String action, String authorID, String fullName) {
        logChange(action, authorID, fullName, null, null);
    }
}