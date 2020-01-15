import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileNewExitCommandInvoker {
    List<FileNewExitCommand> fileNewExitCommandList = new ArrayList<>();

    public void takeCommand(FileNewExitCommand fileNewExitCommand) {
        fileNewExitCommandList.add(fileNewExitCommand);
    }

    public void executeCommand() {
        //Iterator Pattern
        Iterator iterator = fileNewExitCommandList.iterator();
        while (iterator.hasNext()) {
            var next = iterator.next();
            if (next instanceof FileNewExitCommand) {
                ((FileNewExitCommand) next).execute();
            }
        }
        fileNewExitCommandList.clear();
    }
}
