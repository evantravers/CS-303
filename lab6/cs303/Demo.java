import java.io.IOException;
import java.util.Arrays;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.PriorityQueue;
import java.util.ArrayList;

import cs303.Cities;
import cs303.Graph;
import cs303.Edge;
import cs303.Data;
import cs303.Timing;
	

public class Demo {
    public static void main(String args[]) throws IOException {
        Data data = new Data();
        Timing t = new Timing();
        Graph g = new Graph();
        Cities c = new Cities();
        // System.out.println("The distance between "+c.getName(5)+
        //                            " and "+c.getName(10)+" is "+
        //                            c.getDistance(5,10));
		
		System.out.println(printmap(prim(c),c));
    }

	public static Vector<Edge> prim(Cities c) {
		PriorityQueue<Edge> edges = new PriorityQueue<Edge>();
		Vector<Edge> result = new Vector<Edge>();
		// for the list of edges
		// build a list of edges
		for (int i =0;i<18 ;i++ ) {
			for (int j=0;j<18 ;j++ ) {
				if (i!=j) {
					edges.add(new Edge(c,i,j));
				}
			}
		}
		
		//System.out.println(edges);
		
		// make a list of all the cities to check off the list
		Vector<Integer> todo = new Vector<Integer>();
		for (int i=1;i<18 ;i++ ) {
			todo.add(i);
		}

		// take the one with the lightest weight off the top
		while (!todo.isEmpty()) {
			Edge currentEdge = edges.poll();
			// System.out.println("considering " +currentEdge);
			// if the nodes it connects aren't already used
			if ((todo.contains(currentEdge.ind(1))||todo.contains(currentEdge.ind(2)))) {
				if ((!todo.contains(currentEdge.ind(1))&&todo.contains(currentEdge.ind(2)))||(todo.contains(currentEdge.ind(1))&&!todo.contains(currentEdge.ind(2)))) {
					// remove the new node from the todo, and add the edge to the used pile
					todo.remove(((Integer)currentEdge.ind(1)));
					todo.remove(((Integer)currentEdge.ind(2)));
					result.add(currentEdge);				
				}
			}
		}		
		return result;
	}
	
	public static String printmap(Vector<Edge> edges, Cities c) {
		String result = "";
		for (int i=0;i<edges.size() ;i++ ) {
			result+= c.getName(edges.get(i).ind(1)) + " " + c.getName(edges.get(i).ind(2)) + " " + edges.get(i).weight() + "\n";
		}
		return result;
	}
}
