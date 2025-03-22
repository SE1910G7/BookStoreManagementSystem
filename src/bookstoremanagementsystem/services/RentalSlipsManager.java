/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.services;

import bookstoremanagementsystem.interfaces.IRentalSlips;
import bookstoremanagementsystem.models.RentalSlips;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mummykiara
 */
public class RentalSlipsManager implements IRentalSlips {

    private List<RentalSlips> rentalSlipsList = new ArrayList<>();
    private final String rentalFile = "rentalSlips.txt";

    @Override
    public void loadRentalSlips() {
        rentalSlipsList.clear();
        try (FileInputStream fi = new FileInputStream(rentalFile);
                ObjectInputStream oi = new ObjectInputStream(fi)) {
            RentalSlips slip;
            while ((slip = (RentalSlips) oi.readObject()) != null) {
                rentalSlipsList.add(slip);
            }
        } catch (FileNotFoundException e) {
//            System.out.println("No rental slips file found. Creating a new one.");
        } catch (IOException | ClassNotFoundException e) {

        }
    }

    @Override
    public void saveToFile() {
        try (FileOutputStream fo = new FileOutputStream(rentalFile);
                ObjectOutputStream oo = new ObjectOutputStream(fo)) {
            for (RentalSlips slip : rentalSlipsList) {
                oo.writeObject(slip);
            }
        } catch (IOException e) {
            System.out.println("Error saving rental slips: " + e.getMessage());
        }
    }

    @Override
    public void createRentalSlip(RentalSlips slip) {
        rentalSlipsList.add(slip);
        saveToFile();
        System.out.printf("%10sRental slip created successfully!", "");
    }

    @Override
    public void updateRentalStatus(String rentFormId, String newStatus) {
        for (RentalSlips slip : rentalSlipsList) {
            if (slip.getRentFormId().equals(rentFormId)) {
                slip.setRentStatus(newStatus);
                saveToFile();
                System.out.printf("%10sRental status updated successfully!", "");
                return;
            }
        }
        System.out.printf("%10sRental slip not found!", "");
    }

    @Override
    public void deleteRentalSlip(String rentFormId) {
        for (int i = 0; i < rentalSlipsList.size(); i++) {
            if (rentalSlipsList.get(i).getRentFormId().equals(rentFormId)) {
                rentalSlipsList.remove(i);
                saveToFile();
                System.out.printf("%10sRental slip deleted successfully!", "");
                return;
            }
        }
        System.out.printf("%10sRental slip not found!", "");
    }

    @Override
    public void showRentalSlips() {
        loadRentalSlips();

        System.out.printf("\n\n%10s*************************************************************\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*--------------------!! RENTAL SLIP LIST !!-----------------*\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*************************************************************\n", "");
        System.out.printf("%10s| %-10s | %-10s | %-10s | %-12s | %-12s | %-8s |\n", "", "Rent ID", "Account", "Book ID", "Start Date", "End Date", "Status");
        System.out.printf("%10s---------------------------------------------------------------------\n", "");

        if (rentalSlipsList.isEmpty()) {
            System.out.printf("%10sNo rental slips found!\n", "");
            return;
        }

        for (RentalSlips slip : rentalSlipsList) {
            String startDate = slip.getStartRentBookDate().toString();
            String endDate = slip.getEndRentBookDate().toString();
            String status = slip.getRentStatus();

            System.out.printf("%10s| %-10s | %-10s | %-10s | %-12s | %-12s | %-8s |\n", "",
                    slip.getRentFormId(), slip.getAccountId(), slip.getBookId(), startDate, endDate, status);
            System.out.printf("%10s---------------------------------------------------------------------\n", "");
        }
    }

    @Override
    public RentalSlips getRentalSlipById(String rentFormId) {
        loadRentalSlips();
        for (RentalSlips slip : rentalSlipsList) {
            if (slip.getRentFormId().equals(rentFormId)) {
                return slip;
            }
        }
        return null;
    }

    @Override
    public void showRentalSlipDetails(RentalSlips slip) {
        System.out.printf("\n\n%10s*************************************************************\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*-----------------!! RENTAL SLIP DETAILS !!----------------*\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*************************************************************\n", "");

        System.out.printf("%10s| %-15s: %-40s |\n", "", "Rental ID", slip.getRentFormId());
        System.out.printf("%10s| %-15s: %-40s |\n", "", "Account ID", slip.getAccountId());
        System.out.printf("%10s| %-15s: %-40s |\n", "", "Book ID", slip.getBookId());
        System.out.printf("%10s| %-15s: %-40s |\n", "", "Start Date", slip.getStartRentBookDate());
        System.out.printf("%10s| %-15s: %-40s |\n", "", "End Date", slip.getEndRentBookDate());
        System.out.printf("%10s| %-15s: %-40s |\n", "", "Status", slip.getRentStatus());

        System.out.printf("%10s*************************************************************\n", "");
    }

}
