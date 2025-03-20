/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.services;

import bookstoremanagementsystem.models.OrderStatus;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class OrderStatusManager {
    private List<OrderStatus> statusList = new ArrayList<>();
    private static final String FILE_NAME = "order_status.dat";
    
    public void addStatus(OrderStatus status) {
        statusList.add(status);
        saveToFile();
    }
    
    public void displayStatuses() {
        statusList.forEach(System.out::println);
    }
    
    public void updateStatus(String statusId, OrderStatus updatedStatus) {
        for (int i = 0; i < statusList.size(); i++) {
            if (statusList.get(i).getStatusId().equals(statusId)) {
                statusList.set(i, updatedStatus);
                saveToFile();
                return;
            }
        }
    }
    
    public void deleteStatus(String statusId) {
        statusList.removeIf(status -> status.getStatusId().equals(statusId));
        saveToFile();
    }
    
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(statusList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            statusList = (List<OrderStatus>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            statusList = new ArrayList<>();
        }
    }
}
