

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.Random;

public class RandomDG extends DirectedGraph{

   private static int     DAG_SIZE = 10;
   private static int     NB_EDGES = 15;
   private static int[][] EDGES    = {{0, 1}, {0, 3}, {1, 2}, 
         {1, 7}, {2, 0}, {2, 9}, {3, 4}, {4, 5}, {4, 9}, 
         {5, 3}, {5, 6}, {6, 7}, {6, 9}, {7, 8}, {8, 6}};
   
   static Random generator;
   
   RandomDG(int id){
      
      super(DAG_SIZE);
      
      generator = new Random(id);
      
      LinkedList<Integer> l  = new LinkedList<Integer>();
            
      for(int i = 0; i<DAG_SIZE; i++) {
         l.addLast(i);
      }
      
      LinkedList<Integer> rl = getShuffledSublist(l, 0, V()-1);
      
      for(Integer item : rl)
         System.out.print(item + " ");
      System.out.println();
      
      for(int edge=0; edge<NB_EDGES; edge++) {
         int v1 = rl.get(EDGES[edge][0]);
         int v2 = rl.get(EDGES[edge][1]);
         connect(v1, v2); 
      }
   }
   
   static private LinkedList<Integer> getShuffledSublist(LinkedList<Integer> al, int index1, int index2){
      if( al == null )
         throw new InvalidParameterException();
      if( index1 <0 || index1 >= al.size() )
         throw new InvalidParameterException();
      if( index2 <0 || index2 >= al.size() )
         throw new InvalidParameterException();
      if( index2 < index1 )
         throw new InvalidParameterException();
      
      LinkedList<Integer> l   = new LinkedList<Integer>(al);
      LinkedList<Integer> lal = new LinkedList<Integer>();
      
      int alSublistSize = index2 - index1 + 1;
      
      for(int i = 0; i < alSublistSize; i++) {
         int j = index1 + generator.nextInt(alSublistSize - i);
         Integer item = l.remove(j);
         lal.addLast( item );
      }
      return lal;
   }
}
