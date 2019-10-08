import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class RandomCache implements Cache {

    private Integer [] cache;
    private Storage storage;
    private int size;
    private int hits;
    private int misses;
    private Random rnd = new Random();



    public RandomCache(int size) {
        this.storage = new Storage();
        this.size = size;
        this.cache = new Integer[size];
    }

    @Override
    public int get(int e) {
        for(int i = 0; i < this.cache.length; i++){
            if(cache[i] != null && cache[i] == e){
                this.hits++;
                return e;
            }
        }
        this.misses++;
        int neu = this.storage.get(e);
        int i = rnd.nextInt(size);
        this.cache[i] = neu;
        return neu;
    }

    @Override
    public int getHits() {
        return this.hits;
    }

    @Override
    public int getMisses() {
        return this.misses;
    }

    @Override
    public Integer[] getCacheOrder() {
        //Arrays.sort(cache, Collections.reverseOrder());
        return cache;
    }
}
