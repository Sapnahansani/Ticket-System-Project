public class TicketCustomer implements Runnable {
    private final TicketPool ticketPool;
    private final int retrievalRate;
    private boolean running = true;

    public TicketCustomer(TicketPool ticketPool, int retrievalRate) {
        this.ticketPool = ticketPool;
        this.retrievalRate = retrievalRate;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            ticketPool.removeTickets(retrievalRate);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
