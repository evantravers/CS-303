package cs303;

public class Node implements Comparable {
	private int indice;
	private Double weight;
	
	public Node(int ind, Double wegh) {
		indice = ind;
		weight = wegh;
	}
	
	public int get() {
		return indice;
	}
	
	public Double weight() {
		return weight;
	}
	
	public int compareTo(Object node) {
		return weight.compareTo((((Node)node).weight()));
	}
}