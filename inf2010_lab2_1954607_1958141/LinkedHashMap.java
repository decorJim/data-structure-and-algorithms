package tp2;

public class LinkedHashMap<KeyType, DataType> {

    private static final double COMPRESSION_FACTOR = 2; // 50%
    private static final int DEFAULT_CAPACITY = 20;
    private static final int CAPACITY_INCREASE_FACTOR = 2;

    private Node<KeyType, DataType>[] map;
    private int capacity;
    private int size = 0;

    public LinkedHashMap() {
        capacity = DEFAULT_CAPACITY;
        map = new Node[DEFAULT_CAPACITY];
    }

    public LinkedHashMap(int capacity) {
        this.capacity = capacity;
        map = new Node[capacity];
    }

    /**
     * Finds the index attached to a particular key
     * @param key Value used to access to a particular instance of a DataType within map
     * @return The index value attached to a particular key
     */
    private int getIndex(KeyType key){
        int keyHash = key.hashCode() % capacity;
        return keyHash < 0 ? -keyHash : keyHash;
    }

    private boolean shouldRehash() {
        return size * COMPRESSION_FACTOR > capacity;
    }

    /** TODO
     * Increases capacity by CAPACITY_INCREASE_FACTOR (multiplication) and
     * reassigns all contained values within the new map
     */
    private void rehash() {

        capacity *= CAPACITY_INCREASE_FACTOR;
        Node<KeyType, DataType>[] previousMap = map;
        map = new Node[capacity];
        size = 0;
        for (int i = 0; i < previousMap.length; i++) {
            for (Node<KeyType, DataType> it = previousMap[i]; it != null; it = it.next){
                put(it.key, it.data);
            }
        }
    }

    public int size() {
        return size;
    }

    public int getCapacity(){
        return capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /** TODO
     * Finds if map contains a key
     * @param key Key which we want to know if exists within map
     * @return if key is already used in map
     */
    public boolean containsKey(KeyType key) {

        int position = getIndex(key);

        for (Node<KeyType, DataType> it = map[position];  it != null ; it = it.next)
        {
            if (it.key.equals(key))
            {
                return true;
            }
        }
        return false;
    }

    /** TODO
     * Finds the value attached to a key
     * @param key Key which we want to have its value
     * @return DataType instance attached to key (null if not found)
     */
    public DataType get(KeyType key) {
        int i = getIndex(key);
        for (Node<KeyType, DataType> it = map[i]; it != null; it = it.next)
        {
            if (it.key.equals(key)) {
                return it.data;
            }
        }
        return null;
    }

    /** TODO
     * Assigns a value to a key
     * @param key Key which will have its value assigned or reassigned
     * @return Old DataType instance at key (null if none existed)
     */
    public DataType put(KeyType key, DataType value) {
        int i = getIndex(key);
        if (map[i] == null) {
            size++;
            map[i] = new Node<>(key, value);
            if (shouldRehash()){
                rehash();
            }
        } else {
            Node<KeyType, DataType> prevNode = null;
            for (Node<KeyType, DataType> it = map[i]; it != null; it = it.next){
                if (it.key.equals(key)) {
                    DataType previousData=it.data; // sauvegarder lancienne valeur
                    it.data=value;  // asigner la nouvelle valeur a la cle
                    return previousData;
                }
                prevNode = it;
            }
            prevNode.next = new Node<>(key, value);
        }
        return null;

    }

    /** TODO
     * Removes the node attached to a key
     * @param key Key which is contained in the node to remove
     * @return Old DataType instance at key (null if none existed)
     */
    public DataType remove(KeyType key) {

        Node<KeyType,DataType> it=map[getIndex(key)];

        if(it==null) {
            return null;
        }

        if(it.key.equals(key)) {
            DataType previousValue=it.data;
            map[getIndex(key)]=it.next;

            size--;
            return previousValue;
        }

        while(it.next!= null) {
            if(it.next.key.equals(key)) {
                Node<KeyType,DataType> toRemove=new Node(it.next.key,it.next.data); // sauvegarder la cle et l'element
                // a enlever

                it.next=it.next.next; // pointer le pointeur de l'element avant a l'element apres celui a enlever

                size--;
                return toRemove.data;
            }
            it=it.next;
        }
        return null;


    }

    /** TODO
     * Removes all nodes contained within the map
     */
    public void clear() {
        for(int i = 0; i < map.length; i++) {
            map[i] = null;
        }

    }


    static class Node<KeyType, DataType> {
        final KeyType key;
        DataType data;
        Node next; // Pointer to the next node within a Linked List

        Node(KeyType key, DataType data)
        {
            this.key = key;
            this.data = data;
            next = null;
        }
    }
}


