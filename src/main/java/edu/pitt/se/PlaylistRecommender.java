package edu.pitt.se;

import java.util.List;

public class PlaylistRecommender {

    /**
     * Calculates average BPM and returns energy level.
     * Rejects null or empty lists by throwing an exception.
     */
    public static String classifyEnergy(List<Integer> bpms) {
        if (bpms == null || bpms.isEmpty()) {
            throw new IllegalArgumentException("BPM list cannot be null or empty.");
        }

        int sum = 0;
        for (int bpm : bpms) {
            sum += bpm;
        }

        // Use double for precise average
        double average = (double) sum / bpms.size();

        if (average >= 140) {
            return "HIGH";
        } else if (average >= 100) {
            return "MEDIUM";
        } else {
            return "LOW";
        }
    }

    /**
     * Checks if title is non-null, length 1-30, and contains only 
     * alphabetic characters or spaces.
     */
    public static boolean isValidTrackTitle(String title) {
        if (title == null) {
            return false;
        }

        // Check length requirements (1 - 30 characters)
        if (title.length() < 1 || title.length() > 30) {
            return false;
        }

        // Regex to allow only Uppercase/Lowercase letters and spaces
        return title.matches("^[a-zA-Z ]+$");
    }

    /**
     * Clamps volume between 0 and 100.
     */
    public static int normalizeVolume(int volumeDb) {
        if (volumeDb > 100) {
            return 100;
        } else if (volumeDb < 0) {
            return 0;
        }
        return volumeDb;
    }
}