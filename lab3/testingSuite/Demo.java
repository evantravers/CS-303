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
        int nQueries = 1000;
        int[] qvals = new int[nQueries];
        int[] truth = new int[nQueries];
        int answer;
        int incJump = 10; // this is the value by which which the multiplier is multiplied
        int minN=1,maxN=nQueries/incJump;
        boolean makeData=false,testData=false,seqSearch=false;
        if (makeData) {
            for (int N=minN; N<maxN; N++) {
                // create an array of non-decreasing (or strictly
                // increasing) values of length N
            	
            	rvals = data.getRandRange(N*incJump);
            	
                // create queries and ground truth for this array
            	
            	data.getQueries(rvals, qvals, 0.9, truth);
            	
                // write the array of values
            	data.write("rvals"+N+".csv", rvals);
                // write queries array
            	data.write("qvals"+N+".csv", qvals);
                // write the ground truth array
            	data.write("truth"+N+".csv", truth);
            }
            System.exit(0);
        } else {
            for (int N=minN; N<maxN;N++) {
                // read the file containing the array for N
            	rvals = data.read("rvals"+N+".csv");

                // read the queries array for N
            	qvals = data.read("qvals"+N+".csv");
                // read the ground truth array for N
            	truth = data.read("truth"+N+".csv");
            	
                if (testData) {
                    System.exit(0);
                } else {
                    t.startRun(N);
                    for (int j=0; j<nQueries; j++) {
                        if (seqSearch) {
                            // run sequential search
                        	answer = seq(rvals, qvals[j]);
                        }
                        else {
                            // run binary search
                        	answer = bin(rvals, qvals[j]);
                        }
                        if (answer != truth[j]) {
                            // For diagnostic purposes.. your search
                            // should give the correct answer
                        	System.out.println("FAIL!");
                            System.exit(-1);
                        }
                    }
                    t.stopRun();
                    t.printRun(N);
                }
            }
            if (seqSearch) {
                // output run for sequential search
            	t.outputRuns("seqruns.csv");
            }
            else {
                // output run for binary search
            	t.outputRuns("binruns.csv");
            }
        }
    }
   
    public static int seq(int[] array, int q) {
		// is it you?
		for (int i=array.length;i>0 ;i-- ) {
			if (array[array.length-i]==q) {
				return array.length-i;
			}
		}
		return -1;
    }
   	public static int bin(int[] array, int value) {
		// time to break it down.
		int low = 0;
		int high = array.length;
		int mid=0;
		while (low<high) {
			mid = (high+low)/2;
			if (array[mid]<value) {
				// use the upper half
				low = mid+1;
			}
			else {
				// use the lower half
				high = mid;
			}
		}
		if (low<array.length&&array[low]==value) {
			return low;
		}
		else {
			return -1;
		}
    }
}
