package aoa.guessers;

import aoa.utils.FileUtils;
import aoa.utils.PGAHelper;

import java.util.List;
import java.util.Map;

public class PAGALetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PAGALetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }
    public Map<Character, Integer> getFrequencyMap(String pattern, List<Character> guesses) {
        return PGAHelper.getPAGAOnlyMatchedFrequencyMap(words,pattern,guesses);
    }
    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN and the GUESSES that have been made. */
    public char getGuess(String pattern, List<Character> guesses) {
        Map<Character, Integer> freqMap = getFrequencyMap(pattern,guesses);
        return PGAHelper.getGuess(guesses,freqMap);
    }

    public static void main(String[] args) {
        PAGALetterFreqGuesser pagalfg = new PAGALetterFreqGuesser("data/example.txt");
        System.out.println(pagalfg.getGuess("-l--", List.of('l','c','o','f')));
    }
}
