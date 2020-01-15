import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class OpenFileUsingScanner implements OpenFileStrategy {
    @Override
    public String openFile(String srcDir) throws IOException {
        File file = new File(srcDir);
        Scanner scanner = new Scanner(file);
        String content = "";
        while (scanner.hasNextLine()) {
            content += scanner.nextLine() + "\n";
        }
        scanner.close();
        return content;
    }
}

