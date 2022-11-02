package ngordnet.main;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Set;

public class GraphTest {
    @Test
    public void testHyponymsSimple() {
        WordNet wn = new WordNet("./data/wordnet/synsets11.txt", "./data/wordnet/hyponyms11.txt");
        assertEquals(Set.of("antihistamine", "actifed"), wn.getHyponyms("antihistamine"));
    }
}
