package testing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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

    private ArrayList<Integer> intArray;
    
    /**
     * Constructs Data class
     * 
     */
    public Data() {
        intArray = new ArrayList<Integer>();
    }

    /**
     * Reads a file containing a list of integers, one per line
     *
     * @param fileName
     *            the file name to read
     */
    public void read(String fileName) {
        intArray.clear();
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
    }

    /**
     * Returns the data read from the file in raw int array form
     *
     */
    public int[] getIntArray() {
        int[] vals = new int[intArray.size()];
        for (int i=0; i<intArray.size(); i++) {
            vals[i] = intArray.get(i).intValue();
        }
        return vals;
    }
    /**
     * Utility function for printing raw int arrays. Prints all the
     * values in the array in a single line.
     *
     * @param array
     *            int array to print
     */
    public void printIntArray(int[] array) {
        for (int i=0; i<array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    /**
     * Utility function for producing arrays of random values,
     * suitable for testing sorting functions at different values of
     * N.
     *
     * @param size
     *            Size of array to produce (i.e. N)
     */
    public int[] getRandom(int size) {
        Random generator = new Random();
        int[] vals = new int[size];

        for (int i=0; i < size; i++) {
            vals[i] = generator.nextInt(100000);
        }
        return vals;
    }
}