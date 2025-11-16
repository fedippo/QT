package mining;

import data.Data;
import data.Tuple;
import utility.ArraySet;

class Cluster {
	private Tuple centroid;

	private ArraySet clusteredData;
	
	/*Cluster(){
		
	}*/

	public Cluster(Tuple centroid){
		this.centroid=centroid;
		clusteredData=new ArraySet();
		
	}
		
	public Tuple getCentroid(){
		return centroid;
	}
	
	//return true if the tuple is changing cluster
	public boolean addData(int id){
		return clusteredData.add(id);
		
	}
	
	//verifica se una transazione � clusterizzata nell'array corrente
	public boolean contain(int id){
		return clusteredData.get(id);
	}
	

	//remove the tuplethat has changed the cluster
	public void removeTuple(int id){
		clusteredData.delete(id);
		
	}
	
	public int  getSize(){
		return clusteredData.size();
	}
	
	
	public int[] iterator(){
		return clusteredData.toArray();
	}
	
	public String toString(){
		String str="Centroid=(";
		for(int i=0;i<centroid.getLength();i++)
			str+=centroid.get(i);
		str+=")";
		return str;
		
	}

	public String toString(Data data){
		String str="Centroid=(";
		for(int i=0;i<centroid.getLength();i++)
			str+=centroid.get(i)+ " ";
		str+=")\nExamples:\n";
		int array[]=clusteredData.toArray();
		for(int i=0;i<array.length;i++){
			str+="[";
			for(int j=0;j<data.getNumberOfAttributes();j++)
				str+=data.getValue(array[i], j)+" ";
			str+="] dist="+getCentroid().getDistance(data.getItemSet(array[i]))+"\n";
			
		}
		str+="\nAvgDistance="+getCentroid().avgDistance(data, array);
		return str;
		
	}

}
