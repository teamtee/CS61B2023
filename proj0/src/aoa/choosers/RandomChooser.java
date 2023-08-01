package aoa.choosers;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;
import java.util.List;
import java.lang.IllegalStateException;
import java.lang.IllegalArgumentException;
public class RandomChooser implements Chooser {
    private final String chosenWord;
    private String pattern;

    public RandomChooser(int wordLength, String dictionaryFile) {
        if( wordLength < 1){
            throw new IllegalArgumentException();
        }
        List<String> words = FileUtils.readWordsOfLength(dictionaryFile,wordLength);
        if( words.size() == 0){
            throw new IllegalStateException();
        }
        int numWord = words.size();
        int randomWordChooseNum = StdRandom.uniform(numWord);
        chosenWord = words.get(randomWordChooseNum);
        pattern = "-".repeat(wordLength);
    }

    @Override
    public int makeGuess(char letter) {
        Integer letterNum = 0;
        StringBuilder str = new StringBuilder(pattern);
        for(Integer iter = 0 ; iter < chosenWord.length() ; iter++){
            if( chosenWord.charAt(iter) == letter){
                letterNum++;
                str.setCharAt(iter,letter);
            }
        }
        pattern = new String(str);
        return letterNum;
    }

    @Override
    public String getPattern() {
        return pattern;
    }

    @Override
    public String getWord() {
        return chosenWord;
    }
}
