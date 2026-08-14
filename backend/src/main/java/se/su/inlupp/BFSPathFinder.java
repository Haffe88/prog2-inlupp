package se.su.inlupp;

import java.util.*;

public class BFSPathFinder<T> implements PathFinder<T> {

	public BFSPathFinder(){
	}


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

	//Kod från föreläsning 10
}


//skall returnera en Path<T> som representerar den hittade vägen, eller null om ingen väg finns.