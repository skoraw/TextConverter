import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class SentenceTestHelper {

    static List<Sentence> prepareTestSentences() {
        return Stream.of(
                new Sentence("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."),
                new Sentence("Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."),
                new Sentence("Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur."),
                new Sentence("Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
        ).collect(Collectors.toList());
    }
}
