/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.services;

import bookstoremanagementsystem.interfaces.IRentalSlips;
import bookstoremanagementsystem.models.RentalSlips;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author PC
 */
public class RentalSlipsManager implements IRentalSlips {

    private final List<RentalSlips> rentalSlipsList = new ArrayList<>();
    private final String filePath = "rentalslips.txt";
    private final int pageSize = 5;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public RentalSlipsManager() {
        loadRentalSlipsFromFile();
        if (rentalSlipsList.isEmpty()) {
            System.out.println("No rental slips found in file. Please add new rental slips.");
        }
    }

    @Override
    public void addRentalSlip(RentalSlips rentalSlip) {
        rentalSlipsList.add(rentalSlip);
        System.out.println("Rental Slip added successfully!");
        saveRentalSlipsToFile();
    }

    @Override
    public void displayRentalSlips() {
        int totalRentalSlips = rentalSlipsList.size();
        if (totalRentalSlips == 0) {
            System.out.println("No rental slips available.");
            return;
        }

        int totalPages = (int) Math.ceil((double) totalRentalSlips / pageSize);
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int currentPage = 1;

        while (true) {
            System.out.println("**************************************");
            System.out.println("!! RENTAL SLIPS LIST !! (Page " + currentPage + " of " + totalPages + ")");
            System.out.printf("%-12s %-12s %-12s %-15s %-15s %-15s %-10s\n", 
                "Rent Form ID", "Account ID", "Book ID", "Start Date", "End Date", "Created At", "Total Price");
            System.out.println("-------------------------------------------------------------------------------");

            int startIndex = (currentPage - 1) * pageSize;
            int endIndex = Math.min(startIndex + pageSize, totalRentalSlips);

            for (int i = startIndex; i < endIndex; i++) {
                RentalSlips rentalSlip = rentalSlipsList.get(i);
                System.out.printf("%-12s %-12s %-12s %-15s %-15s %-15s %-10.2f\n",
                    rentalSlip.getRentFormId(), rentalSlip.getAccountId(), rentalSlip.getBookId(),
                    rentalSlip.getStartRentBookDate(), rentalSlip.getEndRentBookDate(),
                    rentalSlip.getCreatedAt(), rentalSlip.getTotalPrice());
            }

            System.out.println("-------------------------------------------------------------------------------");
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
    public void sortRentalSlips() {
        rentalSlipsList.sort(Comparator.comparing(RentalSlips::getCreatedAt));
        System.out.println("Rental Slips sorted by creation date!");
        displayRentalSlips();
    }

    @Override
    public void searchRentalSlip(String searchInput) {
        boolean found = false;
        for (RentalSlips rentalSlip : rentalSlipsList) {
            if (rentalSlip.getRentFormId().equalsIgnoreCase(searchInput) ||
                rentalSlip.getAccountId().equalsIgnoreCase(searchInput) ||
                rentalSlip.getBookId().equalsIgnoreCase(searchInput)) {
                System.out.printf("Found - Rent Form ID: %s, Account ID: %s, Book ID: %s, " +
                                  "Start Date: %s, End Date: %s, Created At: %s, Total Price: %.2f\n",
                    rentalSlip.getRentFormId(), rentalSlip.getAccountId(), rentalSlip.getBookId(),
                    rentalSlip.getStartRentBookDate(), rentalSlip.getEndRentBookDate(),
                    rentalSlip.getCreatedAt(), rentalSlip.getTotalPrice());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Rental Slip not found.");
        }
    }

    @Override
    public void deleteRentalSlip(String searchInput) {
        boolean found = false;
        Iterator<RentalSlips> iterator = rentalSlipsList.iterator();
        while (iterator.hasNext()) {
            RentalSlips rentalSlip = iterator.next();
            if (rentalSlip.getRentFormId().equalsIgnoreCase(searchInput) ||
                rentalSlip.getAccountId().equalsIgnoreCase(searchInput) ||
                rentalSlip.getBookId().equalsIgnoreCase(searchInput)) {
                iterator.remove();
                System.out.printf("Deleted - Rent Form ID: %s\n", rentalSlip.getRentFormId());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Rental Slip not found.");
        } else {
            saveRentalSlipsToFile();
        }
    }

    @Override
    public void editRentalSlip(String searchInput, RentalSlips newRentalSlip) {
        boolean found = false;
        for (RentalSlips rentalSlip : rentalSlipsList) {
            if (rentalSlip.getRentFormId().equalsIgnoreCase(searchInput) ||
                rentalSlip.getAccountId().equalsIgnoreCase(searchInput) ||
                rentalSlip.getBookId().equalsIgnoreCase(searchInput)) {
                String oldRentFormId = rentalSlip.getRentFormId();
                rentalSlip.setRentFormId(newRentalSlip.getRentFormId());
                rentalSlip.setAccountId(newRentalSlip.getAccountId());
                rentalSlip.setBookId(newRentalSlip.getBookId());
                rentalSlip.setStartRentBookDate(newRentalSlip.getStartRentBookDate());
                rentalSlip.setEndRentBookDate(newRentalSlip.getEndRentBookDate());
                rentalSlip.setCreatedAt(newRentalSlip.getCreatedAt());
                rentalSlip.setTotalPrice(newRentalSlip.getTotalPrice());
                System.out.printf("Updated - Rent Form ID: %s to Rent Form ID: %s\n", 
                    oldRentFormId, newRentalSlip.getRentFormId());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Rental Slip not found.");
        } else {
            saveRentalSlipsToFile();
        }
    }

    @Override
    public void saveRentalSlipsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (RentalSlips rentalSlip : rentalSlipsList) {
                writer.write(rentalSlip.getRentFormId() + "," +
                            rentalSlip.getAccountId() + "," +
                            rentalSlip.getBookId() + "," +
                            rentalSlip.getStartRentBookDate().format(dateFormatter) + "," +
                            rentalSlip.getEndRentBookDate().format(dateFormatter) + "," +
                            rentalSlip.getCreatedAt().format(dateFormatter) + "," +
                            rentalSlip.getTotalPrice());
                writer.newLine();
            }
            System.out.println("Rental Slips saved to file!");
        } catch (IOException e) {
            System.err.println("Error saving rental slips to file: " + e.getMessage());
        }
    }

    @Override
    public void loadRentalSlipsFromFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found: " + filePath + ". Will create new on save.");
            return;
        }
        if (file.length() == 0) {
            System.out.println("File " + filePath + " is empty. No rental slips loaded.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    rentalSlipsList.add(new RentalSlips(
                        parts[0].trim(), // rentFormId
                        parts[1].trim(), // accountId
                        parts[2].trim(), // bookId
                        LocalDate.parse(parts[3].trim(), dateFormatter), // startRentBookDate
                        LocalDate.parse(parts[4].trim(), dateFormatter), // endRentBookDate
                        LocalDate.parse(parts[5].trim(), dateFormatter), // createAt
                        Double.parseDouble(parts[6].trim()) // totalPrice
                    ));
                } else {
                    System.err.println("Invalid line in file " + filePath + ": " + line);
                }
            }
            System.out.println("Rental Slips loaded from file!");
        } catch (IOException e) {
            System.err.println("Error loading rental slips from file: " + e.getMessage());
        }
    }
}
