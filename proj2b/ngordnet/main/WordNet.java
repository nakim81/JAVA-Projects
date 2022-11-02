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
        ArrayList<String> list = new ArrayList<String>();
        while (synsetFile.hasNextLine()) {
            String line = synsetFile.readLine();
            String[] tokens = line.split(",");
            firstSynsetItem = Integer.parseInt(tokens[0]);
            secondSynsetItem = tokens[1];
            list.set(firstSynsetItem, secondSynsetItem);
            graph.put(secondSynsetItem, null);
        }
        while(hyponymFile.hasNextLine()) {
            String line = hyponymFile.readLine();
            String[] tokens = line.split(",");
            String word = list.get(Integer.parseInt(tokens[0]));
            for (int i = 1; i < line.length(); i++) {
                graph.setEdge(word, list.get(Integer.parseInt(tokens[i])));
            }
        }

    }

    public List<String> getHyponyms(String word) {
        return graph.getChildren(word);
    }
}
