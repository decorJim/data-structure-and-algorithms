import java.util.*;


public class BinaryHeap<AnyType extends Comparable<? super AnyType>> extends AbstractQueue<AnyType> {
    private static final int DEFAULT_CAPACITY = 100;
    private int currentSize;      // Nombre d'elements
    private AnyType[] array;    // Tableau contenant les donnees (premier element a l'indice 1)
    private boolean min;
    private int modifications;    // Nombre de modifications apportees a ce monceau

    @SuppressWarnings("unchecked")
    public BinaryHeap(boolean min) {
        this.min = min;
        currentSize = 0;
        array = (AnyType[]) new Comparable[DEFAULT_CAPACITY + 1];
    }

    @SuppressWarnings("unchecked")
    public BinaryHeap(AnyType[] items, boolean min) {
        this.min = min;

        // COMPLETEZ
        // invoquez buildMinHeap() ou buildMaxHeap() en fonction du parametre min;
        array = (AnyType[]) new Comparable[DEFAULT_CAPACITY + 1]; // creer un tableau temporaire avec une case candidat

        int i=1;
        for(AnyType item : items) {
            array[i++] = item;       // recopier tous les elements de l'ancien tableau au nouveau a partir de position 1
        }

        currentSize = items.length; // le nombre d'element est le nombre d'element dans notre tableau avec un element de plus

        if (min) {
            buildMinHeap();  // si c'est un minimum appeler la fonction pour creer un min heap
        } else {
            buildMaxHeap(); // sinon appeler la fonction pour creer un max heap
        }
    }

    public boolean offer(AnyType x) {
        if (x == null) {
            throw new NullPointerException("Cannot insert null in a BinaryHeap");
        }

        if (currentSize + 1 == array.length) {
            doubleArray();
        }

        // COMPLETEZ
        if (array[1] == null) {
            array[1] = x;  // si il y a rien a la position 1 mettre l'element a inserer a la position 1
        } else {
            int currentPosition = currentSize + 1;
            array[currentSize + 1] = x; // sinon inserer l'element a une position apres le dernier element present dans le tableau
            int compareResult = array[currentPosition].compareTo(array[(int) ((currentPosition) / 2)]); // comparer element inserer avec parent
            while (((min && compareResult < 0) || (!min && compareResult > 0))) { // si element plus petit que parent et que c'est un min heap ou si l'element est plus grand que son parent et que c'est un max heap

                swapReferences(currentPosition, (int) ((currentPosition) / 2));   // changer la position de l'element avec celui de son parent
                if (currentPosition / 2 != 1) {
                    currentPosition = currentPosition / 2; // mettre a jour la position courante
                    compareResult = array[currentPosition].compareTo(array[(int) ((currentPosition) / 2)]); // comparer element inserer avec parent
                } else {
                    break;
                }
            }
        }
        currentSize++;
        modifications++;
        return true;
    }


    public AnyType peek() {
        if (!isEmpty()) {
            return array[1];
        }
        return null;
    }

    public AnyType poll() {
        //COMPLETEZ
        if(isEmpty()) {
            return null;
        }

        AnyType elementToReturn = peek();

        if(elementToReturn == null) {
            return null;
        }
        array[1] = array[currentSize--];  // mettre le dernier element au root

        if(min) {
            percolateDownMinHeap(1, currentSize);
        }
        else {
            percolateDownMaxHeap(1, currentSize);
        }

        modifications++;
        return elementToReturn;
    }

    public Iterator<AnyType> iterator() {
        return new HeapIterator();
    }

    private void buildMinHeap() {
        //COMPLETEZ
        for( int i = currentSize / 2; i > 0; i-- ) {  // parent et enfant
            percolateDownMinHeap(i, currentSize);
        }
    }

    private void buildMaxHeap() {
        //COMPLETEZ
        for( int i = currentSize / 2; i > 0; i-- ) {
            percolateDownMaxHeap(i, currentSize);
        }
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public int size() {
        return currentSize;
    }

    public void clear() {
        currentSize = 0;
        modifications = 0;
        array = (AnyType[]) new Comparable[DEFAULT_CAPACITY + 1];
    }

    private static int leftChild(int i, boolean heapIndexing) {
        return (heapIndexing ? 2 * i : 2 * i + 1);
    }

    private void swapReferences(int index1, int index2) {
        swapReferences(array, index1, index2);
    }

    private static <AnyType extends Comparable<? super AnyType>>
    void swapReferences(AnyType[] array, int index1, int index2) {

        AnyType tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    @SuppressWarnings("unchecked")
    private void doubleArray() {
        AnyType[] newArray;

        newArray = (AnyType[]) new Comparable[array.length * 2];
        for (int i = 0; i < array.length; i++)
            newArray[i] = array[i];
        array = newArray;
    }


    /**
     * @param hole Position a percoler
     * @param size Indice max du tableau
     */
    private void percolateDownMinHeap(int hole, int size) {
        percolateDownMinHeap(array, hole, size, true);
    }

    /**
     * @param array        Tableau d'element
     * @param hole         Position a percoler
     * @param size         Indice max du tableau
     * @param heapIndexing True si les elements commencent a l'index 1, false sinon
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void percolateDownMinHeap(AnyType[] array, int hole, int size, boolean heapIndexing) {
        //COMPLETEZ
        int descendant;
        AnyType element;

        for(element = array[hole]; leftChild(hole, heapIndexing) <= size; hole = descendant) {

            descendant  = leftChild(hole, heapIndexing);
            if(descendant != size && array[descendant].compareTo(array[descendant+1]) > 0) {
                descendant++;
            }
            if( element.compareTo(array[descendant]) > 0 ) {
                array[hole] = array[descendant];
            }
            else {
                break;
            }
        }
        array[hole] = element;
    }

    /**
     * @param hole Position a percoler
     * @param size Indice max du tableau
     */
    private void percolateDownMaxHeap(int hole, int size) {
        percolateDownMaxHeap(array, hole, size, true);
    }

    /**
     * @param array        Tableau d'element
     * @param hole         Position a percoler
     * @param size         Indice max du tableau
     * @param heapIndexing True si les elements commencent a l'index 1, false sinon
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void percolateDownMaxHeap(AnyType[] array, int hole, int size, boolean heapIndexing) {
        //COMPLETEZ

        int descendant;
        AnyType tempArray;

        for(tempArray = array[hole]; leftChild(hole, true) <= size; hole = descendant) {

            descendant  = leftChild(hole, true);
            if(descendant != size && array[descendant].compareTo(array[descendant+1]) < 0) {
                descendant++;
            }
            if( tempArray.compareTo(array[descendant]) < 0 ) {
                array[hole] = array[descendant];
            }
            else {
                break;
            }
        }
        array[hole] = tempArray;
    }

    public static <AnyType extends Comparable<? super AnyType>>
    void heapSort(AnyType[] a) {
        //COMPLETEZ
        for(int i = a.length / 2; i >= 0; i--) {    // trier heap
            percolateDownMaxHeap(a, i, a.length - 1, false);
        }

        for(int i = a.length - 1; i > 0; i--)	// trier tableau
        {
            swapReferences(a, 0, i);
            percolateDownMaxHeap(a, 0, i-1, false);
        }
    }

    public static <AnyType extends Comparable<? super AnyType>>
    void heapSortReverse(AnyType[] a) {
        //COMPLETEZ
        for(int i = a.length / 2 - 1; i > 0; i--) {  // trier heap
            percolateDownMinHeap(a, i, a.length - 1, false);
        }

        for(int i = a.length - 1; i > 0; i--)     // trier tableau
        {
            swapReferences(a, 0, i);
            percolateDownMinHeap(a, 0, i-1, false);
        }
    }

    public String nonRecursivePrintFancyTree() {
        String outputString = "";
        //COMPLETEZ
        class HeapElementToSout<AnyType extends Comparable<? super AnyType>> {

            private String display_ = "";
            private int position_;
            private boolean printed_ = false;

            public HeapElementToSout(int position) {
                position_ = position;
                outputDisplay();
            }

            private void outputDisplay() {

                int parentPosition = position_ / 2;

                while (parentPosition > 0)
                {
                    if (parentPosition % 2 != 0)
                    {
                        display_ = "   " + display_;
                    } else {
                        display_ = "|  " + display_;
                    }
                    parentPosition /= 2;
                }
                display_ += "|__" + array[position_];
            }

            private void setPrinted() {
                printed_ = true;
            }

            public String getDisplay() {
                return display_;
            }

            public boolean isPrinted() {
                return printed_;
            }
        }

        HeapElementToSout<AnyType>[] tab = new HeapElementToSout[currentSize + 2];
        for (int i = 1; i < currentSize + 2; i++)
        {
            tab[i] = new HeapElementToSout<>(i);
        }

        int positionToPrint = 1;
        int nbElements = 0;
        while (nbElements < currentSize)
        {
            if (tab[positionToPrint].isPrinted()==false)
            {
                outputString += (tab[positionToPrint].getDisplay() + "\n");
                tab[positionToPrint].setPrinted();
                nbElements++;
            }
            if ((positionToPrint * 2) <= currentSize + 1 && !(tab[positionToPrint * 2].isPrinted()))
            {
                positionToPrint *= 2;
            } else if (positionToPrint + 1 <= currentSize + 1 && !(positionToPrint % 2 == 1))
            {
                positionToPrint++;
            } else
                {
                positionToPrint = positionToPrint / 2;
            }
        }
        return outputString;
    }

    public String printFancyTree() {
        return printFancyTree(1, "");
    }

    private String printFancyTree(int index, String prefix) {
        String outputString = "";

        outputString = prefix + "|__";

        if (index <= currentSize) {
            boolean isLeaf = index > currentSize / 2;

            outputString += array[index] + "\n";

            String _prefix = prefix;

            if (index % 2 == 0) {
                _prefix += "|  "; // un | et trois espace
            } else {
                _prefix += "   "; // quatre espaces
            }

            if (!isLeaf) {
                outputString += printFancyTree(2 * index, _prefix);
                outputString += printFancyTree(2 * index + 1, _prefix);
            } else {
                outputString += "null\n";
            }


        }
        return outputString;
    }

    private class HeapIterator implements Iterator {

        private int position_ = 1;
        private int numMod_ = modifications;

        public boolean hasNext() {
            if ((array[0] != null && position_ < (currentSize - 1)) || (array[0] == null && position_ < currentSize)) {
                return true;
            }
            return false;
        }

        public Object next() throws NoSuchElementException, ConcurrentModificationException,
                UnsupportedOperationException
        {
            if(modifications != numMod_) {
                throw new ConcurrentModificationException();
            }

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return array[position_++];
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }
}
