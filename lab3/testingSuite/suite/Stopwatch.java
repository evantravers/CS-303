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
    private long getSystemTime() {
        return System.nanoTime();
    }
    /**
     * Starts the stopwatch
     * 
     */
    public void start() {
        startTime = getSystemTime();
        started = true;
        running = true;
    }
    /**
     * Stops the stopwatch
     * 
     */
    public void stop() {
        stopTime = getSystemTime();
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
            return ((float)(stopTime - startTime))/1000000000.;
        else if (started) 
            return (getSystemTime()-startTime)/1000000000.;
        return 0;
    }
}