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

    /**
     * Creates a BadMap which is like a HashMap but not as good
     */
    public BadMap() {

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
    }

    /**
     * Tells you if the map is empty
     */
    public boolean isEmpty() {
    }

    /**
     * Adds a pair of key and value to the map
     * @param key   key used for lookup
     * @param value thing to be looked up
     */
    public void put(K key, V value) {
    }

    /**
     * Resizes the size of the ArrayList that is the basis of the map
     */
    private void rehash() {
    }

    /**
     * Removes the element corresponding to the given key
     * @param key   thing to remove
     * @return      value of thing that was removed
     */
    public V remove(K key) {
    }

    /**
     * Retrieves the item corresponding to the given key
     * @param key   the key of the thing to get
     * @return      the value corresponding to the key
     */
    public V get(K key) {
    }

    /**
     * Gets the index for use in the ArrayList for a given key
     * @param key   Thing to have its index acquired
     * @return      Index of thing
     */
    private int hashValue(K key) {

    }

    /**
     * checks presence of items in keys
     * @param key   the key to check
     * @return      true if this contains a value corresponding to key
     */
    public boolean containsKey(K key) {

    }

    /**
     * Gets all of the keys in this map.
     * @return a set of the keys in this map
     */
    public Set<K> keys() {

    }

    /**
     * Gets the values in this map
     * @return a set of values in this map
     */
    public Set<V> values() {

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