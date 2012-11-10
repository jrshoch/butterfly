package team000;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CollectionUtils {

    public static <E> List<E> subtract(Collection<E> whole, Set<E> part) {
        List<E> output = new ArrayList<E>();
        for(E item : whole) {
            if(!part.contains(item)) {
                output.add(item);
            }
        }
        return output;
    }

}
