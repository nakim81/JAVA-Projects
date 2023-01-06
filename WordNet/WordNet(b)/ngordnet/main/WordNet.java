package ngordnet.main;

import java.sql.Array;
import java.util.*;

import edu.princeton.cs.algs4.In;

public class WordNet {
    private HashMap<Set<String>, Set<Integer>> wordsToWordID;
    private HashMap<Integer, List<Integer>> hypoRelationship;
    private HashMap<Integer, List<String>> wordIDToWords;
    private HashMap<String, Set<Integer>> wordToWordID;

    public WordNet(String synsetFileName, String hyponymFileName) {
        
        In synsetFile = new In(synsetFileName);
        In hyponymFile = new In(hyponymFileName);
        wordsToWordID = new HashMap<>();
        hypoRelationship = new HashMap<>();
        wordIDToWords = new HashMap<>();
        wordToWordID = new HashMap<>();
        while (synsetFile.hasNextLine()) {
            String[] line = synsetFile.readLine().split(",");
            Integer firstSynsetItem = Integer.parseInt(line[0]);
            String[] secondSynsetItem = line[1].split(" ");
            Set<String> stringSet = new HashSet<>();
            List myList = Arrays.asList(secondSynsetItem);
            for (String string : secondSynsetItem) {
                stringSet.add(string);
                if (wordToWordID.containsKey(string)) {
                    wordToWordID.get(string).add(firstSynsetItem);
                } else {
                    Set<Integer> set = new HashSet<>();
                    set. add(firstSynsetItem);
                    wordToWordID.put(string, set);
                }
            }
            if (wordsToWordID.get(stringSet) == null) {
                Set<Integer> integers = new HashSet<>();
                integers.add(firstSynsetItem);
                wordsToWordID.put(stringSet, integers);
            } else {
                wordsToWordID.get(stringSet).add(firstSynsetItem);
            }
            wordIDToWords.put(firstSynsetItem, myList);
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
    }

    public Set<String> getHyponyms(String word) {
        List<Integer> integerList = new ArrayList<>();
        if (wordToWordID.containsKey(word)) {
            Set<Integer> temp = wordToWordID.get(word);
            for (Integer integer : temp) {
                integerList.add(integer);
            }
        }
        return getChildren(integerList);
    }

    private Set<String> getChildren(List<Integer> stack) {
        //Tree traversal
        Set<String> children = new HashSet<>();
        Stack<List<Integer>> traversalStack = new Stack<>();
        traversalStack.push(stack);
        while (!traversalStack.isEmpty()) {
            List<Integer> current = traversalStack.pop();
            for (Integer integer : current) {
                List<String> words = wordIDToWords.get(integer);
                for (String word : words) {
                    children.add(word);
                }
                List<Integer> temp = hypoRelationship.get(integer);
                if (temp == null) {
                } else {
                    traversalStack.push(temp);
                }
            }
        }
        return children;
    }
}
