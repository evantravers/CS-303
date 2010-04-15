package cs303;

public class Edge implements Comparable {
	private int[] indices = new int[2];
	private Double weight;
	
	public Edge(Cities graph, int ind1, int ind2) {
		indices[0] = ind1;
		indices[1] = ind2;
		weight = graph.getDistance(ind1,ind2);
	}
	
	public Double weight() {
		return weight;
	}
	
	public int start() {
		return indices[0];
	}
	
	public int end() {
		return indices[1];
	}
	
	public int ind(int i) {
		return indices[i-1];
	}
	
	public int compareTo(Object edge) {
		return weight.compareTo((((Edge)edge).weight()));
	}
	public String toString() {
		return indices[0] + " " + indices[1] +  ": " + weight;
	}
}