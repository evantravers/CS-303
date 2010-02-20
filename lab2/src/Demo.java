import java.io.IOException;

import testing.Stopwatch;
import testing.Data;
import testing.Timing;

public class Demo {
    public static void main(String args[]) throws IOException {
        Data data = new Data();
        Timing t = new Timing();
        int[] rvals;
        for (int N=100; N<50100;N += 1000) {
            for (int j=0; j<5; j++) {
				// create random values for search/sort
                rvals = data.getRandom(N);
                t.startRun(N);
                // code to test goes here
				Sort.bubbleSort(rvals);
                t.stopRun();
            }
            t.printRun(N);
        }
		// output times for graph
        t.outputRuns("times.csv");
    }
}