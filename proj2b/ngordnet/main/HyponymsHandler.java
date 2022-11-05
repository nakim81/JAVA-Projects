package ngordnet.main;

import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;

import java.util.*;

public class HyponymsHandler extends NgordnetQueryHandler {
    private WordNet wn;

    public HyponymsHandler(WordNet wn) {
        this.wn = wn;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        List<Set<String>> sets = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            sets.add(i, wn.getHyponyms(word));
        }
        Set<String> temp = sets.get(0);
        for(int j = 1; j < sets.size(); j++) {
            temp.retainAll(sets.get(j));
        }
        List<String> children = new ArrayList<>();
        for (String word : temp) {
            children.add(word);
        }
        Collections.sort(children);
        String response = "[";
        for (int i = 0; i < children.size(); i++) {
            response += children.get(i);
            if (i < children.size() - 1) {
                response += ", ";
            }
        }
        response += "]";
        return response;
    }
}
