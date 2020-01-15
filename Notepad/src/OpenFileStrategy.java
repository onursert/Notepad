import java.io.IOException;

public interface OpenFileStrategy {
    String openFile(String srcDir) throws IOException;
}
