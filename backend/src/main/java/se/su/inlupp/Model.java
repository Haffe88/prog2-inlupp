package se.su.inlupp;

import java.io.*;
import java.util.*;
import java.util.Set;
/*
public class Model {

    private Graph<Location> graph;

    public Model(){
        graph = new ListGraph<>();
    }

    public void addLocation(Location l){
        graph.add(l);
    }

    public void removeLocation(Location l){
        graph.remove(l);
    }

    public void connectLocations(Location from, Location to, String name, int weight){
        graph.connect(from, to, name, weight);
    }

    public void disconnectLocations(Location from, Location to) {
        graph.disconnect(from, to);
    }

    public Path<Location> findPath(Location start, Location end, PathFinder<Location> pathFinder){
        return pathFinder.findPath(graph, start, end);

    }

    public Set<Location> getLocations() {
        return graph.getNodes();
    }


    public Graph<Location> getGraph() {
        return graph;
    }

}
*/


//ska innehålla programmets data och logik (gui ska bara hantera gränssnitt och javafx-komponenter)
//frågan är ifall man då måste ha en nod-klass i backend också? eftersom vi vill skilja vyn (cirkel och label) från själva datan (namn, koordinater)
//eftersom modelklassen behöver någon typ av nod
