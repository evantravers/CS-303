import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;
import java.util.Stack;
import java.util.LinkedList;

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

public class Demo {
    public static void main(String args[]) throws IOException {
        Data data = new Data();
        Timing t = new Timing();
		int nums=200;
		int other=2;
		// evaluating the component as a variable
		for (int i=1;i<nums;i+=1 ) {
			Graph g = new Graph(i,other,other);
	        // g.print();
	      	// evaluate();
			numComponents(g,i,t,true);
			System.out.println("Working on comp dfs #"+i);
		}  
		t.outputRuns("dfscomp"+nums+".csv");

		for (int i=1;i<nums;i+=1 ) {
			Graph g = new Graph(i,other,other);
	        // g.print();
	      	// evaluate();
			evaluate(g,i,t,true);
			System.out.println("Working on comp bfs #"+i);
		}
		t.outputRuns("bfscomp"+nums+".csv");

		// evaluating the depth as a variable
		for (int i=1;i<nums;i+=1 ) {
			Graph g = new Graph(1,i,other);
	        // g.print();
	      	// evaluate();
			numComponents(g,i,t,true);
			System.out.println("Working on depth dfs #"+i);
		}  
		t.outputRuns("dfsdepth"+nums+".csv");

		for (int i=1;i<nums;i+=1 ) {
			Graph g = new Graph(1,i,other);
	        // g.print();
	      	// evaluate();
			evaluate(g,i,t,true);
			System.out.println("Working on depth bfs #"+i);
		}
		t.outputRuns("bfsdepth"+nums+".csv");
		
		// evaluating the branching factor as a variable
		for (int i=1;i<nums;i+=1 ) {
			Graph g = new Graph(1,other,i);
	        // g.print();
	      	//evaluate();
			numComponents(g,i,t,true);
			System.out.println("Working on branching dfs #"+i);
		}  
		t.outputRuns("dfsbranching"+nums+".csv");

		for (int i=1;i<nums;i+=1 ) {
			Graph g = new Graph(1,other,i);
	        // g.print();
	      	//evaluate();
			evaluate(g,i,t,true);
			System.out.println("Working on branching bfs #"+i);
		}
		t.outputRuns("bfsbranching"+nums+".csv");		
    }

	public static Vector<Integer> numComponents(Graph g, int timerCount, Timing t, Boolean timerEnable) {
		Vector<Integer> return1 = new Vector<Integer>();
		if (timerEnable) {
			t.startRun(timerCount);
		}
		for (int i=0;i<g.graphLength();i++) {
			if (!g.getVisited(i)) {
				return1.add(g.dfsNR(i));
			}
		}
		if (timerEnable) {
			t.stopRun();
		}
		return return1;
	}
	
	public static void evaluate(Graph g, int timerCount, Timing t, Boolean timerEnable) {
		String output = "";
		Vector<Integer> numComp = numComponents(g,timerCount,t,false);
		output += "Number of components: " + numComp.size() + "";
		g.setAllUnvisited();
		if (timerEnable) {
			t.startRun(timerCount);
		}
		for (int i=0;i<numComp.size();i++) {
			int node = numComp.get(i);
			if (!g.getVisited(node)) {
				output += "\nNodes of component " + i + " are: " +  g.bfs(node);
			}
		}
		if (timerEnable) {
			t.stopRun();
		}
		System.out.println(output);
	}
}