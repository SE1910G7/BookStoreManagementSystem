/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.services;

import bookstoremanagementsystem.models.Authors;
import bookstoremanagementsystem.interfaces.CRUDPublisher;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.*;

/**
 *
 * @author DELL
 */
public class PublisherManager implements CRUDPublisher {
    private Map<String, String> publishers = new LinkedHashMap<>(); // Maintain insertion order
    private final String dataFile = "publishers.txt";

    public PublisherManager() {
        loadPublishers();
    }

    private void loadPublishers() {
        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    publishers.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("No existing publisher data found.");
        }
    }

    private void savePublishers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile))) {
            for (Map.Entry<String, String> entry : publishers.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving publisher data.");
        }
    }

    @Override
    public void addPublisher(String id, String name, StringBuilder outputBuffer) {
        publishers.put(id, name);
        savePublishers();
        outputBuffer.append("Added Publisher: ").append(id).append(" - ").append(name).append("\n");
    }

    @Override
    public void viewPublishers(StringBuilder outputBuffer) {
        outputBuffer.append("--- Publisher List ---\n");
        for (Map.Entry<String, String> entry : publishers.entrySet()) {
            outputBuffer.append(entry.getKey()).append(" - ").append(entry.getValue()).append("\n");
        }
        outputBuffer.append("----------------------\n");
    }

    @Override
    public void updatePublisher(String id, String newName, StringBuilder outputBuffer) {
        if (publishers.containsKey(id)) {
            publishers.put(id, newName);
            savePublishers();
            outputBuffer.append("Updated Publisher: ").append(id).append(" -> ").append(newName).append("\n");
        } else {
            outputBuffer.append("Publisher not found.\n");
        }
    }

    @Override
    public void deletePublisher(String id, StringBuilder outputBuffer) {
        if (publishers.containsKey(id)) {
            publishers.remove(id);
            savePublishers();
            outputBuffer.append("Deleted Publisher: ").append(id).append("\n");
        } else {
            outputBuffer.append("Publisher not found.\n");
        }
    }
}
