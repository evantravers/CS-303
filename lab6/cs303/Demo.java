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
import cs303.Cities;

public class Demo {
    public static void main(String args[]) throws IOException {
        Data data = new Data();
        Timing t = new Timing();
        Graph g = new Graph();
        Cities c = new Cities();
        System.out.println("The distance between "+c.getName(5)+
                           " and "+c.getName(10)+" is "+
                           c.getDistance(5,10));
    }
}
