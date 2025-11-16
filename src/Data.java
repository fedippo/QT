public class Data {
    private Object data[][];
    private int numberOfExamples;
    private Attribute attributeSet[];

    Data(){

        data = new Object [][] {
                {"sunny",    "hot",  "high",   "weak",   "no"},
                {"sunny",    "hot",  "high",   "strong", "no"},
                {"overcast", "hot",  "high",   "weak",   "yes"},
                {"rain",     "mild", "high",   "weak",   "yes"},
                {"rain",     "cool", "normal", "weak",   "yes"},
                {"rain",     "cool", "normal", "strong", "no"},
                {"overcast", "cool", "normal", "strong", "yes"},
                {"sunny",    "mild", "high",   "weak",   "no"},
                {"sunny",    "cool", "normal", "weak",   "yes"},
                {"rain",     "mild", "normal", "weak",   "yes"},
                {"sunny",    "mild", "normal", "strong", "yes"},
                {"overcast", "mild", "high",   "strong", "yes"},
                {"overcast", "hot",  "normal", "weak",   "yes"},
                {"rain",     "mild", "high",   "strong", "no"}
        };

        numberOfExamples=14;

        attributeSet = new Attribute[5];

        String outLookValues[]=new String[3];
        outLookValues[0]="overcast";
        outLookValues[1]="rain";
        outLookValues[2]="sunny";
        attributeSet[0] = new DiscreteAttribute("Outlook",0, outLookValues);

        String TemperatureValues[]=new String[3];
        TemperatureValues[0]="hot";
        TemperatureValues[1]="mild";
        TemperatureValues[2]="cool";
        attributeSet[1] = new DiscreteAttribute("Temperature",1, TemperatureValues);

        String HumidityValues[]=new String[2];
        HumidityValues[0]="high";
        HumidityValues[1]="normal";
        attributeSet[2] = new DiscreteAttribute("Humidity",2, HumidityValues);

        String WindValues[]=new String[2];
        WindValues[0]="weak";
        WindValues[1]="strong";
        attributeSet[3] = new DiscreteAttribute("Humidity",3, WindValues);

        String PlayTennisValues[]=new String[2];
        PlayTennisValues[0]="no";
        PlayTennisValues[1]="yes";
        attributeSet[4] = new DiscreteAttribute("PlayTennis",4, PlayTennisValues);

    }

    int getNumberOfExamples(){
        return numberOfExamples;
    }

    int getNumberOfAttributes(){
        return attributeSet.length;
    }

    Attribute[] getAttributeSchema(){
        return attributeSet;
    }

    Object getValue(int exampleIndex, int attributeIndex){
        return data[exampleIndex][attributeIndex];
    }

    Tuple getItemSet(int index){
        Tuple tuple=new Tuple(attributeSet.length);
        for(int i=0;i<attributeSet.length;i++)
            tuple.add(new DiscreteItem((DiscreteAttribute) attributeSet[i], (String)data[index][i]),i);
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
