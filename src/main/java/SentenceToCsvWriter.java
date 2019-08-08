import com.opencsv.CSVWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SentenceToCsvWriter implements Writer {

    private final SentenceToWordsConverter sentenceToWordsConverter;

    SentenceToCsvWriter(SentenceToWordsConverter sentenceToWordsConverter) {
        this.sentenceToWordsConverter = sentenceToWordsConverter;
    }

    public void write(List<Sentence> sentences, File destination) {
        try (CSVWriter csvWriter = new CSVWriter(
                Files.newBufferedWriter(Paths.get(destination.toString())),
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END)
        ) {
            List<WordsWrapper> wordsWrappers = sentences.stream()
                    .map(sentenceToWordsConverter::convertIntoWords)
                    .collect(Collectors.toList());

            csvWriter.writeNext(prepareHeaderRow(wordsWrappers).toArray(new String[0]));

            IntStream.range(0, wordsWrappers.size())
                    .forEach(idx -> {
                                List<String> words = wordsWrappers.get(idx).getWords();
                                words.add(0, "Sentence " + (idx + 1));
                                csvWriter.writeNext(words.toArray(new String[0]));
                            }
                    );

        } catch (IOException e) {
            throw new IllegalArgumentException("Write sentence to CSF file failed!");
        }
    }

    private List<String> prepareHeaderRow(List<WordsWrapper> wordsWrappers) {
        WordsWrapper max = Collections.max(wordsWrappers, Comparator.comparing(WordsWrapper::getNumberOfWords));
        List<String> header = new ArrayList<>();
        for (int i = 0; i < max.getNumberOfWords(); i++) {
            header.add("Word " + i);
        }
        // empty header for sentence number column
        header.add(0, " ");
        return header;
    }
}