package se.su.inlupp;

import java.util.Set;

public class ListEdge<T> implements Edge<T> {

    private String name;
    private int weight;
    private T destination;

    public ListEdge(String n, int w, T d){
        name = n;
        weight = w;
        destination = d;
    }

    @Override
    public int getWeight(){
        return weight;
    }

    @Override
    public void setWeight(int weight){

        if(weight < 0){ throw new IllegalArgumentException("The weight is too low");
        }
        this.weight = weight;
    }

    @Override
    public T getDestination(){
    return destination;

    }

    @Override
    public String getName(){
        return name;
    }
    
    @Override
    public String toString(){
    	return "..";
    	
    	//ska ha en meningsfull toString-metod

    }
    
    

}
