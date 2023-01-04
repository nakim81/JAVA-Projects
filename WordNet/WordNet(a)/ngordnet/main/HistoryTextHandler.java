package ngordnet.main;

import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.ngrams.*;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {
    private NGramMap myMap;
    public HistoryTextHandler(NGramMap map) {
        myMap = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        String response = "";
        int startYear = q.startYear();
        int endYear = q.endYear();
        for (String word: words) {
            response += String.format("%s: %s\n", word, myMap.weightHistory(word, startYear, endYear).toString());
            //response += word + ": " + myMap.weightHistory(word).toString() + "\n";
        }
        return response;
    }
}
