import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class OpenFileUsingBufferedReader implements OpenFileStrategy {
    @Override
    public String openFile(String srcDir) throws IOException {
        File file = new File(srcDir);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String content = "";
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            content += line + "\n";
        }
        bufferedReader.close();
        return content;
    }
}
