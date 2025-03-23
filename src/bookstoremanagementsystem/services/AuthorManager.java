/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.services;

import bookstoremanagementsystem.interfaces.IAuthors;
import bookstoremanagementsystem.models.Authors;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author
 */
public class AuthorManager implements IAuthors {

    private final List<Authors> authorsList = new ArrayList<>();
    private final String filePath = "authors.txt";

    public AuthorManager() {
        loadAuthorsFromFile();
    }

    @Override
    public void addAuthor(String authorID, String fullName) {
        authorsList.add(new Authors(authorID, fullName));
        System.out.printf("\n%10sAuthor added successfully!", "");
        saveAuthorsToFile();
    }

    @Override
    public void displayAuthors() {
        System.out.printf("\n\n%10s*************************************************************\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*--------------------!! AUTHORS LIST !!--------------------*\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*************************************************************\n", "");
        System.out.printf("%10s| %-10s | %-30s |\n", "", "Author ID", "Full Name");
        System.out.printf("%10s-------------------------------------------------------------\n", "");

        if (authorsList.isEmpty()) {
            System.out.printf("%10sNo authors available!\n", "");
            return;
        }

        for (Authors author : authorsList) {
            System.out.printf("%10s| %-10s | %-30s |\n", "", author.getAuthorID(), author.getFullName());
            System.out.printf("%10s-------------------------------------------------------------\n", "");
        }
    }

    @Override
    public void sortAuthors() {
        authorsList.sort(Comparator.comparing(Authors::getFullName));
        System.out.println("Authors sorted by name!");
        displayAuthors();
    }

    @Override
    public void searchAuthor(String searchInput) {
        boolean found = false;

        System.out.printf("\n\n%10s*************************************************************\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*-------------------!! SEARCH AUTHOR !!-------------------*\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*************************************************************\n", "");
        System.out.printf("%10s| %-10s | %-30s |\n", "", "Author ID", "Full Name");
        System.out.printf("%10s-------------------------------------------------------------\n", "");

        for (Authors author : authorsList) {
            if (author.getFullName().contains(searchInput) || author.getAuthorID().contains(searchInput)) {
                System.out.printf("%10s| %-10s | %-30s |\n", "", author.getAuthorID(), author.getFullName());
                System.out.printf("%10s-------------------------------------------------------------\n", "");
                found = true;
            }
        }

        if (!found) {
            System.out.printf("%10s| %-55s |\n", "", "Author not found!");
            System.out.printf("%10s-------------------------------------------------------------\n", "");
        }
    }

    @Override
    public void deleteAuthor(String searchInput) {
        boolean found = false;
        Iterator<Authors> iterator = authorsList.iterator();
        while (iterator.hasNext()) {
            Authors author = iterator.next();
            if (author.getFullName().equalsIgnoreCase(searchInput) || author.getAuthorID().equalsIgnoreCase(searchInput)) {
                String authorID = author.getAuthorID();
                String fullName = author.getFullName();
                iterator.remove();
                System.out.printf("%10sDeleted authors successfully!\n", "");
                found = true;
            }
        }
        if (!found) {
            System.out.printf("%10sAuthor not found.!\n", "");
        } else {
            saveAuthorsToFile();
        }
    }

    @Override
    public void saveAuthorsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (Authors author : authorsList) {
                oos.writeObject(author);
            }
        } catch (IOException e) {
            System.err.println("Error saving authors to file: " + e.getMessage());
        }
    }

    @Override
    public void loadAuthorsFromFile() {
        authorsList.clear();
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.printf("%10sFile not found!\n", "");
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            while (true) {
                try {
                    Authors author = (Authors) ois.readObject();
                    authorsList.add(author);
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException e) {
//                    System.err.println("Class not found: " + e.getMessage());
                    break;
                }
            }
        } catch (IOException e) {
//            System.err.println("Error occurs loading authors from file: " + e.getMessage());
        }
    }

    @Override
    public Authors searchAuthorByID(String authorID) {
        loadAuthorsFromFile();
        for (Authors author : authorsList) {
            if (author.getAuthorID().equalsIgnoreCase(authorID)) {
                return author;
            }
        }
        return null;
    }

    @Override
    public void updateAuthor(Authors author) {
        loadAuthorsFromFile();
        for (int i = 0; i < authorsList.size(); i++) {
            if (authorsList.get(i).getAuthorID().equals(author.getAuthorID())) {
                authorsList.set(i, author);
                System.out.printf("%10sAuthour updated successfully!\n", "");
            }
        }
        saveAuthorsToFile();
    }

}
