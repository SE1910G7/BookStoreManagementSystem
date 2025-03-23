/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.services;

import bookstoremanagementsystem.interfaces.IGenres;
import bookstoremanagementsystem.models.Genres;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author nhulnt
 */
public class GenresManager implements IGenres {

    Scanner sc = new Scanner(System.in);
    private List<Genres> genreList = new ArrayList<>();
    private String genreFile = "genres.txt";
    private String etc = "";

    @Override
    public void loadGenresFile() {
        try {
            if (genreList.size() > 0) {
                genreList.clear();
            }
            File f = new File(genreFile);
            if (!f.exists()) {
                return;
            }
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream fo = new ObjectInputStream(fi);
            Genres genre;
            while ((genre = (Genres) (fo.readObject())) != null) {
                genreList.add(genre);
            }
            fo.close();
            fi.close();
        } catch (Exception e) {
//            System.out.println(e);
        }
    }

    @Override
    public void saveGenresFile() {
        if (genreList.size() == 0) {
            System.out.println("Empty List.");
            return;
        }
        try {
            FileOutputStream f = new FileOutputStream(genreFile);
            ObjectOutputStream fo = new ObjectOutputStream(f);
            for (Genres genre : genreList) {
                fo.writeObject(genre);
            }
            fo.close();
            f.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void createGenre() {
        String gId, gName, gDes;
        System.out.printf("%10s\tID: ", etc);
        gId = sc.nextLine();
        System.out.printf("%10s\tName: ", etc);
        gName = sc.nextLine();
        System.out.printf("%10s\tDescription: ", etc);
        gDes = sc.nextLine();
        genreList.add(new Genres(gId, gName, gDes));
        saveGenresFile();
    }

    @Override
    public void showGenreList() {
        System.out.printf("\n%10s*************************************************************\n", etc);
        System.out.printf("%10s*%59s*\n", etc, etc);
        System.out.printf("%10s*------------------%5s Genre List %5s-------------------*\n", etc, etc, etc);
        System.out.printf("%10s*%59s*\n", etc, etc);
        System.out.printf("%10s*************************************************************\n", etc);
        System.out.printf("%10s| %-8s | %-15s | %-28s |\n", etc, "Genre ID", "Genre Name", "Description");
        System.out.printf("%10s*************************************************************\n", etc);

        if (genreList.isEmpty()) {
            System.out.printf("%10s| %-57s |\n", etc, "No genres available.");
        } else {
            for (Genres genre : genreList) {
                System.out.printf("%10s%s\n", etc, genre.toString());
            }
        }

        System.out.printf("%10s*************************************************************\n", etc);
    }

    @Override
    public void updateGenre() {
        if (genreList.isEmpty()) {
            System.out.println("No genres available to update.");
            return;
        }

        System.out.printf("%10sEnter Genre ID to update: ", "");
        String gId = sc.nextLine();

        for (Genres genre : genreList) {
            if (genre.getGenreId().equalsIgnoreCase(gId)) {
                System.out.printf("\n\n%10s*************************************************************\n", "");
                System.out.printf("%10s*%59s*\n", "", "");
                System.out.printf("%10s*------------------!! UPDATE GENRE !!------------------*\n", "");
                System.out.printf("%10s*%59s*\n", "", "");
                System.out.printf("%10s*************************************************************\n", "");
                System.out.printf("%10s| %-15s: %-40s |\n", "", "Genre ID", genre.getGenreId());
                System.out.printf("%10s| %-15s: %-40s |\n", "", "Current Name", genre.getGenreName());
                System.out.printf("%10s| %-15s: %-40s |\n", "", "Current Description", genre.getDescription());

                System.out.printf("%10s-------------------------------------------------------------\n", "");
                System.out.printf("\n%10sEnter new Genre Name (leave blank to keep current): ", "");
                String newName = sc.nextLine();
                if (!newName.isEmpty()) {
                    genre.setGenreName(newName);
                }
                System.out.printf("%10sEnter new Description (leave blank to keep current): ", "");
                String newDesc = sc.nextLine();
                if (!newDesc.isEmpty()) {
                    genre.setDescription(newDesc);
                }
                System.out.printf("\n%10s*************************************************************\n", "");
                System.out.printf("%10s*                Genre updated successfully!                *\n", "");
                System.out.printf("%10s*************************************************************\n", "");
                saveGenresFile();
                return;
            }
        }

        System.out.printf("\n%10s*************************************************************\n", "");
        System.out.printf("%10s*               Genre ID not found. Try again.               *\n", "");
        System.out.printf("%10s*************************************************************\n", "");
    }

    @Override
    public void removeGenre() {
        if (genreList.isEmpty()) {
            System.out.printf("%10sNo genres available to remove.", "");
            return;
        }

        System.out.printf("%10sEnter Genre ID to remove: ", "");
        String gId = sc.nextLine();

        for (Genres genre : genreList) {
            if (genre.getGenreId().equalsIgnoreCase(gId)) {
                genreList.remove(genre);
                System.out.printf("%10sGenre removed successfully!", "");
                saveGenresFile();
                return;
            }
        }
        System.out.printf("%10sGenre ID not found!", "");
    }

    @Override
    public void showGenreDetail() {
        if (genreList.isEmpty()) {
            System.out.printf("%10sNo genres available.", "");
            return;
        }
        System.out.printf("%10sEnter Genre ID to view details: ", "");
        String gId = sc.nextLine();

        for (Genres genre : genreList) {
            if (genre.getGenreId().equalsIgnoreCase(gId)) {
                System.out.printf("\n\n%10s*************************************************************\n", "");
                System.out.printf("%10s*%59s*\n", "", "");
                System.out.printf("%10s*------------------!! GENRE DETAILS !!------------------*\n", "");
                System.out.printf("%10s*%59s*\n", "", "");
                System.out.printf("%10s*************************************************************\n", "");

                System.out.printf("%10s| %-15s: %-40s |\n", "", "Genre ID", genre.getGenreId());
                System.out.printf("%10s| %-15s: %-40s |\n", "", "Name", genre.getGenreName());
                System.out.printf("%10s| %-15s: %-40s |\n", "", "Description", genre.getDescription());

                System.out.printf("%10s*************************************************************\n", "");
                return;
            }
        }
        System.out.printf("%10sGenre ID not found.", "");
    }

    @Override
    public Genres searchGenreById(String id) {
        loadGenresFile();
        for (Genres g : genreList) {
            if (g.getGenreId().equalsIgnoreCase(id)) {
                return g;
            }
        }
        return null;
    }

    @Override
    public List<Genres> GetGenreList() {
        loadGenresFile();
        return genreList;
    }

}
