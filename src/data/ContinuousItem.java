package data;

public class ContinuousItem extends Item{

    ContinuousItem(Attribute attribute, Double value){
        super(attribute, value);
    }

    @Override
    double distance(Object a){
        double thisValue = (double) this.getValue();
        double otherValue = (double) ((ContinuousItem)a).getValue();

        ContinuousAttribute attribute = (ContinuousAttribute) this.getAttribute();

        double thisScaledValue = attribute.getScaledValue(thisValue);
        double otherScaledValue = attribute.getScaledValue(otherValue);

        return Math.abs(thisScaledValue-otherScaledValue);
    }
}
