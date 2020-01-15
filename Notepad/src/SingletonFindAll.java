import javax.swing.*;

public class SingletonFindAll {

    private static SingletonFindAll instance = new SingletonFindAll();

    private SingletonFindAll() {
    }

    public static SingletonFindAll getInstance() {
        return instance;
    }

    public String findDialog() {
        String findWord = (String) JOptionPane.showInputDialog(
                null,
                "Find what:\n",
                "Find",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
        return findWord;
    }
}
