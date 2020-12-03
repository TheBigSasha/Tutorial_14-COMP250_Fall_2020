import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * A map that allows lookup of a value based on a key
 * @param <K>   The thing to lookup with
 * @param <V>   The thing to be looked up
 */
public class BadMap<K, V> {
    private static final int DEFAULT_SIZE = 5;
    private static final double LOAD_FACTOR = 0.5;

    private ArrayList<LinkedList<BadPair<K, V>>> buckets;
    private int numberOfBuckets;
    private int size = 0;

    /**
     * Creates a BadMap which is like a HashMap but not as good
     */
    public BadMap() {
        buckets = new ArrayList<>(DEFAULT_SIZE);
        numberOfBuckets = DEFAULT_SIZE;

        for (int i = 0; i < numberOfBuckets; i++) {
            buckets.add(new LinkedList<>());                  //This is to fill the array
        }
    }

    /**
     * Test method
     */
    public static void main(String[] args) {
        String[] strings = new String[]{"AIFD","SDGSDG","SDGSDGSD","SDGSDGSDG","SDGS","SDGSDGSDGSD","SDGSDGSDGS", "DS", "sgsadgsdagasdgsd","SDdssdsdsdsd","235123", "2e52352", "hekko", "gedfs", "gergerger", "gergaerygaeghfda", "gerwtq34t61234fa", "314","312513"};
        BadMap<String, Integer> map = new BadMap<>();

        for(String s : strings){
            map.put(s, (int) Math.round(Math.random() * 1000));
        }

        System.out.println("Size is" + map.size() + " Added strings list size is " + strings.length);
        for(String s : strings){
            System.out.print(map.get(s));
            System.out.println( " Now removed " + map.remove(s));
        }

        System.out.println("Size is " + map.size());


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
        //Get list in O(1)
        int bucketIndex = hashValue(key);
        LinkedList<BadPair<K, V>> atIDX = buckets.get(bucketIndex);

        //Replace if present
        boolean didReplace = false;
        for(BadPair<K,V> pair : atIDX){
            if(pair.getKey().equals(key)) {
                pair.setValue(value);
                didReplace = true;
            }
        }

        //Add if not present
        if(!didReplace){
            atIDX.add(new BadPair<>(key, value));
            size++;
        }

        //Resize if needed
        if ((double) size / numberOfBuckets >= LOAD_FACTOR) rehash();

    }

    /**
     * Resizes the size of the ArrayList that is the basis of the map
     */
    private void rehash() {
        ArrayList<LinkedList<BadPair<K, V>>> temp = buckets;
        buckets = new ArrayList<>();
        numberOfBuckets = 2 * numberOfBuckets;
        size = 0;
        for (int i = 0; i < numberOfBuckets; i++)
            buckets.add(new LinkedList<>());
        for(LinkedList<BadPair<K,V>> list : temp) {
            for (BadPair<K, V> item : list) {
                put(item.getKey(), item.getValue());
            }
        }
    }

    /**
     * Removes the element corresponding to the given key
     * @param key   thing to remove
     * @return      value of thing that was removed
     */
    public V remove(K key) {
        //O(1) lookup
        int bucketIndex = hashValue(key);
        LinkedList<BadPair<K, V>> atIDXList = buckets.get(bucketIndex);

        BadPair<K,V> toRemove = null;
        for(BadPair<K,V> pair : atIDXList){
            if(pair.getKey().equals(key)){
                toRemove = pair;
            }
        }

        if(toRemove == null){
            return null;
        }else {
            atIDXList.remove(toRemove);

            size--;
            return toRemove.getValue();
        }
    }

    /**
     * Retrieves the item corresponding to the given key
     * @param key   the key of the thing to get
     * @return      the value corresponding to the key
     */
    public V get(K key) {
        //Hash lookup (O(1))
        int bucketIndex = hashValue(key);
        LinkedList<BadPair<K, V>> atIDXList = buckets.get(bucketIndex);

        //In the case of multiple entries at this hash value, iterate thru (O(number of items at this index))
        for(BadPair<K,V> pair : atIDXList){
            if(pair.getKey().equals(key)){
                return pair.getValue();
            }
        }

        return null;
        //throw new NoSuchElementException("Key " + key.toString() +" does not correspond to a value");
    }

    /**
     * Gets the index for use in the ArrayList for a given key
     * @param key   Thing to have its index acquired
     * @return      Index of thing
     */
    private int hashValue(K key) {
        int hashValue = Math.abs(key.hashCode());
        return hashValue % numberOfBuckets;
    }

    /**
     * checks presence of items in keys
     * @param key   the key to check
     * @return      true if this contains a value corresponding to key
     */
    public boolean containsKey(K key) {
        boolean contains = false;
        for(BadPair<K,V> pair : buckets.get(hashValue(key))){
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
        Set<K> set = new HashSet<>();
        for(LinkedList<BadPair<K,V>> list : buckets){
            for(BadPair<K,V> pair : list){
                set.add(pair.getKey());
            }
        }
        return set;
    }

    /**
     * Gets the values in this map
     * @return a set of values in this map
     */
    public Set<V> values() {
        Set<V> set = new HashSet<>();
        for(LinkedList<BadPair<K,V>> list : buckets){
            for(BadPair<K,V> pair : list){
                set.add(pair.getValue());
            }
        }
        return set;
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