package ngordnet.main;

import ngordnet.browser.NgordnetServer;
import ngordnet.ngrams.NGramMap;

public class Main {
    public static void main(String[] args) {
        NgordnetServer hns = new NgordnetServer();

        /* The following code might be useful to you.

        String wordFile = "./data/ngrams/top_14377_words.csv";
        String countFile = "./data/ngrams/total_counts.csv";
        NGramMap ngm = new NGramMap(wordFile, countFile);

        */
        hns.startUp();
        NGramMap wordMap = new NGramMap("data/ngrams/top_49887_words.csv","data/ngrams/total_counts.csv");
        hns.register("history", new DummyHistoryHandler(wordMap));
        hns.register("historytext", new DummyHistoryTextHandler(wordMap));
    }
}
