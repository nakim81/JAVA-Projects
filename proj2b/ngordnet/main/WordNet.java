package ngordnet.main;

import java.util.*;

import edu.princeton.cs.algs4.In;

public class WordNet {
    public Graph graph;

    public WordNet(String synsetFileName, String hyponymFileName) {
        /**How do I handle multiple words for one wordID? Using single array or single map didn't work out.
         * My third approach is multiple maps.
         * Possible approaches that I am considering now: one map for wordID and word, one map for hyponyms, and try to
         * build graph by combining data in two maps.
         */
        graph = new Graph();
        In synsetFile = new In(synsetFileName);
        In hyponymFile = new In(hyponymFileName);
        Map<Integer, String> map = new HashMap<>();
        Map<Integer, Set<Integer>> map2 = new HashMap<>();
        while (synsetFile.hasNextLine()) {
            String[] line = synsetFile.readLine().split(",");
            Integer firstSynsetItem = Integer.parseInt(line[0]);
            String secondSynsetItem = line[1];
            map.put(firstSynsetItem, secondSynsetItem);
        }
        while (hyponymFile.hasNextLine()) {
            String[] line = hyponymFile.readLine().split(",");
            Integer word = Integer.parseInt(line[0]);
            Set<Integer> hypos;
            if (map2.containsKey(word)) {
                hypos = map2.get(word);
            } else {
                hypos = new HashSet<>();
            }
            for (int i = 1; i < line.length; i++) {
                hypos.add(Integer.parseInt(line[i]));
            }
            map2.put(word, hypos);
        }
        for (Integer integer : map.keySet()) {
            Set<Integer> hypoIDs = map2.get(integer);
            //String[] words = map.get(integer).split("\t");
            if (hypoIDs != null) {
                //for (String word : words) {
                    for (Integer hypoID : hypoIDs) {
                        //String[] hypoList = map.get(hypoID).split("\t");
                        //for (String hypo : hypoList) {
                            String word = map.get(integer);
                            String hypo = map.get(hypoID);
                            graph.setEdge(word, hypo);
                        //}
                    }
                //}
            }
        }
    }

    public List<String> getHyponyms(String word) {
        return graph.getChildren(word);
    }
}
