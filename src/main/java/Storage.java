import java.sql.Array;
import java.util.ArrayList;

/**
 * This classes emulates the storage.
 */
public class Storage {

    private ArrayList<Integer> storage = new ArrayList<>();
    /**
     * Returns the element e from storage
     * @param e Element to retrieve from storage
     * @return element
     */
    int get(int e) {
        if(storage.contains(e)){
            return e;
        } else {
            storage.add(e);
            return e;
        }

    }

}
