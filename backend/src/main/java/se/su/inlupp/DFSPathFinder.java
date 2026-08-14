package se.su.inlupp;

import java.util.*;

public class DFSPathFinder<T> implements PathFinder<T> {

	public DFSPathFinder(){
	}

	@Override
	public Path<T> findPath(Graph<T> graph, T from, T to) {

		Map <T, T> connections = new HashMap<>();
		//connections visar varje nod och vilken nod vi kom ifrån (to, from)
		connect(graph, from, null, connections);

		if (!connections.containsKey(to)){
			return null;
		}
		//om to inte finns (ingen väg finns) - returnera null


		LinkedList<Edge<T>> path = new LinkedList<>();

		T current = to;

		//Går bakåt från målet (noden to) till starten (noden from)

		while(current != null && !current.equals(from)) {
			T next = connections.get(current);
			//hämtar nästa nod
			Edge<T> edge = graph.getEdgeBetween(next,current);
			//hämtar edge mellan noden next och current
			path.addFirst(edge);
			//lägger till edgen först i linked-listed path
			current = next;

		}
		//fortsätter tills når noden from

		return new ListPath<>(from, path);
		//returnera en Path om vägen finns, slutnoden finns implicit i sista edgen i path
	}

	//Kod från föreläsning 10

	private void connect(Graph<T> graph, T to, T from, Map<T, T> connections) {
		connections.put(to, from);
		//lägger in to och from noder i connections hashmap

		for (Edge<T> edge : graph.getEdgesFrom(to)){
			T destination = edge.getDestination();
			//går igenom edges i to-nod och kollar vilka noder de har (kolla grannar)

			if (!connections.containsKey(destination)) {
				connect(graph,destination, to, connections);
			}
			//om noden inte är besökt - gå dit rekursivt
		}
	}
}


//skall returnera en Path<T> som representerar den hittade vägen, eller null om ingen väg finns.