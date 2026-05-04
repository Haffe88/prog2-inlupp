package se.su.inlupp;


public interface PathFinder<T> {

  Path<T> findPath(Graph<T> graph, T from, T to);
  // definierar kontraktet för en sökalgoritm som hittaren väg mellan två noder i en graf
  //söker efter en väg från from till to i den angivna grafen. Returnerar en Path<T> som representerar vägen, eller null om ingen väg finns.
}

