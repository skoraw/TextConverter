import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class SentenceToXmlDocumentWriter implements Writer {

    private final SentenceToWordsConverter sentenceToWordsConverter;

    SentenceToXmlDocumentWriter(SentenceToWordsConverter sentenceToWordsConverter) {
        this.sentenceToWordsConverter = sentenceToWordsConverter;
    }

    public void write(List<Sentence> sentences, File destination) {
        try {
            Document xmldoc = createDocument();
            Element root = xmldoc.getDocumentElement();

            sentences.forEach(sentence -> {
                List<String> words = sentenceToWordsConverter.convertIntoWords(sentence).getWords();
                Element sentenceElement = createSentenceElement(xmldoc, words);
                root.appendChild(sentenceElement);
            });

            writeXML(xmldoc, destination);

        } catch (ParserConfigurationException | TransformerException | IOException e) {
            throw new IllegalArgumentException("Write sentence to XML document failed!");
        }
    }

    private Element createSentenceElement(Document xmldoc, Collection<String> words) {
        Element sentence = createElement(xmldoc, "sentence");
        Element wordElement = createElement(xmldoc, "word");
        words.forEach(word -> wordElement.appendChild(createNode(xmldoc, word)));
        sentence.appendChild(wordElement);
        return sentence;
    }

    private Element createElement(Document xmldoc, String elementName) {
        return xmldoc.createElement(elementName);
    }

    private Node createNode(Document xmldoc, String value) {
        return xmldoc.createTextNode(value);
    }

    private Document createDocument() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation impl = builder.getDOMImplementation();
        return impl.createDocument(null, "text", null);
    }

    private void writeXML(Document xmldoc, File file) throws TransformerException, IOException {
        DOMSource domSource = new DOMSource(xmldoc);
        StreamResult outputFile = new StreamResult(new FileWriter(file));
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
        transformer.transform(domSource, outputFile);
    }
}
