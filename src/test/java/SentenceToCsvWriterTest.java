import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SentenceToCsvWriterTest {

    @Test
    public void shouldWriteSentencesToCsvFile() {
        // given
        Writer xmlWriter = new SentenceToCsvWriter(new SentenceToWordsConverter());
        File destination = new File("example.csv");

        // when
        xmlWriter.write(SentenceTestHelper.prepareTestSentences(), destination);

        // then
        assertTrue(destination.exists());
        //TODO: wczytać plik i porównać zawartość z oczekiwanym body XML
        //assertEquals(expectedXmlContent(), wczytajPlik z zapisanej destynacji);
    }
}