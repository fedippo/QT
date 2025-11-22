package data;

import java.util.LinkedList;
import java.util.List;

public class Data {
    private Object data[][];
    private int numberOfExamples;
    private List<Attribute> attributeSet = new LinkedList<>();

    public Data(){

        data = new Object [][] {
                {"sunny",    30.3,  "high",   "weak",   "no"},
                {"sunny",    30.3,  "high",   "strong", "no"},
                {"overcast", 30.0,  "high",   "weak",   "yes"},
                {"rain",     13.0, "high",   "weak",   "yes"},
                {"rain",      0.0, "normal", "weak",   "yes"},
                {"rain",      0.0, "normal", "strong", "no"},
                {"overcast",  0.1, "normal", "strong", "yes"},
                {"sunny",    13.0, "high",   "weak",   "no"},
                {"sunny",     0.1, "normal", "weak",   "yes"},
                {"rain",     12.0, "normal", "weak",   "yes"},
                {"sunny",    12.5, "normal", "strong", "yes"},
                {"overcast", 12.5, "high",   "strong", "yes"},
                {"overcast",29.21,  "normal", "weak",   "yes"},
                {"rain",     12.5, "high",   "strong", "no"}
        };

        numberOfExamples=14;

        String outLookValues[]=new String[3];
        outLookValues[0]="overcast";
        outLookValues[1]="rain";
        outLookValues[2]="sunny";
        attributeSet.add(new DiscreteAttribute("Outlook",0, outLookValues));

        attributeSet.add(new ContinuousAttribute("Temperature",1, 3.2, 38.7));

        String HumidityValues[]=new String[2];
        HumidityValues[0]="high";
        HumidityValues[1]="normal";
        attributeSet.add(new DiscreteAttribute("Humidity",2, HumidityValues));

        String WindValues[]=new String[2];
        WindValues[0]="weak";
        WindValues[1]="strong";
        attributeSet.add(new DiscreteAttribute("Humidity",3, WindValues));

        String PlayTennisValues[]=new String[2];
        PlayTennisValues[0]="no";
        PlayTennisValues[1]="yes";
        attributeSet.add(new DiscreteAttribute("PlayTennis",4, PlayTennisValues));

    }

    public int getNumberOfExamples(){
        return numberOfExamples;
    }

    public int getNumberOfAttributes(){
        return attributeSet.size();
    }

    public Attribute[] getAttributeSchema(){
        return  attributeSet.toArray(new Attribute[getNumberOfAttributes()]);
    }

    public Object getValue(int exampleIndex, int attributeIndex){
        return data[exampleIndex][attributeIndex];
    }

    public Tuple getItemSet(int index){
        Tuple tuple=new Tuple(attributeSet.size());
        for(int i=0;i<attributeSet.size();i++) {
            Attribute attribute = attributeSet.get(i);

            if (attribute instanceof DiscreteAttribute) {
                tuple.add(new DiscreteItem((DiscreteAttribute) attribute, (String) data[index][i]), i);
            }else if (attribute instanceof ContinuousAttribute) {
                tuple.add(new ContinuousItem((ContinuousAttribute) attribute, (Double) data[index][i]), i);
            }

        }

        return tuple;
    }

    public String toString(){
        String stampa = "Outlook,Temperature,Humidity,Wind,PlayTennis\n";
        for(int i=0;i<getNumberOfExamples();i++){
            stampa+=i+":";
            for(int j=0;j<getNumberOfAttributes();j++){
                stampa+=getValue(i,j)+",";
            }
            stampa+="\n";
        }
        return stampa;
    }

    public static void main(String[] args) {
        System.out.print(new Data().toString());
    }
}
