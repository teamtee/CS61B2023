package aoa.guessers;

import aoa.utils.FileUtils;
import aoa.utils.PGAHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PatternAwareLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PatternAwareLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }
    public Map<Character, Integer> getOnlyMatchedFrequencyMap(String pattern) {
        return PGAHelper.getOnlyMatchedFrequencyMap(words,pattern);
    }
    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN. */
    public char getGuess(String pattern, List<Character> guesses) {
        Map<Character,Integer> wordMatchedFreqMap = getOnlyMatchedFrequencyMap(pattern);
        return PGAHelper.getGuess(guesses,wordMatchedFreqMap);
    }

    public static void main(String[] args) {
        PatternAwareLetterFreqGuesser palfg = new PatternAwareLetterFreqGuesser("data/example.txt");
        System.out.println(palfg.getGuess("-e--", List.of('e')));
    }
}