package com.codewithmosh.librarytry2;

import java.io.*;
import java.util.ArrayList;

public class Library {

    public static void addBooksToFile(ArrayList<String> booksList, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
           writer.write(String.join(",", booksList));
           writer.newLine();  // Adds a new line after each entry
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] SearchFunction(String filePath, String searchTerm) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true; // flag to track the first line

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // skip the first line and set the flag to false
                    continue;
                }

                String[] elements = line.split(",");
                for (String element : elements) {
                    if (element.equalsIgnoreCase(searchTerm)) {
                        String title = elements[0];
                        String author = elements[1];
                        String isbn = elements[2];
                        String availability = elements[3];
                        String borrowerName = elements.length > 4 ? elements[4] : "None";
                        return new String[]{title, author, isbn, availability, borrowerName}; // return array
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("File not found.");
        }
        return null; // return null if no match is found
    }

}
