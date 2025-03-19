/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.services;


import bookstoremanagementsystem.interfaces.IGenres;
import bookstoremanagementsystem.models.Genres;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author Admin Lenovo
 */



public class GenresManager implements IGenres {

    private final List<Genres> genresList = new ArrayList<>();
    private final String filePath = "genres.txt";
    private final String logFilePath = "log.txt";
    private final int pageSize = 5;

    public GenresManager() {
        loadGenresFromFile();
        if (genresList.isEmpty()) {
            System.out.println("No genres found in file. Please add new genres.");
        }
    }

    
    public void addGenre(String genreID, String genreName, String description) {
        genresList.add(new Genres(genreID, genreName, description));
        System.out.println("Genre added successfully!");
        logChange("ADD", genreID, genreName);
        saveGenresToFile();
    }

 
    public void displayGenres() {
        int totalGenres = genresList.size();
        if (totalGenres == 0) {
            System.out.println("No genres available.");
            return;
        }

        int totalPages = (int) Math.ceil((double) totalGenres / pageSize);
        Scanner scanner = new Scanner(System.in);
        int currentPage = 1;

        while (true) {
            System.out.println("**************************************");
            System.out.println("!! GENRES LIST !! (Page " + currentPage + " of " + totalPages + ")");
            System.out.printf("%-10s %-20s %-30s\n", "Genre ID", "Genre Name", "Description");
            System.out.println("-------------------------------------------------------");

            int startIndex = (currentPage - 1) * pageSize;
            int endIndex = Math.min(startIndex + pageSize, totalGenres);

            for (int i = startIndex; i < endIndex; i++) {
                Genres genre = genresList.get(i);
                System.out.printf("%-10s %-20s %-30s\n", genre.getGenreId(), genre.getGenreName(), genre.getDescription());
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

    public void sortGenres() {
        genresList.sort(Comparator.comparing(Genres::getGenreName));
        System.out.println("Genres sorted by name!");
        displayGenres();
    }

 
    public void searchGenre(String searchInput) {
        boolean found = false;
        for (Genres genre : genresList) {
            if (genre.getGenreName().equalsIgnoreCase(searchInput) || 
                genre.getGenreId().equalsIgnoreCase(searchInput)) {
                System.out.printf("Found - Genre ID: %s, Name: %s, Description: %s\n", 
                    genre.getGenreId(), genre.getGenreName(), genre.getDescription());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Genre not found.");
        }
    }

    @Override
    public void deleteGenre(String searchInput) {
        boolean found = false;
        Iterator<Genres> iterator = genresList.iterator();
        while (iterator.hasNext()) {
            Genres genre = iterator.next();
            if (genre.getGenreName().equalsIgnoreCase(searchInput) || 
                genre.getGenreId().equalsIgnoreCase(searchInput)) {
                String genreID = genre.getGenreId();
                String genreName = genre.getGenreName();
                iterator.remove();
                System.out.printf("Deleted - Genre ID: %s, Name: %s\n", genreID, genreName);
                found = true;
                logChange("DELETE", genreID, genreName);
            }
        }
        if (!found) {
            System.out.println("Genre not found.");
        } else {
            saveGenresToFile();
        }
    }

    public void saveGenresToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Genres genre : genresList) {
                writer.write(genre.getGenreId() + "," + genre.getGenreName() + "," + genre.getDescription());
                writer.newLine();
            }
            System.out.println("Genres saved to file!");
        } catch (IOException e) {
            System.err.println("Error saving genres to file: " + e.getMessage());
        }
    }

    public void loadGenresFromFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found: " + filePath + ". Create new on save");
            return;
        }
        if (file.length() == 0) {
            System.out.println("File " + filePath + " Empty. No genres loaded.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    genresList.add(new Genres(parts[0].trim(), parts[1].trim(), parts[2].trim()));
                } else {
                    System.err.println("Invalid line in file " + filePath + ": " + line);
                }
            }
            System.out.println("Genres loaded from file!");
        } catch (IOException e) {
            System.err.println("Error loading genres from file: " + e.getMessage());
        }
    }

    public void editGenre(String searchInput, String newGenreID, String newGenreName, String newDescription) {
        boolean found = false;
        for (Genres genre : genresList) {
            if (genre.getGenreName().equalsIgnoreCase(searchInput) || 
                genre.getGenreId().equalsIgnoreCase(searchInput)) {
                String oldGenreID = genre.getGenreId();
                String oldGenreName = genre.getGenreName();
                genre.setGenreId(newGenreID);
                genre.setGenreName(newGenreName);
                genre.setDescription(newDescription);
                System.out.printf("Updated - Genre ID: %s, Name: %s to Genre ID: %s, Name: %s\n", 
                    oldGenreID, oldGenreName, newGenreID, newGenreName);
                found = true;
                logChange("EDIT", oldGenreID, oldGenreName, newGenreID, newGenreName);
                break; // Chỉ sửa một thể loại
            }
        }
        if (!found) {
            System.out.println("Genre not found.");
        } else {
            saveGenresToFile();
        }
    }

    private void logChange(String action, String oldGenreID, String oldGenreName, String newGenreID, String newGenreName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String timestamp = formatter.format(date);

            String logMessage = String.format("%s - %s: Genre ID: %s, Name: %s -> Genre ID: %s, Name: %s%n",
                    timestamp, action, oldGenreID, oldGenreName, newGenreID, newGenreName);
            writer.write(logMessage);
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    private void logChange(String action, String genreID, String genreName) {
        logChange(action, genreID, genreName, null, null);
    }
}