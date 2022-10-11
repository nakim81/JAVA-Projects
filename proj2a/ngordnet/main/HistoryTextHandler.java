package ngordnet.main;

import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.ngrams.*;
import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {
    private NGramMap m;
    public HistoryTextHandler(NGramMap map) {
        m = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        String response = "";
        for (String word: words) {
            response += String.format("%s: %s\n", word, m.hMap.get(word).toString());
        }
        return response;
    }
}
