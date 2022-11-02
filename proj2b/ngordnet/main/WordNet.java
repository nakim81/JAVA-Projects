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
        Map<Integer, List<String>> map = new HashMap<>();
        while (synsetFile.hasNextLine()) {
            String[] line = synsetFile.readLine().split(",");
            firstSynsetItem = Integer.parseInt(line[0]);
            secondSynsetItem = line[1];
            List<String> list = new ArrayList<>();
            if (secondSynsetItem.contains(" ")) {
                String[] line2 = secondSynsetItem.split("\t");
                for (String word : line2) {
                    list.add(word);
                }
            } else {
                list.add(secondSynsetItem);
            }
            graph.put(secondSynsetItem, null);
        }
        while(hyponymFile.hasNextLine()) {
            //한줄씩 봤을때, 첫 숫자가 부모, 나머지가 자식.
            String[] line = hyponymFile.readLine().split(",");
            String word = map.get(Integer.parseInt(line[0]));
            for (int i = 1; i < line.length; i++) {


                graph.setEdge(word, map.get(Integer.parseInt(line[i])));
            }
        }
    }

    public List<String> getHyponyms(String word) {
        return graph.getChildren(word);
    }
}
