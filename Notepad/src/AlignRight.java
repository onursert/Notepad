import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class AlignRight implements AlignStrategy {
    @Override
    public void align(JTextPane textPane) {
        SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
        StyleConstants.setAlignment(simpleAttributeSet, StyleConstants.ALIGN_RIGHT);
        textPane.setParagraphAttributes(simpleAttributeSet, true);
    }
}