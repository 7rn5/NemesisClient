package nemesis.event.bus;

public class Event {
    private boolean cancelled;
    
    public boolean isCancelled() {
        return cancelled;
    }
    
    public void cancel() {
        cancelled = true;
    }
}