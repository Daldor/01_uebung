import org.junit.Assert;
import org.junit.Test;

/**
 * Test cases for LRU cache.
 */
public class LRUCacheTest {

    @Test
    public void testLRUCacheSimple() {
        Cache c = new LRUCache(3);

        // get 2 which was not in the cache (= miss)
        int v = c.get(2);
        Assert.assertEquals(2, v);
        Assert.assertEquals(1, c.getMisses());
        Assert.assertEquals(new Integer[]{2}, c.getCacheOrder());

        // get 3 which was not in the cache (= miss)
        v = c.get(3);
        Assert.assertEquals(3, v);
        Assert.assertEquals(2, c.getMisses());
        Assert.assertEquals(new Integer[]{3,2}, c.getCacheOrder());

        // get 2 again  ... now it should be a hit
        v = c.get(2);
        Assert.assertEquals(2, v);
        Assert.assertEquals(2, c.getMisses());
        Assert.assertEquals(1, c.getHits());
        Assert.assertEquals(new Integer[]{2,3}, c.getCacheOrder());

    }

    @Test
    public void testLRUCacheComplex() {
        Cache c = new LRUCache(4);
        int v = c.get(0);

        Assert.assertEquals(0, v);
        Assert.assertEquals(1, c.getMisses());
        Assert.assertEquals(0, c.getHits());
        Assert.assertEquals(new Integer[]{0}, c.getCacheOrder());

        v = c.get(1);
        Assert.assertEquals(1, v);
//        Assert.assertEquals(2, c.getMisses());
//        Assert.assertEquals(0, c.getHits());
        Assert.assertEquals(new Integer[]{1, 0}, c.getCacheOrder());

        v = c.get(2);
        Assert.assertEquals(2, v);
        Assert.assertEquals(3, c.getMisses());
        Assert.assertEquals(0, c.getHits());
        Assert.assertEquals(new Integer[]{2, 1, 0}, c.getCacheOrder());

        v = c.get(4);
        Assert.assertEquals(4, v);
        Assert.assertEquals(4, c.getMisses());
        Assert.assertEquals(0, c.getHits());
        Assert.assertEquals(new Integer[]{4, 2, 1, 0}, c.getCacheOrder());

        v = c.get(4);
        Assert.assertEquals(4, v);
        Assert.assertEquals(4, c.getMisses());
        Assert.assertEquals(1, c.getHits());
        Assert.assertEquals(new Integer[]{4, 2, 1, 0}, c.getCacheOrder());

        v = c.get(1);
        Assert.assertEquals(1, v);
        Assert.assertEquals(4, c.getMisses());
        Assert.assertEquals(2, c.getHits());
        Assert.assertEquals(new Integer[]{1, 4, 2, 0}, c.getCacheOrder());

        v = c.get(5);
        Assert.assertEquals(5, v);
        Assert.assertEquals(5, c.getMisses());
        Assert.assertEquals(2, c.getHits());
        Assert.assertEquals(new Integer[]{5, 1, 4, 2}, c.getCacheOrder());

        v = c.get(4);
        Assert.assertEquals(4, v);
        Assert.assertEquals(5, c.getMisses());
        Assert.assertEquals(3, c.getHits());
        Assert.assertEquals(new Integer[]{4, 5, 1, 2}, c.getCacheOrder());

        v = c.get(7);
        Assert.assertEquals(7, v);
        Assert.assertEquals(6, c.getMisses());
        Assert.assertEquals(3, c.getHits());
        Assert.assertEquals(new Integer[]{7, 4, 5, 1}, c.getCacheOrder());
    }

}