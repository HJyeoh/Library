import java.io.*;
import java.util.Scanner;

public class Library {

    public static void main(String[] args) {
        String filePath ="D:\\IntelliJ SAVING\\library.txt"; // Path to CSV file
        Scanner scanner = new Scanner(System.in); // Scanner object for user input
        String searchTerm;

        while(true) {
            System.out.println("Enter the search term (Title, Author or ISBN) or 'exit':");
            searchTerm = scanner.nextLine(); // User input

            if (searchTerm.equalsIgnoreCase("exit")) {
                System.out.println("Bye~");
                break;
            }
            SearchFunction(filePath, searchTerm);
        }
        scanner.close();
    }
    public static void SearchFunction(String filePath, String searchTerm) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) { // read csv file
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {

                // Split the line into an array of elements
                String[] elements = line.split(",");

                // Check if the search term matches any of the elements (Title, Author, or ISBN)
                for (String element : elements) {
                    if (element.equalsIgnoreCase(searchTerm)) { // If element found
                        System.out.println("Found: " + line); // print whole line

                        // Store elements into separate variables
                        String title = elements[0];
                        String author = elements[1];
                        String isbn = elements[2];
                        String availability = elements[3];
                        String borrowerName = elements.length > 4 ? elements[4] : "None";

                        // Print the stored variables
                        System.out.println("Title: " + title);
                        System.out.println("Author: " + author);
                        System.out.println("ISBN: " + isbn);
                        System.out.println("Availability: " + availability);
                        System.out.println("Borrower Name: " + borrowerName);
                        return; // Exit after finding the first match
                    }
                }
            }
            System.out.println("No match found for: " + searchTerm);
        } catch (Exception ex) {
            System.out.println("File not found.");
            return;
        }
    }
}
