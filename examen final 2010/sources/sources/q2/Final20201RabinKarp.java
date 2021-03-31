import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Final20201RabinKarp {

   private static final int MON_MATRICULE = ; // <= A COMPLETER 
   
   private int computeHash(String substring) {

      int p = 0;

      for(int i=0; i<substring.length(); i++){

         char c = substring.charAt(i);

         if( !ENCODING.containsKey( c ) )
            throw new IllegalArgumentException();

         // A COMPLETER
      }

      return p;
   }

   private int updateHash(int oldHash, char oldChar, char newChar) {

      if( !ENCODING.containsKey( oldChar ) )
         throw new IllegalArgumentException();

      if( !ENCODING.containsKey( newChar ) )
         throw new IllegalArgumentException();

      int newHash = oldHash;

      // A COMPLETER

      return newHash;
   }
   
   // DEBUT - NE PAS MODIFIER CETTE PARTIE
   private static final int NB_BOUNDS = 5;
   private static final int[] PRIMES = {7, 11, 17, 19, 23};

   private static final Map<Character, Integer> ENCODING;
   static {
      ENCODING = new HashMap<Character, Integer>();

      for(int i=0; i<26; i++)
         ENCODING.put((char) ('A' + i), i); 
   }
   private static final int d = 26;

   private int p;
   private int m;
   private int h; 
   private String pattern;
   private static final String text = 
         "RKAACGKFHGOHBBJCMAKJIWDBECJBBJJABJGADCDOMEGAKFOECALKEDKQEACG" +
               "NQAEQLNBAOGTFCDPJAFOFQGFHJABAEIEGKBKGGAHDBCGJFEGJMSQAHOMJEII" + 
               "LWFDCHJBFBNSBILRMFFKSSBHJFEBKKINAJMNOGJFEEEJESOAEMNVADCPPFHB" +
               "RPCIQCQADCJJBFPERAFKOMDCHORDKMEMDKEFUDDPAGBGGQRDBDPVFBNCTFIE" +
               "HPDIJOGFJKGQAGICJAIQNSGBOGNACKOBEGPBVABICQDFIFOFAFESCGDIMEBA" +
               "HQBDMPQADQNCDAIFVAKJFJDJNBNEALCJCECFRAHGJGAGLLSCEAMAGKJLBCFJ" +
               "OEDHHPADABKJDFISQEJFKNDFKSVBFLNNAHQILBAPGTFAJRHDAIMRDCJJGDCB" +
               "KOBIMQWCIAAVEBEBFBEQPCDAMSKADHNHGHFGCAHKJBEHEBMDCBEIBGLNEBCG" +
               "LWFBHLKDGEHNGFPFCFJFMWEJLEHDFIFOGFPFCBGLNEGFHJADBDPVAHKJBBIL" +
               "RMAEQLNCDOMEBAPGTGHFGCBBJJAFFKSSBGGQRGJMSQGAKFOADHNHDDPAGCIQ" +
               "CQEHEBMDAIFVCIAAVFBNCTBJGADDFKSVBFPERBAEIEDKMEMEDKQEDIJOGADQ" +
               "NCACGNQDFISQDGEHNEBEBFAFOFQDCBEIAHGJGFHBRPCGDIMDCHORBEQPCGKB" +
               "KGEJESODHHPACFJOEEGPBVECALKFJKGQFBHLKADCPPAGLLSBCGLWDABKJCEA" +
               "MADBECJFAFESBKKINAFKOMEJFKNGKJLBAJMNOBBJCMGBOGNDCJJGAKJFJAHO" +
               "MJBFLNNAKJIWEALCJGJFEEADCJJGAHDBBIMQWDCBKOBDMPQDJNBNFAJRHCEC" +
               "FREIILWEJLEHDAMSKBAOGTFJFMWAEMNVBFBNSCGJFEAHQILAIQNSFHGOHACK" +
               "OBAACGKABICQFIEHPDKEFUAGICJFDCHJEBAHQFCDPJDAIMRBHJFEDAIFVABI" +
               "CQCECFRAHOMJFJKGQBDMPQDFIFOBBJJACEAMAAFOFQBEQPCBHJFEFBHLKEDK" +
               "QEACKOBAJMNOBIMQWDDPAGBFBNSDJNBNDGEHNFDCHJAFKOMFIEHPAHQILAKJ" +
               "IWBKKINGKBKGAHGJGDFISQGHFGCBBJCMDCBKOAHKJBAKJFJBILRMDHHPABFP" +
               "ERACGNQADCPPGAHDBAGLLSEJFKNCFJOEFHBRPADHNHBAEIEEIILWGBOGNGFH" +
               "JAFHGOHDCJJGGFPFCGAKFODKMEMDFKSVDKEFUFFKSSDAMSKAEMNVDAIMRADQ" +
               "NCDABKJFAJRHBCGLWDBECJDBDPVEALCJGJFEEEJLEHAIQNSFJFMWAACGKFAF" +
               "ESFBNCTBAOGTCIAAVADCJJCIQCQBGGQRGJMSQCGJFEBJGADFCDPJBAPGTCDO" +
               "MEEJESODIJOGEGPBVEBAHQEBEBFBFLNNGKJLBAGICJBGLNEDCBEIDCHORECA" +
               "LKCGDIMEHEBMAEQLNDEMDEMRK";

   public static String monPatron(int monMatricule) {

      char[] c = new char[NB_BOUNDS];

      for(int i = 0; i<NB_BOUNDS; i++)
         c[i] = (char) ('A' + (monMatricule % PRIMES[i] ));

      return new String( c );
   }

   Final20201RabinKarp(String pattern){

      if(pattern == null) throw new IllegalArgumentException();
      if(pattern.length() == 0) throw new IllegalArgumentException(); 

      this.pattern = pattern;
      m = pattern.length();
      p = computeHash(pattern);
      h = (int) Math.pow(26, m-1); // on suppose que cela ne deborde pas
   }

   public ArrayList<Integer> findPattern(String text) {

      if(text== null) throw new IllegalArgumentException();
      if(text.length() == 0) throw new IllegalArgumentException();

      ArrayList<Integer> shifts = new ArrayList<Integer>();
      int n = text.length();

      if( n < m )
         return shifts;

      int i = 0;
      String substring = text.substring(0, m);
      int t = computeHash(substring);

      if( t == p && ( substring.equals(pattern) ) )
         shifts.add( i );

      while( ++i <= (n-m) ){
         char oldChar = text.charAt(i - 1);
         char newChar = text.charAt(i + m - 1);
         t = updateHash(t, oldChar, newChar);

         if( t == p && (text.substring(i, i+m).equals(pattern)) )
            shifts.add( i );
      }

      return shifts;
   }

   public static void main(String[] args) {

      String pattern         = monPatron(MON_MATRICULE);

      System.out.println( "Patron recherche:" + pattern );

      Final20201RabinKarp pm = new Final20201RabinKarp(pattern);

      ArrayList<Integer> shifts = pm.findPattern(text);

      for(int s : shifts )
         System.out.println( "Au decalage: " + s + ", on retrouve: " +  text.substring(s, s+pattern.length()));
   }

   // FIN - NE PAS MODIFIER CETTE PARTIE
} 
