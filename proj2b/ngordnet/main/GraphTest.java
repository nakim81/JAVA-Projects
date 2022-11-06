package ngordnet.main;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

public class GraphTest {
    @Test
    public void testHyponymsSimple() {
        WordNet wn = new WordNet("./data/wordnet/synsets11.txt", "./data/wordnet/hyponyms11.txt");
        assertEquals(List.of("actifed", "antihistamine"), wn.getHyponyms("antihistamine").stream().toList());
        assertEquals(List.of("action", "change", "demotion"), wn.getHyponyms("action").stream().toList());
        assertEquals(List.of("augmentation", "increase", "jump", "leap"), wn.getHyponyms("increase").stream().toList());
    }
}
