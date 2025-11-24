package mining;

import data.Data;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class ClusterSet implements Iterable<Cluster>, Serializable {
    private Set<Cluster> C = new TreeSet<>();

    public ClusterSet(){}

    public void add(Cluster c){
        C.add(c);
    }

    @Override
    public Iterator<Cluster> iterator() {
        return C.iterator();
    }

    public String toString() {
        String str = "";
        int i=1;
        for (Cluster c : this) {
            if (c != null) {
                str += i + ":" + c.toString() + "\n";
            }
            i++;
        }
        return str;
    }

    public String toString(Data data){
        String str="";
        int i=1;
        for(Cluster c : this){
            if (c != null){
                str += i + ":" + c.toString(data) + "\n";
            }
            i++;
        }
        return str;
    }
}
