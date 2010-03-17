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
    private Vector<Integer> data;
    private Vector<Boolean> visited;
    private int maxDegree;
    private Vector<Integer> nvector;

    private void init() {
		Timing t = new Timing();
        adjacency = new Vector<Vector<Integer>>();
        data = new Vector<Integer>();
        visited = new Vector<Boolean>();
        maxDegree = 0;
        nvector = new Vector<Integer>();
    }
    
    public Graph() {
        init();
    }
    /**
     * Constructor for graph with N number of nodes.
     *
     * @param N
     *            Number of nodes for this graph.
     */
    public Graph(int N) {
        init();
        for (int i=0;i<N;i++) {
            int ni = newNode();
        }
    }

	public int graphLength() {
		return visited.size();
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
    
    public void setUnvisited(int node) { visited.set(node,false); }

	public void setAllUnvisited() {
		for (int i=0;i<visited.size();i++) {
			setUnvisited(i);
		}
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

	public void getNeighborsNR(int node, Stack<Integer> neighbors) {
        // neighbors.empty();
        int degree = adjacency.get(node).size();
        for(int i=0;i<degree;i++) {
            int neighbor = adjacency.get(node).get(i);
            if (neighbor < 0)
                neighbor = -(neighbor + 1);
            neighbors.push(neighbor);
        }
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
    
    public Vector<Integer> bfs() {
    	return bfs(0);
    }
    
    public Vector<Integer> bfs(int node) {
    	// you've encountered a wild node!
		// increment the count
		Vector<Integer> nodeList = new Vector<Integer>();
		nodeList.add(node);
		// mark node as read
		setVisited(node);
		// find its friends that you haven't met and repeat
		// get the neighbors
		Vector<Integer> neighbors = new Vector<Integer>();
		getNeighbors(node,neighbors);
		while (!neighbors.isEmpty()) {
			if (getVisited(neighbors.firstElement())) {
				neighbors.remove(0);
			}
			else {
				nodeList.addAll(bfs(neighbors.remove(0)));
			}
		}
		return nodeList;
    }
    
    public int dfs() {
    	return dfs(0);
    }
    
    public int dfs(int node) {
		// DFS goes through the graph, marks them as read, and returns a node in that component.
    	// you've encountered a wild node!
		// increment the count
		// mark node as read
		setVisited(node);
		// find its friends that you haven't met and repeat
		// get the neighbors
		Stack<Integer> neighbors = new Stack<Integer>();
		getNeighbors(node,neighbors);
		while (!neighbors.empty()) {
			if (getVisited(neighbors.peek())) {
				neighbors.pop();
			}
			else {
				bfs(neighbors.pop());
			}
		}
		return node;
    }

	public int dfsNR() {
		return dfsNR(0);
	}
	
	public int dfsNR(int node) {
		// get neighbors
		// push neighbors to stack
		Stack<Integer> neighbors = new Stack<Integer>();
		getNeighborsNR(node,neighbors);
		// while they still exist
		while (neighbors.size()>1) {
			if (!getVisited(neighbors.peek())) {
				setVisited(neighbors.peek());
				// get top's neighbors, and push them on the stack
				getNeighborsNR(neighbors.pop(),neighbors);
			}
			else {
				neighbors.pop();
			}
		}
		return neighbors.pop();
	}
}
