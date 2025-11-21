package mining;

import data.Data;
import data.Tuple;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class Cluster implements Iterable<Integer>, Comparable<Cluster>{
	private Tuple centroid;
	private Set<Integer> clusteredData;
	
	/*Cluster(){
		
	}*/

	public Cluster(Tuple centroid){
		this.centroid=centroid;
		clusteredData=new HashSet();
		
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
		return clusteredData.contains(id);
	}
	

	//remove the tuplethat has changed the cluster
	public void removeTuple(int id){
		clusteredData.remove(id);
		
	}
	
	public int  getSize(){
		return clusteredData.size();
	}
	
	
	public Iterator<Integer> iterator(){
		return clusteredData.iterator();
	}

	public int compareTo(Cluster c){
		if(this.getSize()>c.getSize()){
			return 1;
		}else if(this.getSize()<c.getSize()){
			return -1;
		}else {
			return 0;
		}
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

		for(Integer id : clusteredData){
			str+="[";
			for(int j=0;j<data.getNumberOfAttributes();j++)
				str+=data.getValue(id, j)+" ";
			str+="] dist="+getCentroid().getDistance(data.getItemSet(id))+"\n";
		}

		str+="\nAvgDistance="+getCentroid().avgDistance(data, clusteredData);
		return str;
		
	}

}
