package se.su.inlupp;

import java.util.ArrayList;
import java.util.List;

public class ListPath<T> implements Path<T> {

private final T start;
private final List<Edge<T>> edges;

public ListPath(T start, List<Edge<T>>edges){
    this.start = start;
     this.edges = new ArrayList<>(edges);

    }

}


