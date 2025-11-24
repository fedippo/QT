package data;

import java.io.Serializable;

public class DiscreteItem extends Item implements Serializable {

    public DiscreteItem(DiscreteAttribute attribute, String value) {
        super(attribute, value);
    }

    @Override
    public double distance(Object a) {
        Item otherItem = (Item)a;

        if(this.getValue().equals(otherItem.getValue())) {return 0.0;}
        else {return 1.0;}
    }
}
