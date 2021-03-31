import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.Random;

public class RandomDAG extends DirectedGraph{

   private static int         DAG_SIZE = 10;
   private static final int   DEGREE   = 2;
   
   static Random generator;
   
   RandomDAG(int id){
      
      super(DAG_SIZE);
      
      generator = new Random(id);
      
      LinkedList<Integer> l  = new LinkedList<Integer>();
            
      for(int i = 0; i<V(); i++) {
         l.addLast(i);
      }
      
      LinkedList<Integer> rl = getShuffledSublist(l, 0, V()-1);

      for(int i = 0; i<this.V()-DEGREE; i++) {
         
         int v1 = rl.get(i);
         LinkedList<Integer> destCandidates = getShuffledSublist(rl, i+1, V()-1);
         
         for(int j=0; j<DEGREE; j++) {
            int v2 = destCandidates.get(j);
            connect(v1, v2);
         }
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
