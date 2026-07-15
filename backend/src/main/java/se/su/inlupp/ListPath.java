package se.su.inlupp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListPath<T> implements Path<T> {

	private final T start;
	private final List<Edge<T>> edges;

	public ListPath(T start, List<Edge<T>>edges){
		this.start = start;
		this.edges = new ArrayList<>(edges);
	}

	@Override
	public T getStart() {
		return this.start;
	}

	@Override
	public T getEnd() {
		return edges.get(edges.size()-1).getDestination();
		//hämtar sista edge (mha index) i edges-listan, hämtar sen noden (den sista nodens destination)
	}
	
	
	@Override
	public int getTotalWeight() {
		
		int totalWeight = 0;
		
		for (Edge<T> edge: edges) {
			totalWeight += edge.getWeight();
		}
		return totalWeight;
	}
	//ska returnera summa för alla edges vikter
	
	@Override
	public List<Edge<T>> getEdges(){
		return new ArrayList<>(edges);
	}
	
	
	@Override
	public List<T> getNodes(){

		List<T> nodeList = new ArrayList<>();

		nodeList.add(start);
		//Lägger till startnoden, då den inte är med i edges

		for (Edge<T> edge: edges) {
			T node = edge.getDestination();
			nodeList.add(node);
		}
		
		//Lägger till noder från edges i nodeList

		return nodeList;
	}

    @Override
    public Iterator<Edge<T>> iterator() {
        return edges.iterator();
    }
	
	@Override
	public String toString() {

    return "Från" + getStart() +
    ", Till:" + getEnd() +
    ", Edges:" + edges +
    ", Total vikt: " + getTotalWeight();
		
	}

}


