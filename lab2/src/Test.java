import java.io.IOException;

import testing.Stopwatch;
import testing.Data;
import testing.Timing;

public class Test {
    public static void main(String args[]) throws IOException {
        Data data = new Data();
        Timing t = new Timing();
        int[] rvals;
        rvals = data.getRandom(10);
        // code to test goes here
		Sort.bubbleSort(rvals);
		data.printIntArray(rvals);
		// output times for graph
        t.outputRuns("times.csv");
    }
}