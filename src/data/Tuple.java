package data;

import java.io.Serializable;
import java.util.Set;

public class Tuple implements Serializable {
    private Item [] tuple;

    public Tuple(int size) {
        tuple = new Item[size];
    }

    public int getLength() {
        return tuple.length;
    }

    public Item get(int i) {
        return tuple[i];
    }

    public void add(Item c, int i){
        tuple[i] = c;
    }

    public double getDistance(Tuple obj) {
        double distance=0.0;
        for(int i=0;i<getLength();i++) {
            distance += this.tuple[i].distance(obj.tuple[i]);
        }
        return distance;
    }

    public double avgDistance(Data data, Set<Integer> clusteredData){
        double p=0.0,sumD=0.0;
        for (Integer id : clusteredData) {
            double d = getDistance(data.getItemSet(id));
            sumD += d;
        }
        if (!clusteredData.isEmpty()) {
            p = sumD / clusteredData.size();
        }
        return p;
    }

}
