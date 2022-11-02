package ngordnet.main;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class GraphTest {
    @Test
    public void testHyponymsSimple() {
        WordNet wn = new WordNet("./data/wordnet/synsets11.txt", "./data/wordnet/hyponyms11.txt");
        assertEquals(List.of("antihistamine", "actifed"), wn.getHyponyms("antihistamine"));
        assertEquals(List.of("action", "change", "demotion"), wn.getHyponyms("action"));
        assertEquals(List.of("increase", "jump", "leap", "augmentation"), wn.getHyponyms("increase"));
    }
}
