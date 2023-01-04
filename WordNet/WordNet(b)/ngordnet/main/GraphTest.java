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

    @Test
    public void testHyponymsSimple2() {
        WordNet wn = new WordNet("./data/wordnet/synsets.txt", "./data/wordnet/hyponyms.txt");
        assertEquals(List.of("bidder", "pre-emptor", "preemptor"), wn.getHyponyms("bidder").stream().toList());
        assertEquals(List.of("accompaniment", "acrobatics", "aerobatics", "air", "air_travel", "airspace", "airwave", "atmosphere", "augmentation", "aura", "aviation", "backup", "ballooning", "bass", "bass_part", "basso_continuo", "blind_flying", "blind_landing", "breath", "breeze", "canto", "continuo", "descant", "diminution", "discant", "exhalation", "fanfare", "figured_bass", "flight", "flourish", "fly-by", "flying", "flyover", "flypast", "fresh_breeze", "gentle_breeze", "gentle_wind", "glide", "gliding", "glissando", "ground_bass", "halitosis", "halitus", "hang_gliding", "hot_air", "idea", "inversion", "leitmotif", "leitmotiv", "light_air", "light_breeze", "line", "liquid_air", "low_level_flight", "maiden_flight", "melodic_line", "melodic_phrase", "melodic_theme", "melody", "moderate_breeze", "motif", "motive", "musical_accompaniment", "musical_theme", "mystique", "note", "obbligato", "obligato", "overflight", "paragliding", "parasailing", "part", "partita", "pass", "primo", "roulade", "sailing", "sailplaning", "sea_breeze", "secondo", "signature", "signature_tune", "slide", "soaring", "solo", "sortie", "spin", "statement", "strain", "strong_breeze", "stunt_flying", "stunting", "support", "swoop", "tailspin", "terrain_flight", "theme", "theme_song", "thorough_bass", "tucket", "tune", "vamp", "variation", "vibe", "vibration", "voice", "voice_part", "zephyr"), wn.getHyponyms("air").stream().toList());
    }
}
