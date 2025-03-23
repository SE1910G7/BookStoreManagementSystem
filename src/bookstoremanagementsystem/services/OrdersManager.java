/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.services;

import bookstoremanagementsystem.interfaces.IOrders;
import bookstoremanagementsystem.models.Orders;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nhulnt
 */
public class OrdersManager implements IOrders {

    private List<Orders> orderList = new ArrayList<>();
    private final String orderFile = "orders.txt";

    @Override
    public void loadOrders() {
        orderList.clear();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(orderFile))) {
            Orders order;
            while ((order = (Orders) ois.readObject()) != null) {
                orderList.add(order);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Order file not found. Initializing empty list.");
        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("Error loading orders: " + e.getMessage());
        }
    }

    @Override
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(orderFile))) {
            for (Orders order : orderList) {
                oos.writeObject(order);
            }
        } catch (IOException e) {
            System.out.println("Error saving orders: " + e.getMessage());
        }
    }

    @Override
    public void createOrder(Orders order) {
        loadOrders();
        orderList.add(order);
        saveToFile();
        System.out.printf("%10sOrder created successfully!\n", "");
    }

    @Override
    public List<Orders> getOrderList() {
        loadOrders();
        return new ArrayList<>(orderList);
    }

    @Override
    public Orders getOrderById(String orderId) {
        loadOrders();
        for (Orders order : orderList) {
            if (order.getOrderId().equalsIgnoreCase(orderId)) {
                return order;
            }
        }
        return null;
    }

    @Override
    public void updateOrderStatus(String orderId, String newStatus) {
        loadOrders();
        for (Orders order : orderList) {
            if (order.getOrderId().equalsIgnoreCase(orderId)) {
                order.setStatusId(newStatus);
                saveToFile();
                System.out.printf("%10sOrder status updated successfully!\n", "");
                return;
            }
        }
        System.out.printf("%10sOrder not found!\n", "");
    }

    @Override
    public void deleteOrder(String orderId) {
        loadOrders();
        boolean removed = orderList.removeIf(order -> order.getOrderId().equalsIgnoreCase(orderId));
        if (removed) {
            saveToFile();
            System.out.printf("%10sOrder deleted successfully!\n", "");
        } else {
            System.out.printf("%10sOrder not found!\n", "");
        }
    }

    @Override
    public void showOrderList() {
        loadOrders();
        System.out.printf("\n\n%10s*************************************************************\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*--------------------!! ORDER LIST !!----------------------*\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*************************************************************\n", "");
        System.out.printf("%10s| %-6s | %-10s | %-15s | %-15s |\n", "", "ID", "Customer", "Order Date", "Status");
        System.out.printf("%10s-------------------------------------------------------------\n", "");

        if (orderList.isEmpty()) {
            System.out.printf("%10sNo orders found!\n", "");
            return;
        }

        for (Orders order : orderList) {
            String orderDate = order.getOrderDate().toString();
            String statusName = order.getStatusId();
            String bookOrders = order.getBooksOrder().length() > 30 ? order.getBooksOrder().substring(0, 27) + "..." : order.getBooksOrder();

            System.out.printf("%10s| %-6s | %-10s | %-15s | %-15s |\n", "", order.getOrderId(), order.getCustomerId(), orderDate, statusName);
            System.out.printf("%10s|        | Books Ordered: %-30s %6s |\n", "", bookOrders, "");
            System.out.printf("%10s-------------------------------------------------------------\n", "");
        }
    }

    @Override
    public void showOrderDetails(Orders order) {
        System.out.printf("\n\n%10s*************************************************************\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*-------------------!! ORDER DETAILS !!-------------------*\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*************************************************************\n", "");

        System.out.printf("%10s| %-15s: %-40s |\n", "", "Order ID", order.getOrderId());
        System.out.printf("%10s| %-15s: %-40s |\n", "", "Customer ID", order.getCustomerId());

        System.out.printf("%10s| %-15s: %-40s |\n", "", "Order Date", order.getOrderDate());

        System.out.printf("%10s| %-15s: %-40s |\n", "", "Status", order.getStatusId());

        System.out.printf("%10s-------------------------------------------------------------\n", "");
        System.out.printf("%10s| %-15s %42s|\n", "", "Books Ordered", "");
        System.out.printf("%10s-------------------------------------------------------------\n", "");

        String[] booksOrdered = order.getBooksOrder().split(", ");
        for (String bookId : booksOrdered) {
            System.out.printf("%10s| %-50s |\n", "", bookId);
        }

        System.out.printf("%10s*************************************************************\n", "");
    }
}
