package ngordnet.main;

import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.ngrams.*;
import ngordnet.plotting.Plotter;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class HistoryHandler extends NgordnetQueryHandler {
    private NGramMap myMap;

    public HistoryHandler(NGramMap map) {
        myMap = map;
    }

    @Override
    public String handle(NgordnetQuery q) {

        ArrayList<TimeSeries> lts = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        List<String> words = q.words();
        for (String word : words) {
            labels.add(word);
            //lts.add(myMap.weightHistory(word));
        }

        for (String word : words) {
            lts.add(myMap.weightHistory(word));
        }

        XYChart chart = Plotter.generateTimeSeriesChart(labels, lts);
        String encodedImage = Plotter.encodeChartAsString(chart);

        return encodedImage;
    }
}
