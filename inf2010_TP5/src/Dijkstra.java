import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.*;



public class Dijkstra {

	private Graph graph;
	private Map<Node, Edge> dijkstraTable[];
	private Stack<Edge> path;


	public Dijkstra(Graph g) {
		this.graph = g;
	}

	public void findPath(Node s, Node d) {

		dijkstraTable = new HashMap[graph.getNodes().size()];
		path = new Stack<Edge>();

		// A compléter
		Node currentNode=s;
		boolean visited;  // boolean pour savoir si un noeud a ete visite

		dijkstraTable[0] = new HashMap<>(); // creation du hashmap pour la premiere iteration
		dijkstraTable[0].put(currentNode,new Edge(currentNode,currentNode,0)); // mettre la paire de la premiere iteration

		path.push(dijkstraTable[0].get(getMinimum(dijkstraTable[0]))); // mettre le hashmap de la premiere iteration dans le stack pour le chemin

		for (int i = 1; i < graph.getNodes().size(); i++)
		{
			Node minNode = getMinimum(dijkstraTable[i-1]);  // trouver le noeud avec la distance min de l'iteration precedente

			dijkstraTable[i]= new HashMap<>(dijkstraTable[i-1]);   // creer une copie du hashmap de l'iteration avant
			dijkstraTable[i].remove(minNode); // enlever le noeud min

			List<Edge> listEdges =  graph.getEdgesGoingFrom(minNode); // mettre tous les edges qui part du noeud dans une list

			for (Edge it: listEdges){  // parcours de tous les edges qui sont lie au voisin du noeud
				visited = false;

				Node destination = it.getDestination();
				for (int j = 0; j < i; j++) {
					if (getMinimum(dijkstraTable[j]) == destination) {   // cherche dans tous les iterations avant le courant
						// si il y a un edge min qui vont a la meme destination
						visited = true;   // mettre le chemin trouve dans le stack de path
						break;
					}
				}
				if(visited==false) {  // si aucun des iterations precedentes mene a la destination
					int distance = dijkstraTable[i-1].get(it.getSource()).getDistance() + it.getDistance();  // calcul de la distance du nouveau chemin
					Edge tempEdge = new Edge(it.getSource(), destination, distance);   // creer un nouveau edge

					if (dijkstraTable[i-1].containsKey(destination))  // si dans l'iteration precedente la cle existe deja
					{
						dijkstraTable[i].replace(destination, getMinimum(dijkstraTable[i-1].get(destination), tempEdge));
						// comparer le edge dans l'iteration precedente qui mene a la destination et le nouveau edge cree trouve le plus petit
						// remplacer le edge associe a la cle avec le edge min trouve
					}
					else {
						dijkstraTable[i].put(destination, tempEdge);  // sinon juste mettre le edge dedant
					}
				}
			}
			path.push(dijkstraTable[i].get(getMinimum(dijkstraTable[i])));  // mettre le edge dans le stack de path
		}
	}


	private Node getMinimum(Map<Node, Edge> map) {
		Edge min = null;
		for (Node Key : map.keySet()) {
			if (min ==null || map.get(Key).getDistance() < min.getDistance()) {
				min = map.get(Key);
			}
		}
		return min.getDestination();
	}

	private Edge getMinimum(Edge e1, Edge e2) {
		// A completer
		if(e1==null && e2==null) {
        	return null;
		}
        else if(e1!=null && e2==null) {
        	return e1;
		}
        else if(e1==null && e2!=null) {
        	return e2;
		}
        else {
        	if(e1.getDistance()<e2.getDistance()) {
        		return e1;
			}
        	else {
        		return e2;
			}
		}
	}


	public void showTable() {
		// A complete
		List<Node> allNodes = graph.getNodes();
		HashSet<Node> nodeDisplayed = new HashSet<>();

		for (Node it : allNodes) {
			System.out.print(it.getName() + " | ");
		}
		System.out.println();

		for (Map<Node, Edge> map : dijkstraTable)
		{
			if (map != null)
			{
				for (Node key : allNodes)
				{
					if (map.get(key) != null)
					{
						System.out.print(map.get(key).getDistance() + map.get(key).getSource().getName() + "| ");
						nodeDisplayed.add(key);
					}
					else if ((nodeDisplayed.contains(key))==false)
					{
						System.out.print(" -| ");
					}
					else {
						System.out.print("  | ");
					}
				}
			} else {
				break;
			}
			System.out.println(" ");
		}

	}
		public String printShortPath (Node source, Node destination)
		{
			this.findPath(source, destination);
			StringBuilder chemin = new StringBuilder();
			Edge lastEdge = path.pop();

			int longeurDuChemin = lastEdge.getDistance();
			chemin.append(lastEdge.getDestination().getName() + " ");

			while (!path.empty()) {
				if (!path.empty() && path.peek().getDestination() == lastEdge.getSource()) {
					chemin.append(lastEdge.getSource().getName() + " ");
					lastEdge = path.pop();
				} else {
					path.pop();
				}
			}
			System.out.print("La longueur du plus court chemin est : ");
			System.out.println(longeurDuChemin);
			return "Le chemin le plus court est : " + chemin.reverse().toString();

		}
	}

