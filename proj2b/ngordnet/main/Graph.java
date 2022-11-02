package ngordnet.main;

import java.util.*;

public class Graph extends HashMap<String, Set<String>> {
    //private Map<String, Set<String>> graph;
    public Graph() {
        super();
    }

    public void setEdge(String word, String child) {
        //setting and edge by putting into the corresponding TreeSets
        if (!containsKey(word)) {
            Set<String> set = new TreeSet<>();
            set.add(child);
            put(word, set);
        } else {
            get(word).add(child);
        }
    }

    public List<String> getChildren(String word) {
        //Tree traversal
        List<String> children = new ArrayList<>();
        Stack<String> traversalStack = new Stack<>();
        traversalStack.push(word);
        while (!traversalStack.isEmpty()) {
            String current = traversalStack.pop();
            children.add(current);
            if (containsKey(current)) {
                for (String child : get(current)) {
                    traversalStack.push(child);
                }
            }
        }
        return children;
    }
}
