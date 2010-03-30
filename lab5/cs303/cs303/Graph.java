package cs303;

import java.lang.Math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Random;

/**
 * A class to represent simple undirected graphs
 *
 * @author David O'Gwynn
 * 
 */
public class Graph {
    private Vector<Vector<Integer>> adjacency;
    private Vector<Vector<Double>> distances;
    private Vector<Vector<Double>> verts;
    private Vector<Integer> data;
    private Vector<Boolean> visited;
    private int maxDegree;
    private Vector<Integer> nvector;

    private void init() {
        adjacency = new Vector<Vector<Integer>>();
        distances = new Vector<Vector<Double>>();
        verts = new Vector<Vector<Double>>();
        data = new Vector<Integer>();
        visited = new Vector<Boolean>();
        maxDegree = 0;
        nvector = new Vector<Integer>();
    }
    public Graph() {
        init();
    }

    public void buildFromOFF(String fileName) {
        Scanner scanner = null;
        init();
        verts.clear();
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
            scanner.next(); // OFF
            int nVerts = scanner.nextInt();
            int nFaces = scanner.nextInt();
            int nEdges = scanner.nextInt();
            Vector<Integer> face = new Vector<Integer>();
            for (int i=0;i<nVerts;i++) {
                Vector<Double> vert = new Vector<Double>();
                for (int j=0;j<3;j++)
                    vert.add(new Double(scanner.nextDouble()));
                verts.add(vert);
                distances.add(new Vector<Double>());
                newNode();
            }
            for (int i=0;i<nFaces;i++) {
                face.clear();
                int degree = scanner.nextInt();
                for(int j=0;j<degree;j++) {
                    face.add(new Integer(scanner.nextInt()));
                }
                for(int j=0;j<degree;j++) {
                    int fj = face.get(j);
                    Vector<Double> vj = verts.get(fj);
                    int fjp1 = face.get((j+1)%degree);
                    Vector<Double> vjp1 = verts.get(fjp1);
                    double d=0,dist = 0;
                    for(int k=0;k<3;k++) {
                        d = vj.get(k) - vjp1.get(k);
                        dist += d*d;
                    }
                    addEdge(fj,fjp1,dist);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        System.out.println("Done parsing file");
    }

    /**
     * Constructor for graph from OFF file.
     *
     * @param fileName
     *            File name of OFF file from which to read
     */
    public Graph(String fileName) {
        buildFromOFF(fileName);
    }

    /**
     * Constructor for graph with C number of connected components, D
     * depth per component and B branching factor (maximum number of
     * children at each node).
     *
     * @param C
     *            Number of connected components
     *
     * @param D
     *            Depth of graph (depth of graph interpretted as a
     *            tree with the "root" of each component as the root
     *            of the tree)
     *
     * @param B
     *            Branching factor
     *
     */
    public Graph(int C,int D, int B) {
        init();
        int numNodes = 0;
        int root,node,child,depth=0;
        int np=0,nc=0;
        LinkedList<Integer> to_fill = new LinkedList<Integer>();
        for(int i=0;i<C;i++) {
            root = newNode();
            for(int j=0;j<B;j++) {
                child = newNode();
                addEdge(root,child);
                to_fill.add(child);
            }
            np = B;
            depth = 1;
            while(depth < D) {
                nc = 0;
                for (int j=0;j<np;j++) {
                    node = to_fill.remove();
                    for (int k=0;k<B-1;k++){
                        child = newNode();
                        addEdge(node,child);
                        to_fill.add(child);
                        nc++;
                    }
                }
                np = nc;
                depth++;
            }
            to_fill.clear();
        }
    }

    /**
     * Number of nodes in this graph
     */
    public int size() { return adjacency.size(); }

    /**
     * Maximum degree for any node in the graph (branching factor)
     */
    public int getMaxDegree() { return maxDegree; }

    /**
     * Create a new node in the graph
     */
    public int newNode() {
        int i = adjacency.size();
        Vector<Integer> list = new Vector<Integer>();
        adjacency.add(list);
        data.add(0);
        visited.add(false);
        return i;
    }

    /**
     * Get data stored for this node.
     *
     * @param node
     *            Node index get data
     *
     */
    public int getData(int node) { return data.get(node); }
    /**
     * Set data for this node.
     *
     * @param node
     *            Node index to set data for
     *
     * @param value
     *            Integer value to set for this node
     */
    public void setData(int node, int value) { data.set(node,value); }

    /**
     * Has this node been visited yet?
     *
     * @param node
     *            Node index 
     *
     */
    public boolean getVisited(int node) { return visited.get(node); }
    /**
     * Set node as visited
     *
     * @param node
     *            Node index 
     *
     */
    public void setVisited(int node) { visited.set(node,true); }
    /**
     * Set all nodes in graph as unvisited
     *
     */
    public void clearVisited() {
        for(int i=0;i<visited.size();i++)
            visited.set(i,false);
    }

    /**
     * Does this undirected graph have an edge between node i and node j?
     *
     * @param ni
     *            Node index for node i
     *
     * @param nj
     *            Node index for node j
     *
     */
    public boolean hasEdge(int ni, int nj) {
        getNeighbors(ni,nvector);
        for(int i=0;i<nvector.size();i++) {
            if (nvector.get(i) == nj) {
                // already exists
                return true;
            }
        }
        return false;
    }
    /**
     * Add an edge between node i and node j
     *
     * @param ni
     *            Node index for node i
     *
     * @param nj
     *            Node index for node j
     *
     */
    public void addEdge(int ni, int nj) {
        // Check to see if this edge exists
        if (!hasEdge(ni,nj)) {
            // nj is ni's child, so nj is added as positive index
            adjacency.get(ni).add(nj);
            // ni is nj's parent, so ni is added as negative index
            adjacency.get(nj).add(-(ni+1));

            int degreeI = adjacency.get(ni).size();
            if (degreeI > maxDegree) maxDegree = degreeI;
            int degreeJ = adjacency.get(nj).size();
            if (degreeJ > maxDegree) maxDegree = degreeJ;
        }
    }
    /**
     * Add an edge between node i and node j
     *
     * @param ni
     *            Node index for node i
     *
     * @param nj
     *            Node index for node j
     *
     * @param distance
     *            Distance from node i to node j
     *
     */
    public void addEdge(int ni, int nj, double distance) {
        // Check to see if this edge exists
        if (!hasEdge(ni,nj)) {
            addEdge(ni,nj);
            distances.get(ni).add(distance);
            distances.get(nj).add(distance);
        }
    }

    /**
     * Remove an edge between node i and node j. Not tested.
     *
     * @param ni
     *            Node index for node i
     *
     * @param nj
     *            Node index for node j
     *
     */
    public void removeEdge(int ni, int nj) {
        getNeighbors(ni,nvector);
        for(int i=0;i<nvector.size();i++) {
            if (nvector.get(i) == nj)
                adjacency.get(ni).remove(i);
        }
        getNeighbors(nj,nvector);
        for(int i=0;i<nvector.size();i++) {
            if (nvector.get(i) == ni)
                adjacency.get(nj).remove(i);
        }
    }


    /**
     * Get neighbors for this node
     *
     * @param node
     *            Node index
     *
     * @param neighbors
     *            Vector&lt;Integer&gt; which will be cleared and
     *            populated with the neighbors for node
     *
     */
    public void getNeighbors(int node, Vector<Integer> neighbors) {
        neighbors.clear();
        int degree = adjacency.get(node).size();
        for(int i=0;i<degree;i++) {
            int neighbor = adjacency.get(node).get(i);
            if (neighbor < 0)
                neighbor = -(neighbor + 1);
            neighbors.add(neighbor);
        }
    }

    /**
     * Get neighbors for this node
     *
     * @param node
     *            Node index
     *
     * @param neighbors
     *            Stack&lt;Integer&gt; which will be cleared and
     *            populated with the neighbors for node
     *
     */
    public void getNeighbors(int node, Stack<Integer> neighbors) {
        neighbors.empty();
        int degree = adjacency.get(node).size();
        for(int i=0;i<degree;i++) {
            int neighbor = adjacency.get(node).get(i);
            if (neighbor < 0)
                neighbor = -(neighbor + 1);
            neighbors.push(neighbor);
        }
    }

    /**
     * Get the 3D point in space where graph node is located.
     *
     * @param index
     *            Graph node
     *
     * @param vert
     *            3D double array to store information
     *
     */
    public void getVertex(int index, double[] vert) {
        for(int i=0;i<3;i++)
            vert[i] = verts.get(index).get(i);
    }

    /**
     * Get the distance between node I and node J
     *
     * @param ni
     *            Index for node I
     *
     * @param nj
     *            Index for node J
     *
     */
    public double getDistance(int ni, int nj) {
        getNeighbors(ni,nvector);
        for(int i=0;i<nvector.size();i++) {
            if (nvector.get(i) == nj)
                return distances.get(ni).get(i);
        }
        return Double.POSITIVE_INFINITY;
    }

	public void setDistance(int node, Double distance) {
		distances.set(node, distance);
	}

    /**
     * Print graph adjacency list
     *
     */
    public void print() {
        Vector<Integer> nbors = new Vector<Integer>();
        for(int i=0;i<adjacency.size();i++) {
            getNeighbors(i,nbors);
            System.out.print(i+": ");
            for(int j=0;j<nbors.size();j++)
                System.out.print(nbors.get(j)+" ");
            System.out.println();
        }
    }
}