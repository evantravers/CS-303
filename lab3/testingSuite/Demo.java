import java.io.IOException;
import java.util.Arrays;

import suite.Stopwatch;
import suite.Data;
import suite.Timing;

public class Demo {
    public static void main(String args[]) throws IOException {
        Data data = new Data();
        Timing t = new Timing();
        int[] rvals;
        int nQueries = 100000;
        int[] qvals = new int[nQueries];
        int[] truth = new int[nQueries];
        int answer;
        int minN=0,maxN=0;
        boolean makeData=false,testData=false,seqSearch=false;
        if (makeData) {
            for (int N=minN; N<maxN; N++) {
                // create an array of non-decreasing (or strictly
                // increasing) values of length N

                // create queries and ground truth for this array

                // write the array of values
                // write queries array
                // write the ground truth array
            }
            System.exit(0);
        } else {
            for (int N=minN; N<maxN;N++) {
                // read the file containing the array for N

                // read the queries array for N
                // read the ground truth array for N
                if (testData) {
                    System.exit(0);
                } else {
                    t.startRun(N);
                    for (int j=0; j<nQueries; j++) {
                        if (seqSearch) {
                            // run sequential search
                        }
                        else {
                            // run binary search
                        }
                        if (answer != truth[j]) {
                            // For diagnostic purposes.. your search
                            // should give the correct answer
                            System.exit(-1);
                        }
                    }
                    t.stopRun();
                    t.printRun(N);
                }
            }
            if (seqSearch) {
                // output run for sequential search
            }
            else {
                // output run for binary search
            }
        }
    }
    public static int seq(int[] array, int q) {
		// TODO fix this
		return 0;
    }
    public static int bin(int[] array, int value) {
		// TODO fix this
		return 0;
    }
}
