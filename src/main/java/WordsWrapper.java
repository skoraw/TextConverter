import java.util.List;

public class WordsWrapper {
    private final List<String> words;
    private final Integer numberOfWords;

    public WordsWrapper(List<String> words, Integer numberOfWords) {
        this.words = words;
        this.numberOfWords = numberOfWords;
    }

    public List<String> getWords() {
        return words;
    }

    public Integer getNumberOfWords() {
        return numberOfWords;
    }
}
