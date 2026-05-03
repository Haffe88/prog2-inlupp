package se.su.inlupp;

public class BFSPathFinder<T> implements PathFinder<T> {

  @Override
  public Path<T> findPath(Graph<T> graph, T from, T to) {
    throw new UnsupportedOperationException("Unimplemented method 'findPath'");
  }
}

//Breddenförst sökning för att hitta väg mellan två noder
//skall returnera en Path<T> som representerar den hittade vägen, eller null om ingen väg finns.