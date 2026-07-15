package se.su.inlupp;

public interface Edge<T> {
	
	T getDestination();
	
	int getWeight();
	
	void setWeight(int weight);
	//Om vikten är negativ skall undantaget IllegalArgumentException genereras.
	
	String getName();
	
	//Tog bort tom toString-metod eftersom alla som ärver av Object redan har den
}

//Kant i grafen - det ska vara två stycken kanter mellan två noder för att vara dubbelriktad?