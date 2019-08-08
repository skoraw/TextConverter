import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class SentenceToWordsConverter {

    WordsWrapper convertIntoWords(Sentence sentence) {
        String value = sentence.getValue();
        value = value.replaceAll(",", "");
        value = value.replaceAll("\\.", "");

        List<String> words = Arrays.stream(value.split(" "))
                .filter(word -> !word.isBlank())
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toList());

        return new WordsWrapper(words, words.size());
    }
}
