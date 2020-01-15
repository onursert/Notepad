public class FileExit implements FileNewExitCommand {
    FileNewExit fileNewExit;
    Notepad notepad;

    public FileExit(FileNewExit fileNewExit, Notepad notepad) {
        this.fileNewExit = fileNewExit;
        this.notepad = notepad;
    }

    @Override
    public void execute() {
        fileNewExit.exitFile(notepad);
    }
}
