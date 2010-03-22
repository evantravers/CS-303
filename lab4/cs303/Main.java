import java.io.IOException;
import java.util.Arrays;
import java.util.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

import cs303.Stopwatch;
import cs303.Data;
import cs303.Timing;
import cs303.Graph;

public class Main 
{
	
    public static Scanner scan = new Scanner(System.in);
	
    public static void main(String args[]) throws IOException 
    {
	int[] compData;
	int comps = 0;
        Data data = new Data();
        Timing t = new Timing();
        Graph g;
        
        for(int N = 0; N < 16; N++)
        {
        	g = new Graph(3,3,(N+1));
        	t.startRun(N);
        	
       		g.BFS();
       		
       	
       		t.stopRun();
		t.printRun(N);
       }
       	
      
       	t.outputRuns("BFSb3.csv");
       	
       
    }
    
    
}