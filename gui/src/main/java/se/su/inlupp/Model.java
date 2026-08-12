package se.su.inlupp;

import java.util.Collection;
import java.util.Set;
import se.su.inlupp.Graph;
import se.su.inlupp.ListGraph;
import se.su.inlupp.Path;
import se.su.inlupp.PathFinder;

public class Model {

    private Graph<Location> graph;

    public Model(){
        graph = new ListGraph<>();
    }

    public void addLocation(Location location){
        graph.add(location);
    }

    public void removeLocation(Location location){
        graph.remove(location);
    }

    public void connectLocations(Location from, Location to, String name, int weight){
        graph.connect(from, to, name, weight);
    }

    public void disconnectLocations(Location a, Location b) {
        graph.disconnect(a, b);
    }
    //kolla om denna faktiskt behövs då den inte används någonstans än

    //Ovan är ListGraph-metoder för att skapa graf, ta bort och lägga till i grafen

    public Collection<Edge<Location>> getEdgesFrom(Location location) {
        return graph.getEdgesFrom(location);
    }

    public Path<Location> findPath(Location from, Location to, String choice){
        PathFinder<Location> pathFinder;

        if (choice.equals("DFS")) {
            pathFinder = new DFSPathFinder<>();

        } else {
            pathFinder = new BFSPathFinder<>();
        }

        return pathFinder.findPath(graph, from, to);

    }
    //Kör metoden findPath() via att skicka med skicka med strängen choice för att skapa ett PathFinder-objekt (DFS/BFS), delegering
    //flyttade kod från gui för att skapa objektet- för att vi ska separera vyn från modellen.

    public Set<Location> getLocations() {
        return graph.getNodes();
    }
    //Hämtar noder från ListGraph



}


//ska innehålla programmets data och logik (gui ska bara hantera gränssnitt och javafx-komponenter)
//frågan är ifall man då måste ha en nod-klass i backend också? eftersom vi vill skilja vyn (cirkel och label) från själva datan (namn, koordinater)
//eftersom modelklassen behöver någon typ av nod
