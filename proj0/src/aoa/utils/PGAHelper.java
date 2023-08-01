package aoa.utils;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.List;

public class PGAHelper {
    public static Map<Character, Integer> getFrequencyMap(List<String> words) {
        Map<Character,Integer> frequencyMap = new TreeMap<>();
        for (String word: words){
            for (Integer iter = 0;iter < word.length(); iter++){
                Integer frequency  = frequencyMap.getOrDefault(word.charAt(iter),0);
                frequencyMap.put(word.charAt(iter),frequency+1);
            }
        }
        return frequencyMap;
    }
    public static Map<Character, Integer> getOnlyMatchedFrequencyMap(List<String> words,String pattern) {
        List<String> wordPattern = new ArrayList<>(words);
        // remove length of the word that not match
        for (String str:words){
            if(pattern.length() != str.length()){
                wordPattern.remove(str);
            }
        }
        //remove pattern not match
        for(Integer iter = 0; iter < pattern.length() ; iter++){
            Character c = pattern.charAt(iter);
            if(c != '-'){
                for(String str : words){
                    if( str.length() > iter && str.charAt(iter) != c){
                        wordPattern.remove(str);
                    }
                }
            }
        }
        return getFrequencyMap(wordPattern);
    }
    public static Map<Character, Integer> getPAGAOnlyMatchedFrequencyMap(List<String> words,String pattern,List<Character> guesses) {
        List<String> wordPattern = new ArrayList<>(words);
        // remove length of the word that not match and remove pattern not match
        for(String str: words){
            if(str.length() == pattern.length()){
                for(Integer iter = 0; iter < pattern.length() ; iter++){
                    Character c = pattern.charAt(iter);
                    if(c != '-' ){
                        if ( str.charAt(iter) != c){
                            wordPattern.remove(str);
                        }
                    }
                    else {
                        Character d = str.charAt(iter);
                        if (pattern.contains(d.toString()) ){
                            wordPattern.remove((str));
                        }
                    }
                }
            }
            else {
                wordPattern.remove(str);
            }

        }
        //remove the word contains the character in guesses but not in pattern
        for(Character c: guesses){
            if(!pattern.contains(c.toString())){
                for (String str: words){
                    if(str.contains(c.toString())){
                        wordPattern.remove(str);
                    }
                }
            }
        }
        return getFrequencyMap(wordPattern);
    }
    public static  char getGuess(List<Character> guesses,Map<Character, Integer> freqMap) {
        for(Character c: guesses){
            freqMap.remove(c);
        }
        Character result = '?';
        Integer freqMax = 0;
        for(Character c: freqMap.keySet()){
            if(freqMax < freqMap.get(c)){
                freqMax = freqMap.get(c);
                result = c;
            }
        }
        return result;
    }
}
