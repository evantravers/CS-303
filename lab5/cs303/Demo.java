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
import cs303.Node;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Set;

import java.util.Vector;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Demo {
    public static void main(String args[]) throws IOException {
        Data data = new Data();
        Timing t = new Timing();
		int target = 0;
		if (args.length>0) {
			target = Integer.parseInt(args[0]);
		}
        Graph g = new Graph("graph.off");
		// Vector<Double> answer = g.d(target);
		System.out.println(g.dd(target));
		//System.out.println(printGraph(answer));
		// outputStringasCSVspecial("outputfornode"+target+".csv", answer);
    }

	public static String printGraph(Vector<Double> input) {
		String output = "";
		for (int i=0;i<input.size() ;i++ ) {
			output += "" + i + "," + input.get(i) + "\n";
		}
		return output;
	}
	
	public static void outputStringasCSV(String fileName, Vector<Double> input) throws IOException {
        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter(fileName));
		for (int i=0;i<input.size() ;i++ ) {
			writer.write(i+","+input.get(i));
			writer.newLine();
		}
        writer.close();
    }

	public static void outputStringasCSVspecial(String fileName, Vector<Double> input) throws IOException {
        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter(fileName));
		int[] answers = {1,18,29,126,212,272,289,336};
		for (int i=0;i<answers.length;i++ ) {
			writer.write(answers[i]+","+input.get(answers[i]));
			writer.newLine();
		}
        writer.close();
    }
}
