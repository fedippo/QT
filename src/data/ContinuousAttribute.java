package data;

public class ContinuousAttribute extends Attribute {
    private double max;
    private double min;

    public ContinuousAttribute(String name, int index, double max, double min) {
        super(name, index);
        this.max = max;
        this.min = min;
    }

    public double getScaledValue(double v) {
        v=(v-min)/(max-min);
        return v;
    }
}
