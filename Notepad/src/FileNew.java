public class FileNew implements FileNewExitCommand {
    FileNewExit fileNewExit;

    public FileNew(FileNewExit fileNewExit) {
        this.fileNewExit = fileNewExit;
    }

    @Override
    public void execute() {
        fileNewExit.newFile();
    }
}
