package mining;

public class QTMiner {
    private ClusterSet C;
    private double radius;

    public QTMiner(double radius){
        this.radius=radius;
        C=new ClusterSet();
    }

    ClusterSet getC(){
        return C;
    }

    int compute(Data data){
        int numclusters=0;
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

            int clusteredTupleId[]=c.iterator();
            for(int i=0;i<clusteredTupleId.length;i++){
                isClustered[clusteredTupleId[i]]=true;
            }
            countClustered+=c.getSize();
        }
        return numclusters;
    }

    Cluster buildCandidateCluster(Data data, boolean isClustered[]){
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
