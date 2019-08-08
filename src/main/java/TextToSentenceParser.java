import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TextToSentenceParser {

    public Collection<Sentence> read(File source) {
        List<Sentence> sentences = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(source.toString()), StandardCharsets.UTF_8)) {
            String currentLine;
            String remaining = null;
            while ((currentLine = reader.readLine()) != null) {

                if (remaining != null) {
                    currentLine = remaining + currentLine;
                    remaining = null;
                }

                boolean endedWithDot = currentLine.endsWith(".");
                List<String> strings = Arrays.asList(currentLine.split("\\."));
                int size = strings.size();

                for (int i = 0; i < size; i++) {
                    if (!endedWithDot && i == size - 1) {
                        remaining = strings.get(i);
                    }
                    sentences.add(new Sentence(strings.get(i)));
                }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sentences;
    }
}
