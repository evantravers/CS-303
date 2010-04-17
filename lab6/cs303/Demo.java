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
import cs303.Prim;
	

public class Demo {
    public static void main(String args[]) throws IOException {
        Data data = new Data();
        Timing t = new Timing();
        Graph g = new Graph();
        Cities c = new Cities();
		Prim p = new Prim(c);
		int theNode=0;
		
        		System.out.println("The distance between "+c.getName(6)+
                                                         " and "+c.getName(0)+" is "+
                                                         c.getDistance(6,0));
		if (args.length>0) {
			theNode = Integer.parseInt(args[0]);
		}
		ArrayList<Edge> answer = p.search(theNode);
		System.out.println(p.printmap(answer));
    }
}
