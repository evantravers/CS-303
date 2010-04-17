package cs303;
import java.util.*;

public class Prim {
	private static Cities c;
	
	public Prim(Cities cityGraph) {
		c=cityGraph;
	}
	
	public ArrayList<Edge> search(int begNode) {
		// build list of edges for the result
		ArrayList<Edge> result = new ArrayList<Edge>();

		// you have a list of nodes that have been visited
		ArrayList<Integer> done = new ArrayList<Integer>();

		// populate the todo
		ArrayList<Integer> todo = new ArrayList<Integer>();
		for (int i=0;i<18 ;i++ ) {
			todo.add(i);
		}

		// stick the start node into done, remove from todo
		done.add(begNode);
		todo.remove(begNode);

		// this will be used for the getting of options
		PriorityQueue<Edge> edges = new PriorityQueue<Edge>();

		// until all the nodes are in done
		while (done.size()!=18) {
			// System.out.println("todo:			"+todo);
			// System.out.println("done:			"+done);
			// System.out.println("current result: \t"+result);
			// System.out.println("");
			// get the options currently available, starting at done nodes
			// and ending on "todo" nodes, and load them into a priority queue
			for (int i=0;i<done.size() ;i++ ) {
				int start = done.get(i);
				for (int j=0;j<todo.size() ;j++ ) {
					int end = todo.get(j);
					// System.out.println(" edge we are adding is " + start + " to " + end);
					edges.add(new Edge(c, start, end));
				}
			}

			// take the one off the top, add it to result
			Edge winner = edges.poll();
			// System.out.println("The winner is: " + winner);
			result.add(winner);

			// set it's new node as visited
			done.add((Integer)winner.end());
			todo.remove((Integer)winner.end());

			// clear the edges at the end
			edges.clear();
		}

		return result;
	}
	
	public static String printmap(ArrayList<Edge> edges) {
		//ArrayList<Edge> edges2 = Collection.sort(edges);
		String result = "";
		for (int i=0;i<edges.size() ;i++ ) {
			result+= c.getName(edges.get(i).ind(1)) + " " + c.getName(edges.get(i).ind(2)) + " " + edges.get(i).weight() + "\n";
		}
		return result;
	}
}