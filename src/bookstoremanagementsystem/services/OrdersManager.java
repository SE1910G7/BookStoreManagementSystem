/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.services;

import bookstoremanagementsystem.models.Orders;
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
public class OrdersManager {
    private List<Orders> ordersList;
    private static final String FILE_NAME = "orders.dat";
    
    public OrdersManager() {
        loadFromFile();
    }
    
    public void addOrder(Orders order) {
        ordersList.add(order);
        saveToFile();
    }
    
    public void displayOrders() {
        ordersList.forEach(System.out::println);
    }
    
    public void updateOrder(String orderId, Orders updatedOrder) {
        for (int i = 0; i < ordersList.size(); i++) {
            if (ordersList.get(i).getOrderId().equals(orderId)) {
                ordersList.set(i, updatedOrder);
                saveToFile();
                return;
            }
        }
    }
    
    public void deleteOrder(String orderId) {
        ordersList.removeIf(order -> order.getOrderId().equals(orderId));
        saveToFile();
    }
    
    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(ordersList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            ordersList = (List<Orders>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            ordersList = new ArrayList<>();
        }
    }
}