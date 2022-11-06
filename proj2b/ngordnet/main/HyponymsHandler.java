package ngordnet.main;

import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;

import java.util.*;

public class HyponymsHandler extends NgordnetQueryHandler {
    private WordNet wn;
    private NGramMap ngram;

    public HyponymsHandler(WordNet wn, NGramMap ngm) {
        this.wn = wn;
        this.ngram = ngm;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        Integer k = q.k();
        List<Set<String>> sets = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            sets.add(i, wn.getHyponyms(word));
        }
        if (sets.size() == 0) {
            return "[]";
        }
        Set<String> temp = sets.get(0);
        for (int j = 0; j < sets.size(); j++) {
            temp.retainAll(sets.get(j));
        }
        List<String> children = new ArrayList<>();
        for (String word : temp) {
            children.add(word);
        }
        Collections.sort(children);
        String response = "[";
        if (k <= 0 || k == null) {
            for (int i = 0; i < children.size(); i++) {
                response += children.get(i);
                if (i < children.size() - 1) {
                    response += ", ";
                }
            }
        } else {
            List<Double> counts = new ArrayList<>();
            Map<Double, String> countsToWords = new TreeMap<>();
            for (String word : children) {
                TimeSeries ts = ngram.countHistory(word, startYear, endYear);
                Double count = 0.0;
                for (int year : ts.years()) {
                    count += ts.get(year);
                }
                if (count == 0.0) {
                    continue;
                } else {
                    counts.add(count);
                    countsToWords.put(count, word);
                }
            }
            Collections.sort(counts, Collections.reverseOrder());
            List<String> wordsList = new ArrayList<>();
            for (int i = 0; i < k && i < counts.size(); i++) {
                if (counts.get(i) == null) {
                    break;
                }
                Double count = counts.get(i);
                wordsList.add(countsToWords.get(count));
            }
            Collections.sort(wordsList);
            for (int i = 0; i < wordsList.size(); i++) {
                if (wordsList.get(i) == null) {
                    break;
                }
                String word = wordsList.get(i);
                response += word;
                if (i < k - 1) {
                    response += ", ";
                }
            }
        }
        response += "]";
        return response;
    }
}
