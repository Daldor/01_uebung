import java.util.ArrayList;

public class LRUCache implements Cache {

    private ArrayList<Integer> cache;
    private int size;
    private Storage storage;
    private int hits, misses;

    public LRUCache(int size) {
        this.storage = new Storage();
        this.cache = new ArrayList(size);
        this.size = size;
    }

    @Override
    public int get(int e) {
        if (cache.size() > 0 && cache.contains(e)) {
            this.hits++;
            ArrayList<Integer> neu = cache;
            cache.set(0, e);
            for (int i = 1; i < cache.size(); i++) {
                cache.set(i, neu.get(--i));
            }
            return e;
        } else {
            this.misses++;
            ArrayList<Integer> neu = cache;
            cache.set(0, e);
            for (int i = 1; i < cache.size(); i++) {
                cache.set(i, neu.get(--i));
            }
            return e;
        }
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
        return new Integer[0];
    }
}
