public class FileNewExit {
    public void newFile() {
        new Notepad();
    }

    public void exitFile(Notepad notepad) {
        notepad.dispose();
    }
}
