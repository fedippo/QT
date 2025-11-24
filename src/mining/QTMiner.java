package mining;

import data.Data;
import data.Tuple;

import java.io.*;

public class QTMiner implements Serializable{
    private ClusterSet C;
    private transient double radius;

    public QTMiner(double radius){
        this.radius=radius;
        C=new ClusterSet();
    }

    public QTMiner(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream inFile = new FileInputStream(fileName);
        ObjectInputStream inStream = new ObjectInputStream(inFile);
        this.C = (ClusterSet) inStream.readObject();
        inStream.close();
    }

    public void salva(String fileName) throws FileNotFoundException, IOException{
        FileOutputStream outFile = new FileOutputStream(fileName);
        ObjectOutputStream outStream = new ObjectOutputStream(outFile);
        outStream.writeObject(this.C);
        outStream.close();
    }

    public ClusterSet getC(){
        return C;
    }

    public int compute(Data data)throws ClusteringRadiusException, EmptyDatasetException{
        int numclusters=0;

        if(data.getNumberOfExamples()==0){throw new EmptyDatasetException("The dataset is empty!");}

        boolean isClustered[]=new boolean[data.getNumberOfExamples()];
        for(int i=0;i<isClustered.length;i++)
            isClustered[i]=false;

        int countClustered=0;
        while(countClustered!=data.getNumberOfExamples()){
            //Ricerca cluster più popoloso
            Cluster c=buildCandidateCluster(data, isClustered);
            C.add(c);
            numclusters++;

            //Rimuovo tuple clusterizzate da dataset

            for(int id : c){
                isClustered[id]=true;
            }
            countClustered+=c.getSize();
        }
        if(numclusters==1){throw new ClusteringRadiusException("14 tuples in one cluster!");}
        return numclusters;
    }

    public Cluster buildCandidateCluster(Data data, boolean isClustered[]){
        Cluster bestCluster=null;
        int maxSize = 0;

        for(int i=0;i<data.getNumberOfExamples();i++){
            if(!isClustered[i]){
                Tuple centroid = data.getItemSet(i);
                Cluster candidate = new Cluster(centroid);
                candidate.addData(i);

                for(int j=0;j<data.getNumberOfExamples();j++){
                    if(!isClustered[j]){
                        Tuple currentTuple = data.getItemSet(j);
                        double distance = centroid.getDistance(currentTuple);
                        if(distance<=radius) candidate.addData(j);
                    }
                }
                if(candidate.getSize()>maxSize){
                    maxSize=candidate.getSize();
                    bestCluster=candidate;
                }
            }
        }
        return bestCluster;
    }
}
