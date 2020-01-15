import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class OpenFileUsingBufferedReaderTest {

    @Test
    void openFile() throws IOException {
        ClassLoader classLoader = new OpenFileUsingBufferedReaderTest().getClass().getClassLoader();
        String srcDir = classLoader.getResource("lorem.txt").getFile();
        OpenFileUsingBufferedReader openFileUsingBufferedReader = new OpenFileUsingBufferedReader();
        assertEquals(openFileUsingBufferedReader.openFile(srcDir), 5);
    }
}