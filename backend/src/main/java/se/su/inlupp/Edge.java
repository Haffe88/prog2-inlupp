package se.su.inlupp;

public interface Edge<T> {
	
	T getDestination();
	
	int getWeight();
	
	void setWeight(int weight);
	//Om vikten är negativ skall undantaget IllegalArgumentException genereras.
	
	String getName();
	
	public String toString();
	//ska ha en meningsfull toString-metod
}

//Kant i grafen