package ypp170130;

/**
 *     Team No: 33
 *     @author Vineet Vats: vxv180008
 *     @author Yash Pradhan: ypp170130
 *     Short Project 7: Implementing Robin Hood hashing algorithm
 */

public class RobinHood <K, V>{

    private int CAPACITY = 1024; // initial capacity of hash table(2^10)
    private int size;
    private Entry[] hashTable;
    private float loadFactor = (float) 0.75;
    private int maxDisplacement; // how far to look for element

    // to distinguish between empty and deleted
    enum Status{
        FILLED, EMPTY, DELETED
    }

    public int size(){
        return size;
    }

    /**
     * Entry class
     * @param <K> key
     * @param <V> value
     */
    class Entry<K, V>{
        K key;
        V value;
        Status status;
        Entry(K key, V value){
            this.key = key;
            this.value = value;
            this.status = Status.FILLED;
        }
    }

    /**
     * constructor
     */
    RobinHood(){
        size = 0;
        hashTable = new Entry[CAPACITY]; 
        maxDisplacement = 0;
    }

    /**
     * utility function find()
     * @param key
     * @return
     */
    private int find(K key){
        int k = 0, xspot;
        int i = computeHash(key.hashCode());

        while(true){
            if(k>maxDisplacement){
                return i;
            }
            if(hashTable[i] == null){
                return i;
            }
            if(hashTable[i].status == Status.DELETED)
                break;
            else if(hashTable[i].key.equals(key) || hashTable[i].status == Status.EMPTY)
                return i;
            else{
                k++;
                i = (i+1)%CAPACITY;
            }
        }

        xspot = i;

        while(true){
            k++;
            i = (i+1)%CAPACITY;
            if(k>maxDisplacement){
                return xspot;
            }
            if(hashTable[i].status == Status.DELETED || hashTable[i].key.equals(key))
                return i;
            if(hashTable[i].status == Status.EMPTY)
                return xspot;
        }
    }

    /**
     *
     * @param hashCode
     * @return hashcode for provided argument
     */
    private int computeHash(int hashCode){
        hashCode = hashCode ^ (hashCode>>>20)^(hashCode>>>12);
        return (hashCode^(hashCode>>>7)^(hashCode>>>4))&(CAPACITY-1);
    }

    /**
     *
     * @param key for lookup
     * @return true if key in table, false otherwise
     */
    public boolean contains(K key){ // This function will use find() and tell us whether a value is present in the hash table or not.
        int i = find(key);
        if(hashTable[i] == null)
            return false;
        if(hashTable[i].key.equals(key))
            return true;
        return false;
    }

    /**
     * called by add while surpassing the load factor
     */
    private void rehash(){
        Entry[] elements = new Entry[size];
        for(int i = 0, j = 0; i<CAPACITY; i++){
            if(hashTable[i] != null && hashTable[i].status == Status.FILLED){
                elements[j++] = hashTable[i];
            }
        }

        //double the capacity
        CAPACITY = 2*CAPACITY;
        size = 0;
        maxDisplacement = 0;
        hashTable = new Entry[CAPACITY];

        for(int i = 0; i<elements.length; i++){
            add((K)elements[i].key, (V)elements[i].value);
        }
    }

    /**
     * Adds key, value to the table if key not exists,
     * rejects duplicates for key, value
     * if value is different, updates it
     * @param key
     * @param value
     * @return true on new insertion/updation, false on duplicate
     */
    public boolean add(K key, V value){ // adding a value to the hash table

        if(size/(float)CAPACITY > loadFactor){
            rehash();
        }

        if(contains(key)){
            if(get(key).equals(value))
                return false;
            else{
                int index = find(key);
                hashTable[index].value = value;
                return true;
            }
        }

        int loc = computeHash(key.hashCode());
        int d = 0;

        while(true){
            if(hashTable[loc]==null || hashTable[loc].status == Status.EMPTY || hashTable[loc].status == Status.DELETED) {
                hashTable[loc] = new Entry(key, value);
                size++;
                maxDisplacement = Math.max(d, maxDisplacement);
                return true;
            }
            else if(displacement((K)hashTable[loc].key, loc) >= d){
                d = d+1;
                loc = (loc + 1)%CAPACITY;
            }
            else{
                K tKey = (K) hashTable[loc].key;
                V tValue = (V)hashTable[loc].value;
                hashTable[loc].key = key;
                hashTable[loc].value = value;
                key = tKey;
                value = tValue;
                loc = (loc+1)%CAPACITY;
                d = displacement(key, loc);
            }
        }
    }

    /**
     * method to extract value corresonding to key from table
     * @param key
     * @return value if key exists, null otherwise
     */

    public V get(K key){
        int index = find(key);
        if (hashTable[index] == null){
            return null;
        }
        if(hashTable[index].status==Status.FILLED && hashTable[index].key.equals(key))
            return (V)hashTable[index].value;
        return null;
    }

    /**
     * To keep a record of the maximum displacement in the probing sequence.
     * @param key
     * @param loc
     * @return displacement
     */
    private int displacement(K key, int loc){
        int hashCode = computeHash(key.hashCode());
        return loc >= hashCode ? loc - hashCode : CAPACITY + loc - hashCode;
    }

    /**
     * To remove key, value from table
     * also marks status deleted
     * @param key
     * @return value corresponding to key, null if key not exists
     */
    public V remove(K key){
        int i = find(key);

        if(hashTable[i] == null || hashTable[i].status == Status.DELETED)
            return null;

        if(hashTable[i].key.equals(key)){
            V result = (V)hashTable[i].value;
            hashTable[i].status = Status.DELETED;
            size--;
            return result;
        }
        return null;
    }

    /**
     * Computes number of distinct elements as passed in array
     * @param arr
     * @param <T>
     * @return number of distinct elements
     */
    static<T> int distinctElements(T[ ] arr) {
        RobinHood<T,T> myMap = new RobinHood<>();
        for(T element: arr){
            myMap.add(element, element);
        }
        return myMap.size;
    }
}
