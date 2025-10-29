package sourceManager;//Author: Juan Dingevan

import java.io.*;
import java.nio.charset.StandardCharsets;

public class SourceManagerImpl implements SourceManager{
    private BufferedReader reader;
    private String currentLine;
    private int lineNumber;
    private int lineIndexNumber;
    boolean isEnter;

    public SourceManagerImpl() {
        currentLine = "";
        lineNumber = 1;
        lineIndexNumber = 0;
        isEnter = false;
    }

    @Override
    public void open(String filePath) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);

        reader = new BufferedReader(inputStreamReader);
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

    @Override
    public char getNextChar() throws IOException {
        int charInteger = reader.read();
        lineIndexNumber++;

        if(isEnter){
            lineIndexNumber = 0;
            lineNumber++;
            currentLine = "";

            isEnter = false;
        }

        if(charInteger == -1)
            return END_OF_FILE;

        if (charInteger == '\n') {
            isEnter = true;
        } else
            currentLine += (char) charInteger;

        return (char) charInteger;
    }

    @Override
    public String getCurrentLine() {
        return currentLine;
    }

    @Override
    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public int getColumnNumber() {
        return lineIndexNumber;
    }
}
