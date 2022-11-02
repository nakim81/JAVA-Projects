package ngordnet.main;

import java.util.*;
import edu.princeton.cs.algs4.In;

public class WordNet {
    public Graph graph;
    public WordNet(String synsetFileName, String hyponymFileName) {
        graph = new Graph();
        In synsetFile = new In(synsetFileName);
        In hyponymFile = new In(hyponymFileName);
        int firstSynsetItem;
        String secondSynsetItem;
        Map<Integer, String> map = new HashMap<>();
        while (synsetFile.hasNextLine()) {
            String[] line = synsetFile.readLine().split(",");
            firstSynsetItem = Integer.parseInt(line[0]);
            secondSynsetItem = line[1];
            List<String> list = new ArrayList<>();
            if (secondSynsetItem.contains(" ")) {
                String[] line2 = secondSynsetItem.split("\t");
                for (String word : line2) {
                    graph.put(word, null);
                }
            } else {
                graph.put(secondSynsetItem, null);
            }
            map.put(firstSynsetItem, secondSynsetItem);
        }
        while(hyponymFile.hasNextLine()) {
            String[] line = hyponymFile.readLine().split(",");
            String[] words = map.get(Integer.parseInt(line[0])).split("\t");
            for (String word : words) {
                for (int i = 1; i < line.length; i++) {
                    if (map.get(Integer.parseInt(line[i])).contains(" ")) {
                        
                    } else {
                        graph.setEdge(word, map.get(Integer.parseInt(line[i])));
                    }
                }
            }
        }
    }

    public List<String> getHyponyms(String word) {
        return graph.getChildren(word);
    }
}
