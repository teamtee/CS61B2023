package ngordnet.ngrams;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Collection;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    private static final int MIN_YEAR = 1400;
    private static final int MAX_YEAR = 2100;
    private ArrayList<String> wordList;
    private ArrayList<TimeSeries> wordTimeSeries;
    private TimeSeries totalTimeSeries;
    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        wordList = new ArrayList<>();
        totalTimeSeries = new TimeSeries();
        wordTimeSeries = new ArrayList<>();
        //Read wordsFilename
        In in = new In(wordsFilename);
        String[] line  = in.readLine().split("\t+");
        int wordIndex = 0;
        wordList.add(line[0]);
        wordTimeSeries.add(new TimeSeries());
        wordTimeSeries.get(wordIndex).put(Integer.parseInt(line[1]),Double.parseDouble(line[2]));
        while (in.hasNextLine()){
            line = in.readLine().split("\t+");
            if (!wordList.get(wordIndex).equals(line[0])){
                wordIndex += 1;
                wordList.add(line[0]);
                wordTimeSeries.add(new TimeSeries());
            }
            wordTimeSeries.get(wordIndex).put(Integer.parseInt(line[1]),Double.parseDouble(line[2]));
        }
        //Read countsFilename
        in = new In(countsFilename);
        while (in.hasNextLine()){
            line = in.readLine().split(",");
            totalTimeSeries.put(Integer.parseInt(line[0]),Double.parseDouble(line[1]));
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy".
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        int wordIndex = wordList.indexOf(word);
        if (wordIndex == -1){
            return null;
        }
        return new TimeSeries(wordTimeSeries.get(wordIndex),startYear,endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy,
     * not a link to this NGramMap's TimeSeries. In other words, changes made
     * to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy".
     */
    public TimeSeries countHistory(String word) {
        int wordIndex = wordList.indexOf(word);
        return new TimeSeries(wordTimeSeries.get(wordIndex));
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        return new TimeSeries(totalTimeSeries);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        int wordIndex = wordList.indexOf(word);
        return new TimeSeries(wordTimeSeries.get(wordIndex).dividedBy(totalTimeSeries),startYear,endYear);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to
     * all words recorded in that year. If the word is not in the data files, return an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        int wordIndex = wordList.indexOf(word);
        return wordTimeSeries.get(wordIndex).dividedBy(totalTimeSeries);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS
     * between STARTYEAR and ENDYEAR, inclusive of both ends. If a word does not exist in
     * this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        summedWeightHistory(words);
        return new TimeSeries(summedWeightHistory(words),startYear,endYear);
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        int wordIndex;
        TimeSeries result = new TimeSeries();
        for(String word: words){
            wordIndex = wordList.indexOf(word);
            result = result.plus(wordTimeSeries.get(wordIndex));
        }
        return new TimeSeries(result).dividedBy(totalTimeSeries);
    }

}
