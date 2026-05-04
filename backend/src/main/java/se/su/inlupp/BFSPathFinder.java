package se.su.inlupp;

import java.util.*;

public class BFSPathFinder<T> implements PathFinder<T> {
	

  @Override
  public Path<T> findPath(Graph<T> graph, T from, T to) {
	  
	  Map <T, T> connections = new HashMap<>();
	  connections.put(from, null);
	  
	  LinkedList<T> queue = new LinkedList<>();
	  queue.add(from);
	  
	  while(!queue.isEmpty()) {
		  T current = queue.poll();
		  
		  for (Edge edge: graph.getEdgesFrom(current)) {
			  T next = edge.getDestination();
			  
			  if(connections.containsKey(next)) {
				  connections.put(next, current);
				  queue.add(next);
			  }
		  }
	  }
	  
	  return null;
	  
   // throw new UnsupportedOperationException("Unimplemented method 'findPath'");
  }
}

//Påbörjade koden här men det krävs en path-klass som implementerar path-interfacet för att man ska kunna returnera här

//Breddenförst sökning för att hitta väg mellan två noder
//skall returnera en Path<T> som representerar den hittade vägen, eller null om ingen väg finns.