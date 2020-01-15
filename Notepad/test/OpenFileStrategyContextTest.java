import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class OpenFileStrategyContextTest {
    OpenFileStrategy openFileStrategy;
    Notepad notepad;

    @Test
    void executeOpenFileStrategy() throws IOException {
        assertEquals(new OpenFileStrategyContext(openFileStrategy, notepad).executeOpenFileStrategy(), 3);
    }
}