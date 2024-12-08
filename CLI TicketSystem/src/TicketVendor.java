// TicketVendor class implements Runnable to run in a separate thread
public class TicketVendor implements Runnable {
    private final TicketPool ticketPool;
    private final int releaseRate;
    private boolean running = true;

    public TicketVendor(TicketPool ticketPool, int releaseRate) {
        this.ticketPool = ticketPool;
        this.releaseRate = releaseRate;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            ticketPool.addTickets(releaseRate);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
