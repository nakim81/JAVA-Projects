package ngordnet.main;

import java.util.*;

public class Graph extends HashMap<String, Set<String>> {
    //private Map<String, Set<String>> graph;
    public Graph() {
        super();
    }

    public void setEdge(String word, String child) {
        //setting and edge by putting into the corresponding TreeSets
        if (get(word) == null) {
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
            Set<String> temp = get(current);
            if (temp != null) {
                for (String child : temp) {
                    traversalStack.push(child);
                }
            }
        }
        return children;
    }
}
