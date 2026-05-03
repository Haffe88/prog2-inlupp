package se.su.inlupp;

import java.util.*;


//Grafen skall vara en enkel viktad oriktad graf implementerad med kopplingslistor

public class ListGraph<T> implements Graph<T> {

  private final Map<T, Set<Edge<T>>> list = new HashMap<>();

  @Override
  public void add(T node) {
  list.putIfAbsent(node, new HashSet<>());
  }

  @Override
  public void remove(T node) {
    if (!list.containsKey(node)){
      throw new NoSuchElementException();
    }
    for( T n : list.keySet()){
    list.get(n).removeIf(edge -> edge.getDestination().equals(node));
    }
    list.remove(node);
  }

  @Override
  public boolean hasNode(T node) {
    if (!list.containsKey(node)){
      return false;
    }
    return true;

  }

  @Override
  public void connect(T node1, T node2, String name, int weight) {
	  if (!list.containsKey(node1) || !list.containsKey(node2)){
	      throw new NoSuchElementException();
	    }
	  
	  if (weight < 0) {
		throw new IllegalArgumentException
		}
	  
	  if (//hitta kant mellan noder)
	  }
	  
    throw new UnsupportedOperationException("Unimplemented method 'connect'");
    
    // Om någon av noderna saknas i grafen skall undantaget NoSuchElementException genereras.
    //Om vikten är negativ skall undantaget IllegalArgumentException genereras. 
    //Om en kant redan finns mellan dessa två noder skall undantaget IllegalStateException genereras.
  }

  @Override
  public void disconnect(T node1, T node2) {

	  if (!list.containsKey(node1) || !list.containsKey(node2)){
		  throw new NoSuchElementException();
	  }

	  throw new UnsupportedOperationException("Unimplemented method 'disconnect'");

	  // Om någon av noderna saknas i grafen skall undantaget NoSuch-ElementException genereras. 
	  //Om det inte finns någon kant mellan noderna skall undantaget IllegalStateException genereras
  }

  @Override
  public void setConnectionWeight(T node1, T node2, int weight) {
	  
	  if (!list.containsKey(node1) || !list.containsKey(node2) || //om ingen kant finns mellan dem){
		  throw new NoSuchElementException();
	  }
	  
	  if (weight < 0) {
			throw new IllegalArgumentException
			}
	  
	  
    throw new UnsupportedOperationException("Unimplemented method 'setConnectionWeight'");
    
    // Om någon av noderna saknas i grafen eller om ingen kant finns mellan dem skall undantaget NoSuchElementException genereras. 
    //Om vikten är negativ skall undantaget IllegalArgumentException genereras
    
  }

  @Override
  public Set<T> getNodes() {
    throw new UnsupportedOperationException("Unimplemented method 'getNodes'");
  }

  @Override
  public Collection<Edge<T>> getEdgesFrom(T node) {
	  
	  if (!list.containsKey(node)){
		  throw new NoSuchElementException();
	  }
	  
    throw new UnsupportedOperationException("Unimplemented method 'getEdgesFrom'");
    
    //Om noden saknas i grafen skall undantaget NoSuchElementException genereras
  }

  @Override
  public Edge<T> getEdgeBetween(T node1, T node2) {
	  
	  if (!list.containsKey(node1) || !list.containsKey(node2)){
			  throw new NoSuchElementException();
		  }
  
  
    throw new UnsupportedOperationException("Unimplemented method 'getEdgeBetween'");
    
    //Om någon av noderna saknas i grafen skall undantaget NoSuchElementException genereras
  }
  
  public String toString() {
	  //returnerar en sträng med information om alla noder och deras kanter, gärna med radbrytningar för förbättrad läsbarhet.
  }

  @Override
  public Iterator<T> iterator() {
    throw new UnsupportedOperationException("Unimplemented method 'iterator'");
  }
}

