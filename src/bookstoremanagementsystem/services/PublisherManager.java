/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.services;

import bookstoremanagementsystem.interfaces.IPublishers;
import bookstoremanagementsystem.models.Publishers;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nhulnt
 */
public class PublisherManager implements IPublishers {

    private List<Publishers> publisherList = new ArrayList<>();
    private final String publisherFile = "publishers.txt";

    @Override
    public void loadPublishers() {
        publisherList.clear();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(publisherFile))) {
            while (true) {
                try {
                    Publishers publisher = (Publishers) ois.readObject();
                    publisherList.add(publisher);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("Error loading publishers: " + e.getMessage());
        }
    }

    @Override
    public void addPublisher(Publishers publisher) {
        loadPublishers();
        publisherList.add(publisher);
        saveToFile();
        System.out.printf("%10sPublisher added successfully!\n", "");
    }

    @Override
    public void updatePublisher(Publishers publisher) {
        for (int i = 0; i < publisherList.size(); i++) {
            if (publisherList.get(i).getPublisherId().equals(publisher.getPublisherId())) {
                publisherList.set(i, publisher);
                System.out.printf("%10sPublisher updated successfully!\n", "");
                break;
            }
        }
        saveToFile();
    }

    @Override
    public void deletePublisher(String publisherId) {
        boolean removed = publisherList.removeIf(pub -> pub.getPublisherId().equals(publisherId));
        if (removed) {
            saveToFile();
            System.out.printf("%10sPublisher deleted successfully!\n", "");
        } else {
            System.out.printf("%10sPublisher not found!\n", "");
        }
    }

    @Override
    public void showPublishers() {
        loadPublishers();
        System.out.printf("\n\n%10s*************************************************************\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*--------------------!! PUBLISHER LIST !!--------------------*\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*************************************************************\n", "");
        System.out.printf("%10s| %-10s | %-30s |\n", "", "ID", "Name");
        System.out.printf("%10s-------------------------------------------------------------\n", "");

        if (publisherList.isEmpty()) {
            System.out.printf("%10sNo publishers found!\n", "");
            return;
        }

        for (Publishers pub : publisherList) {
            System.out.printf("%10s| %-10s | %-30s |\n", "", pub.getPublisherId(), pub.getPublisherName());
            System.out.printf("%10s-------------------------------------------------------------\n", "");
        }
    }

    @Override
    public List<Publishers> searchPublisher(String searchDetail) {
        List<Publishers> results = new ArrayList<>();
        for (Publishers pub : publisherList) {
            if (pub.getPublisherId().contains(searchDetail) || pub.getPublisherName().toLowerCase().contains(searchDetail.toLowerCase())) {
                results.add(pub);
            }
        }
        return results;
    }

    @Override
    public Publishers searchPublisherById(String publisherId) {
        for (Publishers pub : publisherList) {
            if (pub.getPublisherId().equalsIgnoreCase(publisherId)) {
                return pub;
            }
        }
        return null;
    }

    @Override
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(publisherFile))) {
            for (Publishers pub : publisherList) {
                oos.writeObject(pub);
            }
        } catch (IOException e) {
            System.out.println("Error saving publishers: " + e.getMessage());
        }
    }

    @Override
    public void showPublishersByList(List<Publishers> list) {
        System.out.printf("\n\n%10s*************************************************************\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*-------------------!! SEARCH PUBLISHER !!-------------------*\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*************************************************************\n", "");
        System.out.printf("%10s| %-10s | %-30s |\n", "", "ID", "Name");
        System.out.printf("%10s-------------------------------------------------------------\n", "");

        if (list.isEmpty()) {
            System.out.printf("%10sNo publishers found!\n", "");
            return;
        }
        for (Publishers pub : list) {
            System.out.printf("%10s| %-10s | %-30s |\n", "", pub.getPublisherId(), pub.getPublisherName());
            System.out.printf("%10s-------------------------------------------------------------\n", "");
        }
    }
}
