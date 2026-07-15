package se.su.inlupp;

import java.util.List;
import java.util.*;

public interface Path<T> extends Iterable<Edge<T>> {

  T getStart();

  T getEnd();

  int getTotalWeight();

  List<Edge<T>> getEdges();

  List<T> getNodes();

  //Tog bort tom toString-metod eftersom alla som ärver av Object redan har den
  //skall dessutom ha en meningsfull toString()-metod som inkluderar information om start- och slutnod, alla delsträckor och den totala vikten.
}

//Väg genom grafen


//Vi behöver en klass som implementerar gränssnittet