import java.io.IOException;
import java.util.Arrays;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Vector;

import cs303.Stopwatch;
import cs303.Data;
import cs303.Timing;
import cs303.Graph;

public class Demo {
    public static void main(String args[]) throws IOException {
		// TODO investigate java's priority queue
        Data data = new Data();
        Timing t = new Timing();
        Graph g = new Graph("graph.off");
		System.out.println(g.dijkstra(1,18));
    }
}
