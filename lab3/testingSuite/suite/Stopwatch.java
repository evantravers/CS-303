package suite;

/**
 * A stopwatch class for doing code timing.
 * 
 * @author David O'Gwynn
 * 
 */
public class Stopwatch {
    private boolean started;
    private boolean running;
    private long startTime;
    private long stopTime;

    /**
     * Constructs stopwatch 
     * 
     */
    public Stopwatch() {
        started = false;
        running = false;
    }
    /**
     * Starts the stopwatch
     * 
     */
    public void start() {
        startTime = System.currentTimeMillis();
        started = true;
        running = true;
    }
    /**
     * Stops the stopwatch
     * 
     */
    public void stop() {
        stopTime = System.currentTimeMillis();
        running = false;
    }
    /**
     * Is the stopwatch currently running?
     * 
     */
    public boolean isRunning() { return running; }
    /**
     * If the stopwatch is still running, return the elapsed time
     * since starting. If it has been stopped, return the time between
     * start and stop. If it was never started, return 0.
     * 
     */
    public double elapsed() {
        if (!running)
            return ((float)(stopTime - startTime))/1000.;
        else if (started)
            return (System.currentTimeMillis()-startTime);
        return 0;
    }
}