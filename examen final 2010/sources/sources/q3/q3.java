public class q3 {

   private static final int MON_MATRICULE = ; // <= A COMPLETER
   
   public static void main( String [ ] args ) {
      RandomDAG rdag = new RandomDAG(MON_MATRICULE);
      System.out.println("DAG:");
      System.out.println(rdag);
      
      TopologicalOrder to = new TopologicalOrder(rdag);
      
      System.out.println("Topological Order 1");
      int[] order1 = to.getOrder1();
      printOrder(order1);
      
      System.out.println("\nTopological Order 2");
      int[] order2 = to.getOrder2();
      printOrder(order2);
   }
   
   static void printOrder(int[] order) {
      for(int i=0; i<order.length; i++) {
         System.out.println(i + " " + order[i]);
      }
   }
}
