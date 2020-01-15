import javax.swing.*;

public class AlignStrategyContext {
    private AlignStrategy alignStrategy;
    JTextPane textPane;

    public AlignStrategyContext(AlignStrategy alignStrategy, JTextPane textPane) {
        this.alignStrategy = alignStrategy;
        this.textPane = textPane;
    }

    public void executeStrategy() {
        alignStrategy.align(textPane);
    }
}
