import com.sun.tools.javac.util.List;
import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class LRUCache implements Cache {

    private Integer[] cache;
    private int size;
    private Storage storage;
    private int hits, misses;
    private Integer[] changed;

    public LRUCache(int size) {
        this.storage = new Storage();
        this.cache = new Integer[size];
        this.changed = new Integer[size];
        this.size = size;
    }

    @Override
    public int get(int e) {
        boolean check = true;
        ArrayList<Integer> tempcache = new ArrayList<>();
        tempcache.add(0);
        int count = 0;
        if (cache.length > 0) {
            for (Integer i : cache) {
                if (i != null && e == i) {
                    this.hits++;
                    tempcache.set(0, e);
                    for (int j = 1; j < cache.length; j++) {
                        if (cache[j] != e) {
                            tempcache.add(cache[j]);
                            count++;
                        }
                    }
                    break;
                    //return e;
                } else {
                    tempcache.set(0, e);
                    for (Integer j = 0; j < cache.length; j++){
                        if(cache[j] != null && cache[j] != e && count < cache.length){
                            tempcache.add(cache[j]);
                            count++;
                        }
                    }

                    if(count == cache.length || cache[0] == null){
                        this.misses++;
                    } else {
                        this.hits++;
                    }

                    break;
                }

            }
        }

        while(tempcache.size() > size){
            tempcache.remove(tempcache.size() - 1);
        }
        cache = tempcache.toArray(new Integer[tempcache.size()]);
        return e;
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
        return cache;
    }
}
