public class TicketPool {
    private int tickets;
    private final int maxCapacity;

    public TicketPool(int initialTickets, int maxCapacity) {
        this.tickets = initialTickets;
        this.maxCapacity = maxCapacity;
    }

    public synchronized void addTickets(int count) {
        if (tickets + count <= maxCapacity) {
            tickets += count;
            System.out.println("Vendor added " + count + " tickets. Total tickets: " + tickets);
        } else {
            System.out.println("Vendor tried to add tickets, but pool is full.");
        }
    }

    public synchronized void removeTickets(int count) {
        if (tickets >= count) {
            tickets -= count;
            System.out.println("Customer purchased " + count + " tickets. Remaining tickets: " + tickets);
        } else {
            System.out.println("Customer tried to purchase tickets, but not enough available.");
        }
    }

    public synchronized int getTicketCount() {
        return tickets;
    }
}
