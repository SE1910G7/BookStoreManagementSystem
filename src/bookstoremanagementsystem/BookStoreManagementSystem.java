/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem;

import bookstoremanagementsystem.interfaces.IAuthors;
import bookstoremanagementsystem.models.BookAuthors;
import bookstoremanagementsystem.services.AuthorManager;
import bookstoremanagementsystem.services.BookAuthorsManager;
import bookstoremanagementsystem.services.PublisherManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author mummykiara
 */
public class BookStoreManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PublisherManager publisherService = new PublisherManager();
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, true))) { 

            int T = Integer.parseInt(br.readLine().trim()); 
            StringBuilder outputBuffer = new StringBuilder();

            for (int i = 0; i < T; i++) {
                String[] command = br.readLine().split(" ", 3);
                int choice = Integer.parseInt(command[0]);

                switch (choice) {
                    case 1: 
                        publisherService.addPublisher(command[1], command[2], outputBuffer);
                        break;
                    case 2: 
                        publisherService.viewPublishers(outputBuffer);
                        break;
                    case 3: 
                        publisherService.updatePublisher(command[1], command[2], outputBuffer);
                        break;
                    case 4: 
                        publisherService.deletePublisher(command[1], outputBuffer);
                        break;
                    default:
                        outputBuffer.append("Invalid Command!\n");
                }
            }
            bw.write(outputBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}