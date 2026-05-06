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
		  
		  for (Edge<T> edge: graph.getEdgesFrom(current)) {
			  T next = edge.getDestination();
			  
			  if(!connections.containsKey(next)) {
				  connections.put(next, current);
				  queue.add(next);
			  }
		  }
	  }
	  if (!connections.containsKey(to)){
		  return null;
	  }
	  LinkedList<Edge<T>> path = new LinkedList<>();

	  T current = to;

	  while(current != null && !current.equals(from)) {
		  T next = connections.get(current);
		  Edge<T> edge = graph.getEdgeBetween(next,current);
		  path.addFirst(edge);
		  current = next;
	  }

	  return new ListPath<>(from, path);

  }
}

//Påbörjade koden här men det krävs en path-klass som implementerar path-interfacet för att man ska kunna returnera här

//Breddenförst sökning för att hitta väg mellan två noder
//skall returnera en Path<T> som representerar den hittade vägen, eller null om ingen väg finns.