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
		// 		System.out.println(c.getName(13)+ " to " + c.getName(9)+c.getDistance(13,9));
		// 		System.out.println(c.getName(9)+ " to " + c.getName(2)+c.getDistance(9,3));
        // 		System.out.println("The distance between "+c.getName(5)+
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
		Edge currentEdge = edges.peek();
		todo.remove(((Integer)currentEdge.ind(1)));
		
		while (!todo.isEmpty()) {
			currentEdge = edges.poll();
			// System.out.println("considering " +currentEdge);
			
			// if the beginning of the edge is already visited and the end of the edge hasn't been visited
			if (!todo.contains(currentEdge.ind(1))&&todo.contains(currentEdge.ind(2))) {
				// remove the new node from the todo, and add the edge to the used pile
				todo.remove(((Integer)currentEdge.ind(1)));
				todo.remove(((Integer)currentEdge.ind(2)));
				result.add(currentEdge);		
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
