package se.su.inlupp;

import java.util.*;

public class DFSPathFinder<T> implements PathFinder<T> {

  @Override
  public Path<T> findPath(Graph<T> graph, T from, T to) {
	  
	  Map <T, T> connections = new HashMap<>();
	  connect(from, null, connections);
	  LinkedList<Edge> path = new LinkedList<>();
	  
	  T current = to;
	  
	  while(current != null && !current.equals(from) {
		  T next = connections.get(current)
		  Edge edge = getEdgeBetween(current, next);
		  path.addFirst(edge);
		  current = next;
	  
	  }
	  
	  return null;
	  
	  
	  
   // throw new UnsupportedOperationException("Unimplemented method 'findPath'");
  }
}

//Påbörjade koden här men det krävs en path-klass som implementerar path-interfacet för att man ska kunna returnera här

//Djupetförst-sökning för att hitta väg mellan två noder
//skall returnera en Path<T> som representerar den hittade vägen, eller null om ingen väg finns.