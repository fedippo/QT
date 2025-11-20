package data;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

public class DiscreteAttribute extends Attribute implements Iterable<String>{
    private TreeSet<String> values;

    public DiscreteAttribute(String name, int index, String values[]) {
        super(name, index);
        this.values = new TreeSet<>(Arrays.asList(values));
    }

    public int getNumberOfDistinctValues() {
        return values.size();
    }

    @Override
    public Iterator<String> iterator(){
        return values.iterator();
    }

}
