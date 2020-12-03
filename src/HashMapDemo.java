import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HashMapDemo {
    public static void main(String args[]){
        HashMap<String, Integer> ageOfCeleb = new HashMap<>();
        ageOfCeleb.put("Justin Bieber", 44);
        ageOfCeleb.put("Ariana Vente", 28);
        Integer[] ages = new Integer[]{44, 28};

        if(ageOfCeleb.containsKey("Justin Bieber")){
            System.out.println(ageOfCeleb.get("Justin Bieber"));
        }
        if(ageOfCeleb.containsKey("Jack Hughman")){
            System.out.println(ageOfCeleb.get("Jack Hughman"));
        }

        HashMap<String, List<String>> moviesOfActor = new HashMap<>();
        ArrayList<String> moviesOfJackHughman = new ArrayList<>();
        moviesOfJackHughman.add("Les pas Miserables");
        moviesOfJackHughman.add("The worst showman");
        moviesOfActor.put("Jack Hughman", moviesOfJackHughman);
        moviesOfActor.put("Jack Hughman", null);

        System.out.println(moviesOfActor.get("Jack Hughman"));

        AWFULhashamp ageOfCeleb2 = new AWFULhashamp();
        ageOfCeleb2.put("Justin Bieber", "44");
        ageOfCeleb2.put("Jack Hughman", "14");
        ageOfCeleb2.put("Ariana Vente", "28");
            System.out.println(ageOfCeleb2.get("Justin Bieber"));

            System.out.println(ageOfCeleb2.get("Ariana Vente"));
            System.out.println(Arrays.toString(ageOfCeleb2.buckets));

    }

    static class AWFULhashamp{
         AWFULHashPair[] buckets;

        public AWFULhashamp() {
            buckets = new AWFULHashPair[26];
        }

        public void put(String key, String value){
            buckets[badHashCode(key)] = new AWFULHashPair(key, value);
        }

        public String get(String key){
            return buckets[badHashCode(key)].value;
        }

        /**
         * Encodes our item as an integer. A way I show it here is that it takes the first letter of the string.
         * @param key
         * @return
         */
        private int badHashCode(String key){
            return key.toCharArray()[0] % buckets.length;
        }

        class AWFULHashPair{
            String key;
            String value;

            public AWFULHashPair(String key, String value) {
                this.key = key;
                this.value = value;
            }

            @Override
            public String toString() {
                final StringBuilder sb = new StringBuilder("AWFULHashPair{");
                sb.append("key='").append(key).append('\'');
                sb.append(", value='").append(value).append('\'');
                sb.append('}');
                return sb.toString();
            }
        }
    }
}
