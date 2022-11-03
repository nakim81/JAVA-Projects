package ngordnet.main;

import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;

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
        List<List<String>> lists = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            lists.add(i, wn.getHyponyms(word));
        }
        List<String> children = lists.get(0);
        for(int j = 1; j < lists.size(); j++) {
            children.retainAll(lists.get(j));
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
