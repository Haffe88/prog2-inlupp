package se.su.inlupp;

import java.util.*;

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
    throw new UnsupportedOperationException("Unimplemented method 'connect'");
  }

  @Override
  public void disconnect(T node1, T node2) {
    throw new UnsupportedOperationException("Unimplemented method 'disconnect'");
  }

  @Override
  public void setConnectionWeight(T node1, T node2, int weight) {
    throw new UnsupportedOperationException("Unimplemented method 'setConnectionWeight'");
  }

  @Override
  public Set<T> getNodes() {
    throw new UnsupportedOperationException("Unimplemented method 'getNodes'");
  }

  @Override
  public Collection<Edge<T>> getEdgesFrom(T node) {
    throw new UnsupportedOperationException("Unimplemented method 'getEdgesFrom'");
  }

  @Override
  public Edge<T> getEdgeBetween(T node1, T node2) {
    throw new UnsupportedOperationException("Unimplemented method 'getEdgeBetween'");
  }

  @Override
  public Iterator<T> iterator() {
    throw new UnsupportedOperationException("Unimplemented method 'iterator'");
  }
}

