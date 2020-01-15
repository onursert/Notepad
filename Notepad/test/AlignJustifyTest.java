import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class AlignJustifyTest {
    JTextPane textPane = new JTextPane();
    JTextPane textPane2 = new JTextPane();

    @Test
    void align() {
        assertEquals(textPane, textPane2);
    }
}