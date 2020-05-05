import java.util.ArrayList;
import java.util.List;



public class Graph {

	private List<Node> nodes; // Noeuds
	private List<Edge> edges; // Les arcs
	
	public Graph() {
		// A compléter
		nodes=new ArrayList<Node>();
		edges=new ArrayList<Edge>();
	}
	
	public List<Edge> getEdgesGoingFrom(Node source) {
		// A complèter
		List<Edge> edgesGoingFrom=new ArrayList<Edge>();

		for(Edge it:edges) {
			if(it.getSource().getName()==source.getName()) {
				edgesGoingFrom.add(it);
			}
		}
		return edgesGoingFrom;
	}
	public List<Edge> getEdgesGoingTo(Node dest) {
		// A complèter
		List<Edge> edgesGoingTo=new ArrayList<Edge> ();

		for(Edge it:edges) {
			if(it.getDestination().getName()==dest.getName()) {
				edgesGoingTo.add(it);
			}
		}
		return edgesGoingTo;
	}
	
	// Accesseurs 
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	public List<Edge> getEdges() {
		return edges;
	}
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	
}
