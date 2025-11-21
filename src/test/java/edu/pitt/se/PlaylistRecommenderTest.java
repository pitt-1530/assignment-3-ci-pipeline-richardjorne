package edu.pitt.se;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class PlaylistRecommenderTest {

    /**
     * Tests classifyEnergy logic.
     * 1. High Energy (Avg >= 140)
     * 2. Medium Energy (100 <= Avg <= 139)
     * 3. Low Energy (Avg < 100)
     * 4. Exception handling for empty lists.
     */
    @Test
    public void testClassifyEnergy() {
        // Test HIGH energy
        List<Integer> highBpms = Arrays.asList(140, 150, 160);
        assertEquals("HIGH", PlaylistRecommender.classifyEnergy(highBpms));

        // Test MEDIUM energy
        List<Integer> mediumBpms = Arrays.asList(100, 120, 110); // Avg 110
        assertEquals("MEDIUM", PlaylistRecommender.classifyEnergy(mediumBpms));

        // Test LOW energy
        List<Integer> lowBpms = Arrays.asList(60, 80, 90); // Avg ~76
        assertEquals("LOW", PlaylistRecommender.classifyEnergy(lowBpms));

        // Test Exception on Empty List
        List<Integer> emptyList = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            PlaylistRecommender.classifyEnergy(emptyList);
        });
    }

    /**
     * Tests isValidTrackTitle logic.
     * 1. Valid: Alphabetic + spaces, correct length.
     * 2. Invalid: Contains numbers or symbols.
     * 3. Invalid: Null input.
     */
    @Test
    public void testIsValidTrackTitle() {
        // Valid case
        assertTrue(PlaylistRecommender.isValidTrackTitle("Summer Vibes"));

        // Invalid case: Special characters/Numbers
        assertFalse(PlaylistRecommender.isValidTrackTitle("Summer Vibes 2025!"));
        
        // Invalid case: Too long (> 30 chars)
        assertFalse(PlaylistRecommender.isValidTrackTitle("This title is definitely way too long to be valid"));

        // Invalid case: Null
        assertFalse(PlaylistRecommender.isValidTrackTitle(null));
    }

    /**
     * Tests normalizeVolume logic.
     * 1. Clamp Upper (Values > 100 become 100).
     * 2. Clamp Lower (Values < 0 become 0).
     * 3. No Change (Values between 0-100 stay the same).
     */
    @Test
    public void testNormalizeVolume() {
        // Upper bound clamping
        assertEquals(100, PlaylistRecommender.normalizeVolume(150));
        
        // Lower bound clamping
        assertEquals(0, PlaylistRecommender.normalizeVolume(-20));

        // Inside range
        assertEquals(55, PlaylistRecommender.normalizeVolume(55));
    }
}