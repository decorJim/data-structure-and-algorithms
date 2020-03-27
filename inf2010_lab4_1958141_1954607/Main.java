import java.util.*; 


public class Main 
{
   /**
     * Fonction principale
     */
   public static void main(String[] args) 
   {
      // Creer un monceau avec 22 éléments et un tableau équivalent
      int numItems = 22;
      BinaryHeap<Integer> heap = new BinaryHeap<Integer>(true);
      
      Integer [ ] items = new Integer[ numItems ];

      int i;
      int j;

      // En insérant les éléments un a un
      for( i = 11, j = 0; j != numItems; i = ( i + 37 ), j++ )
      {
	  heap.offer( i );
	  items[ j ] = i;

	  i %=  numItems; 
      }

      // en construisant le monceau depuis le depart
      System.out.println("Monceau min contruit element par element:");
      System.out.println( heap.printFancyTree() );

      heap = new BinaryHeap<Integer>(false);
      // en inserant les elements un a un
      for( Integer item : items)
	  heap.offer( item );

      // en construisant le monceau depuis le depart
      System.out.println("Monceau max contruit element par element:");
      System.out.println( heap.printFancyTree() );

      heap = new BinaryHeap<Integer>(items,false);
      System.out.println("Monceau max contruit a partir d'un tableau:");
      System.out.println( heap.printFancyTree() );

      heap = new BinaryHeap<Integer>(items,true);
      System.out.println("Monceau min contruit a partir d'un tableau:");
      System.out.println( heap.printFancyTree() );

      System.out.println();
      System.out.println("Affichage recursif:");
      System.out.println( heap.printFancyTree() );

      System.out.println("Affichage non recursif:");
      System.out.println( heap.nonRecursivePrintFancyTree() );

      System.out.println();
      System.out.println("Tableau d'origine:");
      System.out.println( printArray( items ) );
      
      BinaryHeap.heapSort( items );
      System.out.println("Tableau ordonne:");
      System.out.println( printArray( items ) );

      BinaryHeap.heapSortReverse( items );
      System.out.println("Tableau inversement ordonne:");
      System.out.println( printArray( items ) );


      /*
       * Ajouter appels pour repondre a la question
       **/
      BinaryHeap<Integer> maxHeap = new BinaryHeap<Integer>(false);
      BinaryHeap<Integer> minHeap = new BinaryHeap<Integer>(true);
      PriorityQueue<Integer> priorityQueueTest = new PriorityQueue<Integer>();

      for(int temp : items)
      {
         maxHeap.offer(temp);
         minHeap.offer(temp);
         priorityQueueTest.offer(temp);
      }

      int maxElem = maxHeap.poll();
      boolean isMaxValue = true;
      Iterator<Integer> iteratorMaxHeap = maxHeap.iterator();

      while(iteratorMaxHeap.hasNext())
      {
         if(maxElem < iteratorMaxHeap.next())
         {
            isMaxValue = false;
         }
      }

      int minValue = minHeap.peek();
      boolean isMinValue = true;
      Iterator<Integer> iterator = minHeap.iterator();

      while(iterator.hasNext())
      {
         if(minValue > iterator.next())
         {
            isMinValue = false;
         }
      }

      boolean percolate = true;
      boolean iteratorNextTest = true;
      boolean PeekTest = true;
      boolean PollTest = true;
      boolean iteratorHasNextTest = true;

      for(i = 1; i < minHeap.size(); i++)
      {
         if(minHeap.peek() != priorityQueueTest.peek()) {
            PeekTest = false;
         }
         if(minHeap.poll() != priorityQueueTest.poll()) {

            PollTest = false;
            Iterator<Integer> iterator3 = minHeap.iterator();
            Iterator<Integer> iterator4 = minHeap.iterator();

            while(iterator3.hasNext())
            {
               if(iterator3.next() != iterator4.next())
               {
                  percolate = false;
                  iteratorNextTest = false;
               }
               if(iterator3.hasNext() != iterator4.hasNext())
               {
                  iteratorHasNextTest = false;
               }

            }
         }
      }

      System.out.print("\n");

      minHeap.clear();
      priorityQueueTest.clear();

      boolean isPollTest = true;
      boolean isPeekTest = true;

      if(minHeap.poll() != null)
      {
         isPollTest = false;
      }

      if(minHeap.peek() != null)
      {
         isPeekTest = false;
      }

      minHeap.clear();

      boolean itElementExcept = false;
      boolean itUnavailableOperationExept = false;

      Iterator<Integer> it1 = minHeap.iterator();

      try
      {
         it1.next();
      }
      catch (NoSuchElementException error)
      {
         itElementExcept = true;
      }


         finally {

         try {
            it1.remove();
         } catch (UnsupportedOperationException error) {
            itUnavailableOperationExept = true;
         }
         finally {

            int totalPoint = 0;

            if (isMinValue && isMaxValue && PeekTest && PollTest && isPeekTest && isPollTest && percolate) {
               System.out.print("*****************************************************************\n");
               System.out.println(" ");
               System.out.print("Test de offer() : OK! 8/8 \n");
               System.out.println(" ");
               System.out.print("*****************************************************************\n");
            } else {
               System.out.print("*****************************************************************\n");
               System.out.println(" ");
               System.out.print("Test de offer() : Echec! \n");
               System.out.println(" ");
               System.out.print("*****************************************************************\n");
            }
            System.out.println(" ");

            if (isMinValue) {
               System.out.print("Test pour le minimum : OK! 1/1 \n");
               totalPoint++;
            } else {
               System.out.print("Test pour le minimum : Echec! 0/1 \n");
            }

            if (isMaxValue) {
               System.out.print("Test pour le maximum : OK! 1/1 \n");
               totalPoint++;
            } else {
               System.out.print("Test pour le maximum : Echec! 0/1 \n");
            }

            if (PeekTest) {
               System.out.print("Test pour peek() : OK! 1/1 \n");
               totalPoint++;
            } else {
               System.out.print("Test pour peek() : Echec! 0/1 \n");
            }

            if (PollTest) {
               System.out.print("Test pour poll() : OK! 1/1 \n");
               totalPoint++;
            } else {
               System.out.print("Test pour poll() : Echec! 0/1 \n");
            }


            if (isPeekTest) {
               System.out.print("Test Dirty pour peek() : OK! 1/1 \n");
               totalPoint++;
            } else {
               System.out.print("Test Dirty pour peek() : Echec! 0/1 \n");
            }

            if (isPollTest) {
               System.out.print("Test Dirty pour poll() : OK! 1/1 \n");
               totalPoint++;
            } else {
               System.out.print("Test Dirty pour poll() : Echec! 0/1 \n");
            }

            if (percolate && isMaxValue) {
               System.out.print("Test pour percolateDownMinHeap() : OK! 1/1 \n");
               System.out.print("Test pour percolateDownMaxHeap() : OK! 1/1 \n");
               totalPoint += 2;
            } else {
               System.out.print("Test pour percolateDownMinHeap() : Echec!  0/1 \n");
               System.out.print("Test pour percolateDownMaxHeap() : Echec!  0/1 \n");
            }

            System.out.println(" ");
            System.out.print("*****************************************************************\n\n");


            if (iteratorNextTest && itElementExcept && iteratorHasNextTest && itUnavailableOperationExept) {
               System.out.print("*****************************************************************\n");
               System.out.println(" ");
               System.out.print("Test pour l'iterateur : OK! 4/4 \n");
               System.out.println(" ");
               System.out.print("*****************************************************************\n");
            } else {
               System.out.print("*****************************************************************\n");
               System.out.println(" ");
               System.out.print("Test pour l'iterateur : Echec! \n");
               System.out.println(" ");
               System.out.print("*****************************************************************\n");
            }

            System.out.println(" ");

            if (iteratorNextTest) {
               System.out.print("Test 1 pour next() : OK! 1/1 \n");
               totalPoint++;
            } else {
               System.out.print("Test 1 pour next() : Echec!  0/1 \n");
            }

            if (itElementExcept) {
               System.out.print("Test 2 pour next() : OK! 1/1 \n");
               totalPoint++;
            } else {
               System.out.print("Test 2 pour next() : Echec!  0/1 \n");
            }

            if (iteratorHasNextTest) {
               System.out.print("Test pour hasNext() : OK! 1/1 \n");
               totalPoint++;
            } else {
               System.out.print("Test pour hasNext() : Echec!  0/1 \n");
            }

            if (itUnavailableOperationExept) {
               System.out.print("Test pour remove() : OK! 1/1 \n");
               totalPoint++;
            } else {
               System.out.print("Test pour remove() : Echec! 0/1 \n");
            }

            System.out.println(" ");
            System.out.print("*****************************************************************\n\n");

            System.out.print("points totals des tests : " + totalPoint + "/12\n");
         }
      }
   }

   private static <AnyType> String printArray(AnyType[] a)
   {
      String outputString = "";

      for(AnyType item : a)
      {
         outputString += item;
         outputString += ", ";
      }

      return outputString.substring(0,outputString.length()-2);
   }
}
