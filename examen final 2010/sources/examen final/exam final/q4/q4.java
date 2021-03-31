

public class q4 {

   private static final int MON_MATRICULE =1954607 ; // <= A COMPLETER
   
   public static void main( String [ ] args ) {
      RandomDG rdg = new RandomDG(MON_MATRICULE);
      System.out.println("DG:");
      System.out.println(rdg);
      
      CFC cfc = new CFC(rdg); 
      int[] id = cfc.getID();
      
      for(int i = 0; i<id.length; i++) {
         System.out.println(i + ": " + id[i]);
      }
   }
}
