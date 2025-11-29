package data;

import java.util.LinkedList;
import java.util.List;

public class Data {
    private Object data[][];
    private int numberOfExamples;
    private List<Attribute> attributeSet = new LinkedList<>();

    public Data(){}

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
