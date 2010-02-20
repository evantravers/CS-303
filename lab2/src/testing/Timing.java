package testing;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import java.util.Vector;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A class to do timing for algorithms. To use:
 *
 * <ol><li> instantiate a Timing object (t)</li>
 * <li> call t.startRun(N) for the value of N</li>
 * <li> run your algorithm for that value of N</li>
 * <li> call t.stopRun()</li></ol>
 *
 * When you're done timing your algorithm, call t.outputRuns(fileName)
 * to output your timing information in CSV format.
 * 
 * @author David O'Gwynn
 * 
 */

public class Timing {
    private boolean started;
    private int currentN;
    private Stopwatch sw;
    private Map<Integer,Vector<Double>> table;

    /**
     * Timing constructor
     * 
     */
    public Timing() {
        started = false;
        sw = new Stopwatch();
        table = new TreeMap<Integer,Vector<Double>>();
    }

    /**
     * Method to start timing for some value of N. The algorithm can
     * be run multiple times at the same value of N. Each time is
     * stored separately, and all times are averaged when runTime(N)
     * is called.
     *
     * @param N
     *            The value of N for this run.
     */
    public void startRun(int N) {
        if (!started) {
            sw.start();
            started = true;
            currentN = N;
        }
    }
    /**
     * Method to stop timing for the current run. DO NOT DO NESTED
     * startRun()/stopRun() CALLS WITH THE SAME TIMING OBJECT.
     *
     */
    public void stopRun() {
        if (started) {
            sw.stop();
            if(!table.containsKey(currentN)) {
                Vector<Double> newTimes = new Vector<Double>(2);
                table.put(currentN,newTimes);
            }
            Vector<Double> times = table.get(currentN);
            times.add(sw.elapsed());
            started = false;
        }
    }
    /**
     * Method to return the average time for some value of N.
     *
     * @param N
     *            The value of N to get the run time for.
     */
    public double runTime(int N) {
        double sum=0,time = 0;
        if(table.containsKey(N)) {
            Vector<Double> times = table.get(N);
            for (int i=0; i< times.size(); i++) {
                sum += times.get(i);
            }
            time = sum / times.size();
        }
        return time;
    }
    /**
     * Method to print the average time for some value of N to stdout.
     *
     * @param N
     *            The value of N to get the run time for.
     */
    public void printRun(int N) {
        if(table.containsKey(N)) {
            System.out.println(N + ": " + runTime(N));
        }
    }

    /**
     * Method to output all runs for this Timing object. Data is
     * outputted in comma-separated value (CSV) format suitable for
     * loading into OpenOffice Calc or Microsoft Excel. Each line of
     * output is in the form: "N-value,avg-run-time"
     *
     * @param fileName
     *            The file name to which the data will be written.
     */
    public void outputRuns(String fileName) throws IOException {
        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter(fileName));
        Iterator it = table.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry m = (Map.Entry) it.next();
            int N = (Integer)m.getKey();
            double time = runTime(N);
            writer.write(N+","+time);
            writer.newLine();
        }
        writer.close();
    }
}