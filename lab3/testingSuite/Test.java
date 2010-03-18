import java.io.IOException;
import java.util.Arrays;

import suite.Stopwatch;
import suite.Data;
import suite.Timing;

public class Test {
	public static void main(String[] args) {
		Data data = new Data();
        int nQueries = 100;
        int[] qvals = new int[nQueries];
		
		qvals = data.getRange(nQueries);
		bin(qvals,75);
	}
	
	public static int seq(int[] array, int q) {
		// is it you?
		int counter=0;
		for (int i=array.length;i>0 ;i-- ) {
			counter++;
			//System.out.println(i);
			if (array[array.length-i]==q) {
				//System.out.println("success.");
				System.out.println(counter);
				return i;
			}
		}
		System.out.println(counter);
		return -1;
    }
	public static int bin(int[] array, int value) {
		// time to break it down.
		int low = 0;
		int high = array.length;
		int mid=0;
		int counter=0;
		while (low<high) {
			counter++;
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
			System.out.println(counter);
			return low;
		}
		else {
			System.out.println(counter);
			return -1;
		}
    }
}