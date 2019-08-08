import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SentenceToXmlDocumentWriterTest {

    @Test
    public void shouldWriteSentencesToXmlFile() {
        // given
        Writer xmlWriter = new SentenceToXmlDocumentWriter(new SentenceToWordsConverter());
        File destination = new File("test.xml");

        // when
        xmlWriter.write(SentenceTestHelper.prepareTestSentences(), destination);

        // then
        assertTrue(destination.exists());
        //TODO: wczytać plik i porównać zawartość z oczekiwanym body XML
        //assertEquals(expectedXmlContent(), wczytajPlik z zapisanej destynacji);
    }

    private String expectedXmlContent() {
        return "<sentence>dsadasd</sentence>";
    }
}