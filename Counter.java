package sorting;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Counter<T  extends   Comparable<T> > {
    private final int size;

    private Map<T,Integer> counter ;

    public Counter(List<T> data ) {
        this.counter = new HashMap<>();
        this.size = data.size();

        data.stream().forEach(v ->
            counter.compute(v , (key,value) ->  (value == null) ? 1 : value + 1));
    }






    public int percentage(T key){
        return 100 * counter.get(key) / counter.size() ;
    }

    public void show() {
        List<T> keys = new ArrayList<>(counter.keySet());


    Comparator<T> c = (s,t) -> {
         int  l = counter.get(t);
         int  m = counter.get(s);
         if (m == l){
             return s.compareTo(t);
         }
         else return m < l ? - 1 : 1;
    };

        Collections.sort(keys, c);

        System.out.println("Total numbers: " + size);
        for(T  key : keys ){
            System.out.println(key +": "+counter.get(key) +" time(s), "+percentage(key)+"%");
        }
    }
}
