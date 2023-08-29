package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import java.util.List;

import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;

public class DummyHistoryTextHandler extends NgordnetQueryHandler {
    public static NGramMap wordNGMap;
    public DummyHistoryTextHandler(NGramMap inputNGMap){
        wordNGMap =  inputNGMap;
    }
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();


        StringBuilder response = new StringBuilder();
        for (String word:words){
            response.append(handleHelper(word,startYear,endYear));
        }
        return response.toString();
    }
    private String handleHelper(String word,int startYear,int endYear){
        StringBuilder result = new StringBuilder(word+": {");
        TimeSeries wordTime = wordNGMap.countHistory(word,startYear,endYear);
        for (int year:wordTime.years()){
            result.append(year + "=" + wordTime.get(year) +",");
        }
        result.deleteCharAt(result.length() - 1);
        result.append("}\n");
        return  result.toString();
    }
}
