import java.io.File;
import java.util.List;

public interface Writer {
    void write(List<Sentence> sentences, File destination);
}
