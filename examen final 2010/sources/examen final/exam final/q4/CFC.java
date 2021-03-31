import java.security.InvalidParameterException;
import java.util.LinkedList;

public class CFC {
   
   /*
    * Cette méthode est appelée par findOrder(...)
    * Elle retourne dans dfo le DFS post ordre inverse
    * de GT
    */
   private void dfs1(int s, DirectedGraph GT){
      // A COMPLETER

      dfsMarked[s]=true;
      id[s]=count;

      for(int voisin:GT.adj(s))
      {
         if(dfsMarked[voisin]==false)
         {
            dfs1(voisin,GT);
            count++;
         }
      }

   }
   
   // DEBUT - NE PAS MODIFIER CETTE PARTIE
   private boolean[] dfsMarked;
   private boolean[] marked;
   private int[] id;
   private int count;
   LinkedList<Integer> dfo;

   public CFC(DirectedGraph G){
      
      if(G == null) 
         throw new InvalidParameterException();

      dfsMarked = new boolean[G.V()];
      marked    = new boolean[G.V()]; 
      id        = new int[G.V()];
      count     = 1;

      // 1. Trouver l'ordre de visite en executant 
      //    un DFS post ordre sur GT
      findOrder(G);
      
      // 2. Identifier les CFC au moyen d'un parcours
      //    DSF sur G  suivant l'ordre trouvé en 1
      for( int v : dfo ) {
         if( !marked[v] ){
            dfs2(v, G); 
            count++; // nouvelle cfc
         }
      }
   }
   
   // Cette méthode est appelée par le constructeur
   // Rapportez-vous au constructeur pour comprendre
   private void findOrder(DirectedGraph G) {

      DirectedGraph  GT   = G.transposed();            
      dfo  = new LinkedList<Integer>();
      
      for(int s=0; s<GT.V(); s++)
         if( !dfsMarked[s] )
            dfs1(s, GT);
   }

   private void dfs2(int v, Graph G){
      marked[v] = true;

      // identifier la cfc
      id[v] = count;

      for(int w : G.adj(v))
         if(!marked[w])
            dfs2(w, G);
   }
   
   public int[] getID() {
      int[] rID = new int[id.length]; 
      System.arraycopy(id, 0, rID, 0, id.length);
      return rID;
   }
   // FIN - NE PAS MODIFIER CETTE PARTIE
}
