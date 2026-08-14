package se.su.inlupp;

import java.util.*;

//Grafen skall vara en enkel viktad oriktad graf implementerad med kopplingslistor

public class ListGraph<T> implements Graph<T> {

	private final Map<T, Set<Edge<T>>> list = new HashMap<>();

	public ListGraph(){
	}

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

		if (node1.equals(node2)) {
			throw new IllegalArgumentException();
		}
		// Så att inte en nod går att koppla till sig själv. Det grafiska gränsnsittet tillåter inte det men det är bra att det inte
		// tillåts här heller.

		if (weight < 0) {
			throw new IllegalArgumentException();
		}

		Set<Edge<T>> edgesFrom1 = list.get(node1);
		for (Edge<T> e : edgesFrom1) {
			if (e.getDestination().equals(node2)) {
				throw new IllegalStateException();
			}
		}
		Set<Edge<T>> aEdges = list.get(node1);
		Set<Edge<T>> bEdges = list.get(node2);
		//sparar edges från varje nod

		aEdges.add(new ListEdge<>(name, weight, node2));
		bEdges.add(new ListEdge<>(name, weight, node1));
		//lägger till de nya edges

		// Om någon av noderna saknas i grafen skall undantaget NoSuchElementException genereras.
		//Om vikten är negativ skall undantaget IllegalArgumentException genereras.
		//Om en kant redan finns mellan dessa två noder skall undantaget IllegalStateException genereras.
	}

	//Får felmeddelande där den förväntar sig returnera något?

	@Override
	public void disconnect(T node1, T node2) {

		if (!list.containsKey(node1) || !list.containsKey(node2)){
			throw new NoSuchElementException();
		}

		Edge<T> edge1 = getEdgeBetween(node1,node2); //Den anropar en metod längre ner.
		Edge<T> edge2 = getEdgeBetween(node2,node1); //Behöver göra det på båda nod då kanten finns hos båda.

		if ( edge1 == null || edge2 == null){
			throw new IllegalStateException();
		}

		Set<Edge<T>> aEdges = list.get(node1);
		Set<Edge<T>> bEdges = list.get(node2);

		aEdges.remove(edge1);
		bEdges.remove(edge2);

		// Om någon av noderna saknas i grafen skall undantaget NoSuch-ElementException genereras.
		//Om det inte finns någon kant mellan noderna skall undantaget IllegalStateException genereras
	}

	@Override
	public void setConnectionWeight(T node1, T node2, int weight) {

		Edge<T> edge1 = getEdgeBetween(node1,node2); //Den anropar en metod längre ner.
		Edge<T> edge2 = getEdgeBetween(node2,node1); //Behöver göra det på båda nod då kanten finns hos båda.

		if ( edge1 == null || edge2 == null || !list.containsKey(node1) || !list.containsKey(node2)) {
			throw new NoSuchElementException();

		}

		if (weight < 0) {
			throw new IllegalArgumentException();
		}

		edge1.setWeight(weight);
		edge2.setWeight(weight);

		// Om någon av noderna saknas i grafen eller om ingen kant finns mellan dem skall undantaget NoSuchElementException genereras.
		// Om vikten är negativ skall undantaget IllegalArgumentException genereras

	}

	@Override
	public Set<T> getNodes() {
		return Collections.unmodifiableSet(new HashSet<>(list.keySet()));
	}

	//La till att den inte går att modifiera

	@Override
	public Collection<Edge<T>> getEdgesFrom(T node) {

		if (!list.containsKey(node)){
			throw new NoSuchElementException();
		}
		return new HashSet<>(list.get(node));

		//Om noden saknas i grafen skall undantaget NoSuchElementException genereras
	}

	@Override
	public Edge<T> getEdgeBetween(T node1, T node2) {

		if (!list.containsKey(node1) || !list.containsKey(node2)){
			throw new NoSuchElementException();
		}

		Set<Edge<T>> edgesFrom1 = list.get(node1);
		for (Edge<T> e : edgesFrom1) {
			if (e.getDestination().equals(node2)) {
				return e;
			}
		}
		return null;
		//Om någon av noderna saknas i grafen skall undantaget NoSuchElementException genereras
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		for (T node : getNodes()) {
			sb.append(node).append(": ");

			for (Edge<T> edge : getEdgesFrom(node)) {
				sb.append(edge).append(" ");
			}

			sb.append("\n");
		}

		return sb.toString();

		//returnerar en sträng med information om alla noder och deras kanter
	}

	@Override
	public Iterator<T> iterator() {
		return list.keySet().iterator();
	}

	//keySet ger alla noder, iterator-objekt går igenom dem en och en
	//en iterator() som itererar över grafens noder
}

