package com.codewithmosh.librarytry2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private String availability;
    private String borrowerName;

    public Book(String title, String author, String isbn, String availability, String borrowerName) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.availability = availability;
        this.borrowerName = borrowerName;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAvailability() {
        return availability;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public static List<Book> displayLibraryBook(String filePath) {
        List<Book> books = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // Read the first line (header) and skip it

            while ((line = reader.readLine()) != null) {
                String[] bookData = line.split(",");

                // Check if the line matches the expected column count
                if (bookData.length == 5) {
                    // Create Book object and add to the list
                    books.add(new Book(bookData[0], bookData[1], bookData[2], bookData[3], bookData[4]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return books;
    }
}
