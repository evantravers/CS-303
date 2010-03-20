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

public class Test {
	public static void main(String args[]) {
		Data data = new Data();
        Timing t = new Timing();
		// evaluating the component as a variable
		Graph g = new Graph(2,2,2);
        // g.print();
      	// evaluate();
		int i = 2;
		g.dfs(0);
	}
	
	public static Vector<Integer> numComponents(Graph g, int timerCount, Timing t, Boolean timerEnable) {
		Vector<Integer> return1 = new Vector<Integer>();
		if (timerEnable) {
			t.startRun(timerCount);
		}
		for (int i=0;i<g.graphLength();i++) {
			if (!g.getVisited(i)) {
				return1.add(g.dfs(i));
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