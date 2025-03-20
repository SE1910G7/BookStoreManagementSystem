/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.services;

import bookstoremanagementsystem.interfaces.CRUDRentalSlipTransactions;
import java.io.*;
import java.util.*;

/**
 *
 * @author DELL
 */
public class RentalSlipTransactionsManager implements CRUDRentalSlipTransactions {
    private final List<String> rentalSlips = new ArrayList<>();
    private final String dataFile = "rental_slips.txt";

    public RentalSlipTransactionsManager() {
        loadRentalSlips();
    }

    private void loadRentalSlips() {
        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                rentalSlips.add(line);
            }
        } catch (IOException e) {
            System.out.println("No existing rental slip data found.");
        }
    }

    private void saveRentalSlips() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile))) {
            for (String slip : rentalSlips) {
                bw.write(slip + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving rental slip data.");
        }
    }

    @Override
    public void addRentalSlip(String id, String details, StringBuilder outputBuffer) {
        String slip = id + " - " + details;
        rentalSlips.add(slip);
        saveRentalSlips();
        outputBuffer.append("Added Rental Slip: ").append(slip).append("\n");
    }

    @Override
    public void viewRentalSlips(StringBuilder outputBuffer) {
        outputBuffer.append("--- Rental Slip Transactions ---\n");
        for (String slip : rentalSlips) {
            outputBuffer.append(slip).append("\n");
        }
        outputBuffer.append("--------------------------------\n");
    }

    @Override
    public void deleteRentalSlip(String id, StringBuilder outputBuffer) {
        rentalSlips.removeIf(slip -> slip.startsWith(id + " -"));
        saveRentalSlips();
        outputBuffer.append("Deleted Rental Slip: ").append(id).append("\n");
    }
}
