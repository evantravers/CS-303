import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String Args[]) {
		// get info 
		String fileName = "values1.txt";
		StringTokenizer tokenizer;
		int[] list = new int[10];
		try {
			Scanner scanFile = new Scanner(new File (fileName));
			int value;
			String line;
			int counter=0;
	           while (scanFile.hasNext())
	           {
	              line = scanFile.nextLine();
	              tokenizer = new StringTokenizer (line);
	              try
	              {
	            	  value = Integer.parseInt (tokenizer.nextToken());
	            	  list[counter]=value;
	            	  counter++;
	              }
	              catch (NumberFormatException exception)
	              {
	                 System.out.println ("Error in input. Line ignored.");
	                 System.out.println (line);
	              }
	              catch (ArrayIndexOutOfBoundsException exception)
	              {
	            	  System.out.println("too many numbers!");
	              }
	           }
	           scanFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println ("Error in input. Line ignored.");
		}
		// sort that data
		for( int gap = list.length / 2; gap > 0;
        gap = gap == 2 ? 1 : (int) ( gap / 2.2 ) )
		for( int i = gap; i < list.length; i++ )
			{
			int tmp = list[ i ];
			int j = i;

			for( ; j >= gap && tmp < list[ j - gap ]; j -= gap )
				list[ j ] = list[ j - gap ];
				list[ j ] = tmp;
			}
		// spit it out
		for (int x=0;x<list.length;x++) {
			System.out.println(list[x]);
		}
	}
}
