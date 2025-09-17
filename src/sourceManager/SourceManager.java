package sourceManager;//Author: Gartner, Facundo

import java.io.FileNotFoundException;
import java.io.IOException;

public interface SourceManager {
    void open(String filePath) throws FileNotFoundException;

    void close() throws IOException;

    char getNextChar() throws IOException;

    String getCurrentLine();

    int getLineNumber();

    int getColumnNumber();

    char END_OF_FILE = (char) 26;
}
