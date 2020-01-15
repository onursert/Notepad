import java.io.FileReader;
import java.io.IOException;

public class OpenFileUsingFileReader implements OpenFileStrategy {
    @Override
    public String openFile(String srcDir) throws IOException {
        FileReader fileReader = new FileReader(srcDir);
        String content = "";
        int i;
        while ((i = fileReader.read()) != -1) {
            content += (char) i;
        }
        fileReader.close();
        return content;
    }
}
