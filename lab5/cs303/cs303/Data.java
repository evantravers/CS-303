package cs303;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.util.List;
import java.util.regex.Pattern;
import java.util.Random;

/**
 * A data-handling class. Reads files (of integers). Produces random
 * arrays.
 * 
 * @author David O'Gwynn
 * 
 */
public class Data {
    static private Pattern isNumber = Pattern.compile("^[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?$");
    static private Pattern isInteger = Pattern.compile("^[-+]?[0-9]*");

    /**
     * Constructs Data class
     * 
     */
    public Data() {
    }

    /**
     * Reads a file containing a list of integers, one per line. To be
     * used when the number of integers in the file is known.
     *
     * @param fileName
     *            the file name to read
     *
     * @param values
     *            int array correctly sized to number of integers in
     *            file
     *
     */
    public void read(String fileName, int[] values) {
        ArrayList<Integer> intArray = new ArrayList<Integer>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
            while (scanner.hasNextLine()) {
                intArray.add(new Integer(scanner.nextLine()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        for (int i=0; i<intArray.size(); i++) {
            values[i] = intArray.get(i).intValue();
        }
    }
    /**
     * Reads a file containing a list of integers, one per line. To be
     * used when it is unknown how many integers are in the file.
     *
     * @param fileName
     *            the file name to read
     *
     * @return int array of values read from file
     */
    public int[] read(String fileName) {
        ArrayList<Integer> intArray = new ArrayList<Integer>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
            while (scanner.hasNextLine()) {
                intArray.add(new Integer(scanner.nextLine()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        int[] values = new int[intArray.size()];
        for (int i=0; i<intArray.size(); i++) {
            values[i] = intArray.get(i).intValue();
        }
        return values;
    }

    public void write(String fileName, int[] array) {
        PrintWriter out = null;
        try {
            out=new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
            for (int i=0;i<array.length;i++) {
                out.println(array[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * For producing arrays of random values, suitable for testing
     * sorting functions at different values of N. <b>For large values
     * of size, duplicate values are probable.</b>
     *
     * @param size
     *            Size of array to produce (i.e. N)
     */
    public int[] getRandom(int size) {
        Random generator = new Random();
        int[] vals = new int[size];
        int maxN = size*100;
        for (int i=0; i < size; i++) {
            vals[i] = generator.nextInt(maxN);
        }
        return vals;
    }

    /**
     * For producing arrays of ordered values from 0 (inclusive) to
     * stop (exclusive), suitable for testing sorting functions at
     * different values of N.
     *
     * @param stop
     *            End value of range (exclusive)
     *
     * @return int array of values
     */
    public int[] getRange(int stop) {
        int[] vals = new int[stop];
        for (int i=0; i < stop; i++) {
            vals[i] = i;
        }
        return vals;
    }

    /**
     * For producing arrays of ordered values from start (inclusive)
     * to stop (exclusive), suitable for testing sorting functions at
     * different values of N. <b>start must be less than stop</b>.
     *
     * @param start
     *            Start value of range (inclusive). 
     *
     * @param stop
     *            End value of range (exclusive)
     *
     * @return int array of values
     */
    public int[] getRange(int start, int stop) {
        int size = stop-start;
        int[] vals = new int[size];
        for (int i=start; i < stop; i++) {
            vals[i] = i;
        }
        return vals;
    }

    /**
     * For producing arrays of reverse ordered values from size-1 down
     * to 0, suitable for testing sorting functions at different
     * values of N.
     *
     * @param size
     *            Size of array to produce (i.e. N)
     *
     * @return int array of values
     */
    public int[] getRevRange(int size) {
        int[] vals = new int[size];
        for (int i=size-1; i >= 0; i--) {
            vals[i] = 0;
        }
        return vals;
    }

    /**
     * For producing arrays of ordered values from random starting
     * value (inclusive) to that value + size (exclusive). 
     *
     * @param size
     *            From random start to random start + size
     *
     * @return int array of values
     */
    public int[] getRandRange(int size) {
        Random gen = new Random();
        int I = gen.nextInt(size*10)+1;
        int[] vals = new int[size];
        for (int i=0; i < size; i++) {
            vals[i] = I++;
        }
        return vals;
    }

    /**
     * For producing arrays of query values suitable for testing
     * search algorithms.
     *
     * @param array
     *            Array from which the queries will come. Assumed to
     *            be non-decreasing.
     *
     * @param queries
     *            Query array. Length of array determines the number
     *            of queries to produce.
     *
     * @param outPct
     *            Percentage (as double [0..1]) of queries to be
     *            misses
     *
     * @param truth
     *            Ground truth array. If queries[i] is in array,
     *            truth[i] is the index int array of queries[i],
     *            otherwise truth[i]==-1.
     *
     */
    public void getQueries(int[] array, int[] queries, double outPct,
                           int[] truth) {
        Random generator = new Random();
        for(int i=0;i<queries.length;i++) {
            double out = generator.nextDouble();
            //System.out.println(out);
            //System.exit(0);
            int qIndex = generator.nextInt(array.length);
            int qVal = array[qIndex];
            truth[i] = qIndex;
            if (out < outPct) {
                qVal = (array[array.length-1] +
                        generator.nextInt(array.length)+1);
                truth[i] = -1;
            }
            queries[i] = qVal;
        }
    }

}