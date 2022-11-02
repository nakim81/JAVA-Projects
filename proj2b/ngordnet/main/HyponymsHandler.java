package ngordnet.main;

import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        List<String> children = new ArrayList<>();
        for (String word : words) {
            children.addAll(wn.getHyponyms(word));
        }
        Collections.sort(children);
        String response = "[";
        for (int i = 0; i < children.size() - 1; i++) {
            if (i < children.size() - 1) {
                response += String.format("%s, ", children.get(i));
            } else {
                response += children.get(i);
            }
        }
        response += "]";

        return response;
    }
}
