package br.nom.martinelli.ricardo;

import static java.util.stream.Collectors.groupingBy;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DataProcessingWithStreams {

    private static String DATASET_REMOTE_PATH = "https://rimolive.fedorapeople.org/us-elections.csv";
    private static String DATASET_LOCAL_PATH = System.getProperty("user.dir") + "/data/us-elections.csv";

    private static Function<String, UsElectionsItem> mapToItem = (item) -> {
        String[] p = item.split(",");
        
        return new UsElectionsItem(p);
    };

    public void processData() throws Exception {
        // Data Collection
        downloadDataset();

        // Preprocessing
        preProcess();
    }

    public void downloadDataset() throws Exception {
        // Check if file exists
        if(Files.exists(Paths.get(DATASET_LOCAL_PATH), LinkOption.NOFOLLOW_LINKS)) {
            return;
        }
        Files.copy(
            new URL(DATASET_REMOTE_PATH).openStream(),
            Paths.get(DATASET_LOCAL_PATH));
    }

    public void preProcess() throws Exception {
        Map<String, Integer> data = Files.lines(Paths.get(DATASET_LOCAL_PATH))
        //List<UsElectionsItem> data = Files.lines(Paths.get(DATASET_LOCAL_PATH))
            .skip(1)
            .map(mapToItem)
            //.filter(s -> s.party.equals("dem"))
            //.collect(toList());
            .collect(groupingBy(s -> s.party, Collectors.summingInt(s -> s.totalVotes2008)));
            //.collect(groupingBy(s -> s.party, Collectors.summingInt(s -> s.totalVotes2016)));
        
        System.out.println(data);
    }

    public static void main(String[] args) throws Exception {
        new DataProcessingWithStreams().processData();    
    }


}