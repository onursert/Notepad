import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

public class OpenFileStrategyContext {
    private OpenFileStrategy openFileStrategy;
    Notepad notepad;

    public OpenFileStrategyContext(OpenFileStrategy openFileStrategy, Notepad notepad) {
        this.openFileStrategy = openFileStrategy;
        this.notepad = notepad;
    }

    public String executeOpenFileStrategy() throws IOException {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(notepad);
        String srcDir;
        if (result == JFileChooser.APPROVE_OPTION) {
            srcDir = fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            return "";
        }

        return openFileStrategy.openFile(srcDir);
    }
}
