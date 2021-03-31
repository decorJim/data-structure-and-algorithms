import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TopologicalOrder {
   
   private void findTopologicalOrder1() {
      
      int[] inDegree = ((DirectedGraph)G).getInDegree();
      
      Queue<Integer> q = new LinkedList<Integer>();
      
      for(int node = 0; node<G.V(); node++) {
         if(inDegree[node] == 0)
            q.add(node);
      }
      
      int counter = 0;
      while( !q.isEmpty() ) {
         Integer v = q.poll();
         order1[v] = ++counter;
         
         // A COMPLETER
      }
   }
   
   private void findTopologicalOrder2() {
      
      for(int s=0; s<G.V(); s++)
         if( !dfsMarked[s] )
            dfs(s);
      
      int counter = 0;
            
      // A COMPLETER
      
      // Devra comporter la ligne:
      // order2[v] = ++counter;
   }

   // DEBUT - NE PAS MODIFIER
   private Stack<Integer> reversePostOrderDfs;
   boolean[] dfsMarked;
   int[]     dfsParent; 
   int[]     order1; 
   int[]     order2;
   Graph     G;

   TopologicalOrder(DirectedGraph G){
      
      dfsMarked = new boolean[G.V()];
      dfsParent = new int[G.V()];
      order1    = new int[G.V()];
      order2    = new int[G.V()];
      this.G    = G;
      reversePostOrderDfs = new Stack<Integer>(); 
      
      findTopologicalOrder1();
      findTopologicalOrder2();
   }
   
   private void dfs(int s){
      dfsMarked[s] = true;

      for(int w : G.adj(s))
         if( !dfsMarked[w] ){
            dfs(w);
            dfsParent[w] = s;
         }
      
      // Stack  vertex 
      reversePostOrderDfs.push(s);
   }
   
   int[] getOrder1() {
      int[] rOrder1 = new int[G.V()]; 
      System.arraycopy(order1, 0, rOrder1, 0, G.V());
      return rOrder1;
   }

   public int[] getOrder2() {
      int[] rOrder2 = new int[G.V()]; 
      System.arraycopy(order2, 0, rOrder2, 0, G.V());
      return rOrder2;
   }
   // FIN - NE PAS MODIFIER
}
