import java.util.Random;

public class Final20201BinaryHeap<AnyType extends Comparable<? super AnyType>>{

   private static final int MON_MATRICULE = 1954607; // <= A COMPLETER

   private static final int DEFAULT_CAPACITY = 10;
   private static final int NB_BOUNDS = 6;
   private static final int[] PRIMES = {11, 17, 23, 29, 31, 37};

   private int currentSize;   
   private AnyType [ ] array; 

   private int nombre_1;
   private int nombre_2;

   public void printNbInsertPercolations(){
      System.out.println("Nombre 1: " + nombre_1 + " <= VALEUR A INDIQUER");
   }

   public void printNbBuildHeapPercolations(){
      System.out.println("Nombre 2: " + nombre_2 + " <= VALEUR A INDIQUER");
   }

   /**
    * Construct the binary heap.
    */
   public Final20201BinaryHeap( )
   {
      this( DEFAULT_CAPACITY );
   }

   /**
    * Construct the binary heap.
    * @param capacity the capacity of the binary heap.
    */
   @SuppressWarnings("unchecked")
   public Final20201BinaryHeap( int capacity ) {
      initialize();
      array = (AnyType[]) new Comparable[ capacity + 1 ];
   }

   /**
    * Construct the binary heap given an array of items.
    */
   @SuppressWarnings("unchecked")
   public Final20201BinaryHeap( AnyType [ ] items )
   {
      currentSize = items.length;
      array = (AnyType[]) new Comparable[ ( currentSize + 2 ) * 11 / 10 ];

      int i = 1;
      for( AnyType item : items )
         array[ i++ ] = item;
      buildHeap( );
   }

   private void initialize() {
      nombre_1    = 0;
      nombre_2    = 0;
      currentSize = 0;
   }

   /**
    * Insert into the priority queue, maintaining heap order.
    * Duplicates are allowed.
    * @param x the item to insert.
    */
   public void insert( AnyType x )
   {
      if( currentSize == array.length - 1 )
         enlargeArray( array.length * 2 + 1 );

      // Percolate up
      int hole = ++currentSize;
      for( ; hole > 1 && x.compareTo( array[ hole / 2 ] ) < 0; hole /= 2 ) {
         array[ hole ] = array[ hole / 2 ];
         nombre_1++;
      }

      array[ hole ] = x;
      nombre_1++;
   }


   @SuppressWarnings("unchecked")
   private void enlargeArray( int newSize )
   {
      AnyType [] old = array;
      array = (AnyType []) new Comparable[ newSize ];
      for( int i = 0; i < old.length; i++ )
         array[ i ] = old[ i ];        
   }

   /**
    * Find the smallest item in the priority queue.
    * @return the smallest item, or throw an UnderflowException if empty.
    */
   public AnyType findMin( )
   {
      if( isEmpty( ) )
         throw new IndexOutOfBoundsException();
      return array[ 1 ];
   }

   /**
    * Remove the smallest item from the priority queue.
    * @return the smallest item, or throw an UnderflowException if empty.
    */
   public AnyType deleteMin( )
   {
      if( isEmpty( ) )
         throw new IndexOutOfBoundsException( );

      AnyType minItem = findMin( );
      array[ 1 ] = array[ currentSize-- ];
      percolateDown( 1, false );

      return minItem;
   }

   /**
    * Establish heap order property from an arbitrary
    * arrangement of items. Runs in linear time.
    */
   private void buildHeap( )
   {
      for( int i = currentSize / 2; i > 0; i-- )
         percolateDown( i, true );
   }

   /**
    * Test if the priority queue is logically empty.
    * @return true if empty, false otherwise.
    */
   public boolean isEmpty( )
   {
      return currentSize == 0;
   }

   /**
    * Make the priority queue logically empty.
    */
   public void makeEmpty( )
   {
      currentSize = 0;
   }

   /**
    * Internal method to percolate down in the heap.
    * @param hole the index at which the percolate begins.
    */
   private void percolateDown( int hole, boolean buildHeap )
   {
      int child;
      AnyType tmp = array[ hole ];

      for( ; hole * 2 <= currentSize; hole = child )
      {
         child = hole * 2;

         if( buildHeap ) 
            nombre_2++;

         if( child != currentSize &&
               array[ child + 1 ].compareTo( array[ child ] ) < 0 )
            child++;
         if( array[ child ].compareTo( tmp ) < 0 )
            array[ hole ] = array[ child ];
         else
            break;
      }

      array[ hole ] = tmp;

      if( buildHeap ) 
         nombre_2++;
   }

   public String printArray() {
      StringBuilder sb = new StringBuilder(); 
      for (int i=1; i<=currentSize; i++ )
         sb.append(array[i].toString());
      return sb.toString();
   }

   public static int[] monExamen(int monMatricule) {
      int[] id = new int[NB_BOUNDS];

      for(int i = 0; i<NB_BOUNDS; i++)
         id[i] = monMatricule % PRIMES[i];

      return id;
   }

   public static Integer[] cles1(int monMatricule, int nombreCles) {

      int[] id = monExamen(monMatricule);
      int base = PRIMES[ NB_BOUNDS - 1 ];
      Integer[] cles = new Integer[nombreCles];

      Random generator = new Random(monMatricule);

      for(int i=0; i<nombreCles; i++) {
         int bound = id[i % NB_BOUNDS] + 1; 
         int r = generator.nextInt( bound );
         Integer value = new Integer( r - i * base );
         cles[i] = value;
      }

      return cles;
   }

   public static Integer[] cles2(int monMatricule, int nombreCles) {

      int[] id = monExamen(monMatricule);
      int base = PRIMES[ NB_BOUNDS - 1 ];
      Integer[] cles = new Integer[nombreCles];

      Random generator = new Random(monMatricule);

      for(int i=0; i<nombreCles; i++) {
         int bound = id[i % NB_BOUNDS] + 1; 
         int r = generator.nextInt( bound );
         Integer value = new Integer( r + i * base );
         cles[i] = value;
      } 

      return cles;
   }

   public static Integer[] cles3(int monMatricule, int nombreCles) {

      int[] id = monExamen(monMatricule);
      Integer[] cles = new Integer[nombreCles];

      Random generator = new Random(monMatricule);

      for(int i=0; i<nombreCles; i++) {
         int bound = id[i % NB_BOUNDS] + 1; 
         int r = generator.nextInt( bound );
         Integer value = new Integer( r );
         cles[i] = value;
      }

      return cles;
   }

   public static void main( String [ ] args ) {

      final int[] nombreCles   = {1000, 10000, 100000};

      Final20201BinaryHeap<Integer> h;

      for(int i=0; i<3; i++) {

         Integer[] cles1 = cles1(MON_MATRICULE, nombreCles[i]);
         Integer[] cles2 = cles2(MON_MATRICULE, nombreCles[i]);
         Integer[] cles3 = cles3(MON_MATRICULE, nombreCles[i]);

         System.out.println("### Nombre de cles: " + nombreCles[i]);           

         System.out.println("#Jeu de donn�es: cles1");

         h = new Final20201BinaryHeap<Integer>( );

         for(Integer cle : cles1) 
            h.insert(cle);

         h.printNbInsertPercolations();

         h = new Final20201BinaryHeap<Integer>( cles1 );

         h.printNbBuildHeapPercolations();           

         System.out.println("#Jeu de donn�es: cles2");


         h = new Final20201BinaryHeap<Integer>( );

         for(Integer cle : cles2) 
            h.insert(cle);

         h.printNbInsertPercolations();

         h = new Final20201BinaryHeap<Integer>( cles2 );

         h.printNbBuildHeapPercolations();           

         System.out.println("#Jeu de donn�es: cles3");


         h = new Final20201BinaryHeap<Integer>( );

         for(Integer cle : cles3) 
            h.insert(cle);

         h.printNbInsertPercolations();

         h = new Final20201BinaryHeap<Integer>( cles3 );

         h.printNbBuildHeapPercolations();

         System.out.println();
      }
   }
}

