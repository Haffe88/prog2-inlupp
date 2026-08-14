package se.su.inlupp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileManager {

    private final Model mapModel;

    public FileManager(Model mapModel) {
        this.mapModel = mapModel;
    }

    public void save (File file, String imagePath) throws IOException {

            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("Locations:");
            bufferedWriter.newLine();

            for (Location l : mapModel.getLocations() ){
                bufferedWriter.write(l.getName() + ","  + l.getLayoutX() + "," + l.getLayoutY()
                );
                bufferedWriter.newLine();
            }
            //Locations sparas (namn, position)
            //Går igenom alla locations i listgraph graph och hämtar namn och position för varje, en för varje rad

            bufferedWriter.write("Path");
            bufferedWriter.newLine();

            for (Location from : mapModel.getLocations()){

                for (Edge<Location> edge : mapModel.getEdgesFrom(from)){

                    Location to = edge.getDestination();

                    if (from.getName().compareTo(to.getName()) <0) {

                        // Den här behövs för att bara en riktning ska skrivas. En av rikningarna kommer alltid
                        // vara större än 0. Java jämför tecken utifrån deras Unicode-värden.

                        bufferedWriter.write(from.getName() + "," + to.getName() + "," + edge.getName() + "," + edge.getWeight()
                        );
                        bufferedWriter.newLine();

                    }
                }
            }

            //Connection-lines sparas(namnet på location from, location to, namn på edgen, vikten/längden)

            if(imagePath != null){
                bufferedWriter.write("Background:");
                bufferedWriter.newLine();
                bufferedWriter.write(imagePath);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
    }

    public List <String> load (File file) throws IOException {

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        List<String> lines = new ArrayList<>();

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();

        return lines;

    }

}
