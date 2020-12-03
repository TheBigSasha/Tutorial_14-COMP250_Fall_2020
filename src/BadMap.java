import java.util.*;

/**
 * A map that allows lookup of a value based on a key
 * @param <K>   The thing to lookup with
 * @param <V>   The thing to be looked up
 */
public class BadMap<K, V> {
    /**
     * How many buckets there are by default
     */
    private static final int DEFAULT_SIZE = 10;
    /**
     * The ratio of groups to size of group.
     *
     * So if the size vs the number of buckets exceeds this number, we have to increase the number of buckets and change the hash
     * algorithm to give values from 0 to the new number of buckets
     */
    private static final double LOAD_FACTOR = 0.7;

    private ArrayList<LinkedList<BadPair<K,V>>> buckets;
    private int numberOfBuckets;
    private  int size = 0;

    /**
     * Creates a BadMap which is like a HashMap but not as good
     */
    public BadMap() {
        buckets = new ArrayList<>();
        numberOfBuckets = DEFAULT_SIZE;
        for(int i = 0; i < numberOfBuckets; i++){
            buckets.add(new LinkedList<BadPair<K,V>>());
        }
    }

    /**
     * Test method
     */
    public static void main(String[] args) {
        BadMap<String, Integer> ageOfCeleb = new BadMap<>();
        ageOfCeleb.put("Justin Bieber", 44);
        ageOfCeleb.put("Ariana Vente", 28);
        Integer[] ages = new Integer[]{44, 28};

        if(ageOfCeleb.containsKey("Justin Bieber")){
            System.out.println(ageOfCeleb.get("Justin Bieber"));
        }
        if(ageOfCeleb.containsKey("Jack Hughman")){
            System.out.println(ageOfCeleb.get("Jack Hughman"));
        }

        BadMap<String, List<String>> moviesOfActor = new BadMap<>();
        ArrayList<String> moviesOfJackHughman = new ArrayList<>();
        moviesOfJackHughman.add("Les pas Miserables");
        moviesOfJackHughman.add("The worst showman");
        moviesOfActor.put("Jack Hughman", moviesOfJackHughman);

        System.out.println(moviesOfActor.get("Jack Hughman"));
    }

    /**
     * Gets how many things are in the map
     * @return number of stuff in map
     */
    public int size() {
        return size;
    }

    /**
     * Tells you if the map is empty
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Adds a pair of key and value to the map
     * @param key   key used for lookup
     * @param value thing to be looked up
     */
    public void put(K key, V value) {
        int index = hashValue(key);
        LinkedList<BadPair<K,V>> list = buckets.get(index);

        //if the key is the same, we replace the thing with the same key
        //Note that multiple keys can have the same hash value
        // just as multiple names can start with A
        //So its not a given that we have or don't have something to do with this key
        boolean didReplace = false;
        for(BadPair<K, V> pair : list){
            if(pair.getKey().equals(key)){
                pair.setValue(value);
                didReplace = true;
            }
        }if(!didReplace){
            list.add(new BadPair<>(key, value));
            size++;
        }

        if((double) size / numberOfBuckets > LOAD_FACTOR) rehash();
    }

    /**
     * Resizes the size of the ArrayList that is the basis of the map
     */
    private void rehash() {
        ArrayList<LinkedList<BadPair<K,V>>> old = buckets;
        buckets = new ArrayList<>();
        numberOfBuckets *=2;
        size = 0;
        for(int i = 0; i < numberOfBuckets; i++){
            buckets.add(new LinkedList<>());
        }

        for(LinkedList<BadPair<K,V>> list : old){
            for(BadPair<K,V> pair : list){
                put(pair.getKey(), pair.getValue());
            }
        }
    }

    /**
     * Removes the element corresponding to the given key
     * @param key   thing to remove
     * @return      value of thing that was removed
     */
    public V remove(K key) {
        int indexAtBucket = hashValue(key);
        LinkedList<BadPair<K,V>> list = buckets.get(indexAtBucket);

        //Find the thing we oughtta remove
        BadPair<K,V> toRemove = null;
        for(BadPair<K,V> pair : list){
            if(pair.getKey().equals(key)) toRemove = pair;
        }

        //If found, remove it
        if(toRemove != null){
            list.remove(toRemove);
            size--;
            return toRemove.getValue();
        }

        //If not found, return null
        return null;
    }

    /**O(1) sorta, except that it is actually bounded by the length of the list which is bounded by the load factor
     *
     * Retrieves the item corresponding to the given key
     * @param key   the key of the thing to get
     * @return      the value corresponding to the key
     */
    public V get(K key) {
        int indexAtBucket = hashValue(key);
        LinkedList<BadPair<K,V>> list = buckets.get(indexAtBucket);

        //Find the thing we oughtta remove
        for(BadPair<K,V> pair : list){
            if(pair.getKey().equals(key)) return pair.getValue();
        }

        return null;

    }

    /**
     * O(1)
     * Gets the index for use in the ArrayList for a given key
     * @param key   Thing to have its index acquired
     * @return      Index of thing
     */
    private int hashValue(K key) {
        int hashValue = Math.abs(key.hashCode());   //This genrates a unique number for each object we put it through
        return hashValue % numberOfBuckets; //This ensures that the output is less than the maximum index of our array
    }

    /**
     * checks presence of items in keys
     * @param key   the key to check
     * @return      true if this contains a value corresponding to key
     */
    public boolean containsKey(K key) {
        boolean contains = false;
        int indexAtBucket = hashValue(key);
        LinkedList<BadPair<K,V>> list = buckets.get(indexAtBucket);

        //Find the thing we oughtta remove
        for(BadPair<K,V> pair : list){
            if (pair.getKey().equals(key)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    /**
     * Gets all of the keys in this map.
     * @return a set of the keys in this map
     */
    public Set<K> keys() {
        Set<K> output = new HashSet<>();
        for(LinkedList<BadPair<K,V>> list : buckets){
            for(BadPair<K,V> pair : list){
                output.add(pair.getKey());
            }
        }
        return output;
    }

    /**
     * Gets the values in this map
     * @return a set of values in this map
     */
    public Set<V> values() {
        Set<V> output = new HashSet<>();
        for(LinkedList<BadPair<K,V>> list : buckets){
            for(BadPair<K,V> pair : list){
                output.add(pair.getValue());
            }
        }
        return output;
    }


    /**
     * A simple class, like a Tuple in Python sort of
     * @param <K> a key
     * @param <V> a value
     */
    private static class BadPair<K, V> {
        private K key;
        private V value;

        public BadPair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public void set(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}