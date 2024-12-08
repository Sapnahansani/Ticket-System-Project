import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// ConfigurationManager class to manage system configurations
public class ConfigurationManager {
    private int totalTickets;// Total number of tickets available
    private int ticketReleaseRate; // Rate at which tickets are released
    private int customerRetrievalRate;// Rate at which customers retrieve tickets
    private int maxTicketCapacity;// Maximum ticket capacity

    // Method to set up configuration values
    public void setupConfiguration() {
        Scanner scanner = new Scanner(System.in);

        // Get total tickets from user (must be greater than 0)
        totalTickets = getValidInput(scanner, "Enter total tickets (must be greater than 0): ", 1, Integer.MAX_VALUE);
        // Get ticket release rate from user (must be greater than 0)
        ticketReleaseRate = getValidInput(scanner, "Enter ticket release rate (must be greater than 0): ", 1, Integer.MAX_VALUE);
        // Get customer retrieval rate from user (must be greater than 0)
        customerRetrievalRate = getValidInput(scanner, "Enter customer retrieval rate (must be greater than 0): ", 1, Integer.MAX_VALUE);

        // Loop to ensure max ticket capacity is greater than or equal to total tickets
        while (true) {
            maxTicketCapacity = getValidInput(scanner, "Enter max ticket capacity (must be greater than total tickets): ", totalTickets, Integer.MAX_VALUE);
            if (maxTicketCapacity >= totalTickets) break; // Valid input, exit loop
            System.out.println("Error: Max ticket capacity must be greater than or equal to total tickets.");
        }

        // Save the configuration details to a file
        saveConfigurationToFile();
    }
    // Method to get valid user input within a specified range
    private int getValidInput(Scanner scanner, String prompt, int min, int max) {
        int input; // Variable to store user input
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= min) {
                    return input; // Valid input, return value
                } else {
                    System.out.println("Error: Input must be greater than or equal to " + min + "."); // Error message on one line
                }
            } else {
                System.out.println("Error: Invalid input. Please enter a valid integer."); // Error message on one line
                scanner.next(); // Consume the invalid input
            }
        }
    }

    // Method to save configuration details to a file
    private void saveConfigurationToFile() {
        try (FileWriter writer = new FileWriter("configuration.txt", false)) {
            writer.write("Total Tickets: " + totalTickets + "\n");
            writer.write("Ticket Release Rate: " + ticketReleaseRate + "\n");
            writer.write("Customer Retrieval Rate: " + customerRetrievalRate + "\n");
            writer.write("Max Ticket Capacity: " + maxTicketCapacity + "\n");
            System.out.println("Configuration saved to file.");
        } catch (IOException e) {
            System.out.println("Failed to save configuration: " + e.getMessage());
        }
    }
    // Getter method to retrieve total tickets
    public int getTotalTickets() {
        return totalTickets;
    }
    // Getter method to retrieve ticket release rate
    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }
    // Getter method to retrieve customer retrieval rate
    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }
    // Getter method to retrieve max ticket capacity
    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }
}
