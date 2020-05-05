import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		Graph g = new Graph();
		System.out.println("TP05 : Graphes");
		
		// Partie 1: A completer : Création du graphe
		Node A=new Node(0,"A");
		Node B=new Node(1,"B");
		Node C=new Node(2,"C");
		Node D=new Node(3,"D");
		Node E=new Node(4,"E");
		Node F=new Node(5,"F");
		Node G=new Node(6,"G");

		Edge AB=new Edge(A, B,2);
		Edge BA=new Edge(B,A,2);
		Edge AC=new Edge(A, C,1);
		Edge CA=new Edge(C,A,1);

		Edge BD=new Edge(B, D,1);
		Edge DB=new Edge(D,B,1);
		Edge BC=new Edge(B, C,2);
		Edge CB=new Edge(C,B,2);
		Edge BE=new Edge(B, E,3);
		Edge EB=new Edge(E,B,3);

		Edge CD = new Edge(C, D,4);
		Edge DC=new Edge(D,C,4);
		Edge CF = new Edge(C, F,5);
		Edge FC=new Edge(F,C,5);

		Edge DG = new Edge(D, G,5);
		Edge GD=new Edge(G,D,5);
		Edge DF = new Edge(D, F,6);
		Edge FD=new Edge(F,D,6);

		Edge EC = new Edge(E, C,3);
		Edge CE=new Edge(C,E,3);
		Edge EF = new Edge(E, F,1);
		Edge FE=new Edge(F,E,1);

		Edge FG = new Edge(F, G,2);
		Edge GF=new Edge(G,F,2);

		List<Node> tempNodes=new ArrayList<Node>();

		tempNodes.add(A);
		tempNodes.add(B);
		tempNodes.add(C);
		tempNodes.add(D);
		tempNodes.add(E);
		tempNodes.add(F);
		tempNodes.add(G);

		List<Edge> tempEdges=new ArrayList<Edge>();

		tempEdges.add(AB);
		tempEdges.add(BA);
		tempEdges.add(AC);
		tempEdges.add(CA);

		tempEdges.add(BE);
		tempEdges.add(EB);
		tempEdges.add(BC);
		tempEdges.add(CB);
		tempEdges.add(BD);
		tempEdges.add(DB);

		tempEdges.add(CD);
		tempEdges.add(DC);
		tempEdges.add(CF);
		tempEdges.add(FC);

		tempEdges.add(EF);
		tempEdges.add(FE);
		tempEdges.add(EC);
		tempEdges.add(CE);

		tempEdges.add(DF);
		tempEdges.add(FD);
		tempEdges.add(DG);
		tempEdges.add(GD);

		tempEdges.add(FG);
		tempEdges.add(GF);

		g.setNodes(tempNodes);
		g.setEdges(tempEdges);




		
		// Partie 2: A completer : Implémentation de l’algorithme Dijkstra

		Dijkstra d = new Dijkstra(g);
		
		d.findPath(A,G);
		
		d.showTable();

		// Affichage le chemin le plus court :
		System.out.println(d.printShortPath(A,G));

	}
}
