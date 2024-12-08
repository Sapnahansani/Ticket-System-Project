import java.util.Scanner;

// Main class to manage the Ticket System
public class TicketSystemManagement {
    public static void main(String[] args) {
        //Create an instance of ConfigurationManager to set up system parameters
        ConfigurationManager configManager = new ConfigurationManager();
        configManager.setupConfiguration();// Prompt user to input configuration details

        // Initialize the ticket pool with total tickets and max capacity
        TicketPool ticketPool = new TicketPool(configManager.getTotalTickets(), configManager.getMaxTicketCapacity());

        // Initialize the ticket vendor with the ticket release rate
        TicketVendor ticketVendor = new TicketVendor(ticketPool, configManager.getTicketReleaseRate());

        // Initialize the ticket customer with the retrieval rate
        TicketCustomer ticketCustomer = new TicketCustomer(ticketPool, configManager.getCustomerRetrievalRate());

        // Declare threads for the vendor and customer processes
        Thread vendorThread = null;
        Thread customerThread = null;

        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Main loop to handle user commands
        while (true) {
            System.out.print("Enter command (start, stop, exit): ");// Prompt for user command
            String command = scanner.nextLine().trim().toLowerCase();// Read and normalize input

            // Process commands based on user input
            switch (command) {
                case "start":
                    // Start the vendor thread if it's not already running
                    if (vendorThread == null || !vendorThread.isAlive()) {
                        vendorThread = new Thread(ticketVendor, "Vendor");
                        vendorThread.start();
                    }
                    // Start the customer thread if it's not already running
                    if (customerThread == null || !customerThread.isAlive()) {
                        customerThread = new Thread(ticketCustomer, "Customer");
                        customerThread.start();
                    }
                    System.out.println("System started.");
                    break;

                // Stop the vendor thread if it's running
                case "stop":
                    if (vendorThread != null && vendorThread.isAlive()) {
                        ticketVendor.stop(); // Signal vendor to stop
                        try {
                            vendorThread.join(); // Wait for vendor thread to finish
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);// Handle interruption
                        }
                    }
                    // Stop the customer thread if it's running
                    if (customerThread != null && customerThread.isAlive()) {
                        ticketCustomer.stop(); // Signal customer to stop
                        try {
                            customerThread.join();// Wait for customer thread to finish
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);// Handle interruption
                        }
                    }
                    System.out.println("System stopped.");
                    break;
                // Exit the application
                case "exit":
                    System.out.println("Exiting the system.");
                    System.exit(0);
                // Handle invalid commands
                default:
                    System.out.println("Invalid command. Please enter 'start', 'stop', or 'exit'.");
            }
        }
    }
}
