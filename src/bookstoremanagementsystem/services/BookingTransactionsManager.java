/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.services;

import bookstoremanagementsystem.interfaces.CRUDBookingTransactions;
import java.io.*;
import java.util.*;

/**
 *
 * @author DELL
 */
public class BookingTransactionsManager implements CRUDBookingTransactions {
    private final List<String> bookings = new ArrayList<>();
    private final String dataFile = "bookings.txt";

    public BookingTransactionsManager() {
        loadBookings();
    }

    private void loadBookings() {
        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                bookings.add(line);
            }
        } catch (IOException e) {
            System.out.println("No existing booking data found.");
        }
    }

    private void saveBookings() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile))) {
            for (String booking : bookings) {
                bw.write(booking + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving booking data.");
        }
    }

    @Override
    public void addBooking(String id, String details, StringBuilder outputBuffer) {
        String booking = id + " - " + details;
        bookings.add(booking);
        saveBookings();
        outputBuffer.append("Added Booking: ").append(booking).append("\n");
    }

    @Override
    public void viewBookings(StringBuilder outputBuffer) {
        outputBuffer.append("--- Booking Transactions ---\n");
        for (String booking : bookings) {
            outputBuffer.append(booking).append("\n");
        }
        outputBuffer.append("----------------------------\n");
    }

    @Override
    public void deleteBooking(String id, StringBuilder outputBuffer) {
        bookings.removeIf(booking -> booking.startsWith(id + " -"));
        saveBookings();
        outputBuffer.append("Deleted Booking: ").append(id).append("\n");
    }
}
