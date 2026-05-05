package se.su.inlupp;

import java.util.*;

public class DFSPathFinder<T> implements PathFinder<T> {

  @Override
  public Path<T> findPath(Graph<T> graph, T from, T to) {
	  
	  Map <T, T> connections = new HashMap<>();
	  connect(graph, from, null, connections);

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
	private void connect(Graph<T> graph, T to, T from, Map<T, T> connections) {
		connections.put(to, from);

		for (Edge<T> edge : graph.getEdgesFrom(to)){
			T destination = edge.getDestination();

			if (!connections.containsKey(destination)) {
				connect(graph,destination, to, connections);
			}
		}
	}
}

//Påbörjade koden här men det krävs en path-klass som implementerar path-interfacet för att man ska kunna returnera här

//Djupetförst-sökning för att hitta väg mellan två noder
//skall returnera en Path<T> som representerar den hittade vägen, eller null om ingen väg finns.