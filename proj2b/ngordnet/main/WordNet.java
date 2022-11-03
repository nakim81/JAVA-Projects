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
        Map<Integer, List<String>> wordIDtoWords = new HashMap<>();
        Map<Integer, List<Integer>> hypoRelationship = new HashMap<>();
        while (synsetFile.hasNextLine()) {
            String[] line = synsetFile.readLine().split(",");
            Integer firstSynsetItem = Integer.parseInt(line[0]);
            String[] secondSynsetItem = line[1].split(" ");
            List myList = Arrays.asList(secondSynsetItem);
            wordIDtoWords.put(firstSynsetItem, myList);
        }
        while (hyponymFile.hasNextLine()) {
            String[] line = hyponymFile.readLine().split(",");
            Integer wordID = Integer.parseInt(line[0]);
            List<Integer> hypos;
            if (hypoRelationship.containsKey(wordID)) {
                hypos = hypoRelationship.get(wordID);
            } else {
                hypos = new ArrayList<>();
            }
            for (int i = 1; i < line.length; i++) {
                hypos.add(Integer.parseInt(line[i]));
            }
            hypoRelationship.put(wordID, hypos);
        }
        for (Integer integer : wordIDtoWords.keySet()) {
            List<Integer> hypoIDs = hypoRelationship.get(integer);
            List<String> words = wordIDtoWords.get(integer);
            if (hypoIDs != null) {
                for (String word : words) {
                  for (Integer hypoID : hypoIDs) {
                      List<String> hypoList = wordIDtoWords.get(hypoID);
                      for (String hypo : hypoList) {
                          graph.setEdge(word, hypo);
                      }
                  }
                }
            }
        }
    }

    public List<String> getHyponyms(String word) {
        return graph.getChildren(word);
    }
}
