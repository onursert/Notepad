import javax.swing.*;
import javax.swing.text.*;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class Notepad extends JFrame implements ActionListener, MouseListener {

    JMenuBar menuBar;

    JMenu fileMenu;
    JMenuItem newMenuItem, saveMenuItem, exitMenuItem;
    JMenu openSubFileMenu;
    JMenuItem openUsingBufferedReader, openUsingFileReader, openUsingScanner;

    JMenu editMenu;
    JMenuItem undoMenuItem, findAllMenuItem, replaceAllMenuItem;

    JMenu formatMenu;
    JMenuItem alignJustifyMenuItem, alignLeftMenuItem, alignRightMenuItem;

    JTextPane textPane;
    UndoManager undoManager;
    Highlighter highlighter;
    SimpleAttributeSet simpleAttributeSet;
    JScrollPane scrollPane;


    public Notepad() {
        super("Notepad");

        //Menu Bar
        menuBar = new JMenuBar();

        //File Menu and Items
        fileMenu = new JMenu("File");
        newMenuItem = new JMenuItem("New");
        newMenuItem.addActionListener(this);
        fileMenu.add(newMenuItem);
        //Open Sub File and Items
        openSubFileMenu = new JMenu("Open");
        openUsingBufferedReader = new JMenuItem("Open Using Buffered Reader");
        openUsingBufferedReader.addActionListener(this);
        openUsingFileReader = new JMenuItem("Open Using File Reader");
        openUsingFileReader.addActionListener(this);
        openUsingScanner = new JMenuItem("Open Using Scanner");
        openUsingScanner.addActionListener(this);
        openSubFileMenu.add(openUsingBufferedReader);
        openSubFileMenu.add(openUsingFileReader);
        openSubFileMenu.add(openUsingScanner);
        fileMenu.add(openSubFileMenu);
        //
        saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(this);
        fileMenu.add(saveMenuItem);
        //
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(this);
        fileMenu.add(exitMenuItem);

        //Edit Menu and Items
        editMenu = new JMenu("Edit");
        //
        undoMenuItem = new JMenuItem("Undo");
        undoMenuItem.addActionListener(this);
        editMenu.add(undoMenuItem);
        //
        findAllMenuItem = new JMenuItem("Find All");
        findAllMenuItem.addActionListener(this);
        editMenu.add(findAllMenuItem);
        //
        replaceAllMenuItem = new JMenuItem("Replace All");
        replaceAllMenuItem.addActionListener(this);
        editMenu.add(replaceAllMenuItem);

        //Format Menu and Items
        formatMenu = new JMenu("Format");
        //
        alignJustifyMenuItem = new JMenuItem("Align Justify");
        alignJustifyMenuItem.addActionListener(this);
        formatMenu.add(alignJustifyMenuItem);
        //
        alignLeftMenuItem = new JMenuItem("Align Left");
        alignLeftMenuItem.addActionListener(this);
        formatMenu.add(alignLeftMenuItem);
        //
        alignRightMenuItem = new JMenuItem("Align Right");
        alignRightMenuItem.addActionListener(this);
        formatMenu.add(alignRightMenuItem);

        //Add Menu to Menu Bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(formatMenu);
        //Set Menu Bar as default Frame Menu Bar
        setJMenuBar(menuBar);

        //Aligned Left Text Pane with Scroll Pane and Undo, Highlight Property
        //Text Pane
        textPane = new JTextPane();
        //Undo
        undoManager = new UndoManager();
        textPane.getDocument().addUndoableEditListener(undoManager);
        //Highlight and Unhighlight for Find All
        highlighter = textPane.getHighlighter();
        textPane.addMouseListener(this);
        //Align Left
        simpleAttributeSet = new SimpleAttributeSet();
        StyleConstants.setAlignment(simpleAttributeSet, StyleConstants.ALIGN_LEFT);
        textPane.setParagraphAttributes(simpleAttributeSet, true);
        //Scroll Pane
        scrollPane = new JScrollPane(textPane);
        add(scrollPane);

        //Pack Frame and show
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationByPlatform(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //File Menu Items Click Event
        if (e.getSource() == newMenuItem) {
            FileNewExit fileNewExit = new FileNewExit();
            FileNewExitCommandInvoker fileNewExitCommandInvoker = new FileNewExitCommandInvoker();

            //Exit File via Command Pattern
            FileExit fileExit = new FileExit(fileNewExit, this);
            fileNewExitCommandInvoker.takeCommand(fileExit);

            //New File via Command Pattern
            FileNew fileNew = new FileNew(fileNewExit);
            fileNewExitCommandInvoker.takeCommand(fileNew);

            fileNewExitCommandInvoker.executeCommand();
        }
        //Open Sub File Menu Items Click Event via Strategy Pattern
        if (e.getSource() == openUsingBufferedReader) {
            OpenFileStrategyContext strategyBufferedReader = new OpenFileStrategyContext(new OpenFileUsingBufferedReader(), this);
            try {
                String text = strategyBufferedReader.executeOpenFileStrategy();
                if (text != "") {
                    textPane.setText(text);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == openUsingFileReader) {
            OpenFileStrategyContext strategyFileReader = new OpenFileStrategyContext(new OpenFileUsingFileReader(), this);
            try {
                String text = strategyFileReader.executeOpenFileStrategy();
                if (text != "") {
                    textPane.setText(text);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == openUsingScanner) {
            OpenFileStrategyContext strategyScanner = new OpenFileStrategyContext(new OpenFileUsingScanner(), this);
            try {
                String text = strategyScanner.executeOpenFileStrategy();
                if (text != "") {
                    textPane.setText(text);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        //
        if (e.getSource() == saveMenuItem) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fileChooser.showOpenDialog(this);
            String srcDir;
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                srcDir = selectedFile.getAbsolutePath();
            } else {
                return;
            }

            Writer writer = null;
            File writeFile = new File(srcDir + "\\Notepad.txt");
            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(writeFile), "utf-8"));
                textPane.write(writer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == exitMenuItem) {
            //Exit File via Command Pattern
            FileNewExit fileNewExit = new FileNewExit();
            FileExit fileExit = new FileExit(fileNewExit, this);
            FileNewExitCommandInvoker fileNewExitCommandInvoker = new FileNewExitCommandInvoker();
            fileNewExitCommandInvoker.takeCommand(fileExit);
            fileNewExitCommandInvoker.executeCommand();
        }

        //Edit Menu Items Click Event
        if (e.getSource() == undoMenuItem) {
            if (undoManager.canUndo()) {
                undoManager.undo();
            }
        }
        if (e.getSource() == findAllMenuItem) {
            SingletonFindAll singletonFindAll = SingletonFindAll.getInstance();
            String findWord = singletonFindAll.findDialog();
            if ((findWord != null) && (findWord.length() > 0)) {
                Document document = textPane.getDocument();
                String text;
                highlighter.removeAllHighlights();
                try {
                    text = document.getText(0, document.getLength());
                    int position = 0;
                    while ((position = text.indexOf(findWord, position)) >= 0) {
                        highlighter.addHighlight(position, position + findWord.length(), DefaultHighlighter.DefaultPainter);
                        position += findWord.length();
                    }
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        }
        if (e.getSource() == replaceAllMenuItem) {
            JPanel findReplacePanel = new JPanel();

            findReplacePanel.add(new JLabel("Find what:"));
            JTextField findField = new JTextField(5);
            findReplacePanel.add(findField);

            findReplacePanel.add(Box.createHorizontalStrut(15));

            findReplacePanel.add(new JLabel("Replace with:"));
            JTextField replaceField = new JTextField(5);
            findReplacePanel.add(replaceField);

            int result = JOptionPane.showConfirmDialog(
                    null,
                    findReplacePanel,
                    "Replace",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null
            );
            if (result == JOptionPane.OK_OPTION) {
                String findWord = findField.getText();
                String replaceWord = replaceField.getText();
                Document document = textPane.getDocument();
                String text;
                try {
                    text = document.getText(0, document.getLength());
                    textPane.setText(text.replaceAll(findWord, replaceWord));
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        }

        //Format Menu Items Click Event via Strategy Pattern
        if (e.getSource() == alignJustifyMenuItem) {
            AlignStrategyContext strategyAlignJustify = new AlignStrategyContext(new AlignJustify(), textPane);
            strategyAlignJustify.executeStrategy();
        }
        if (e.getSource() == alignLeftMenuItem) {
            AlignStrategyContext strategyAlignLeft = new AlignStrategyContext(new AlignLeft(), textPane);
            strategyAlignLeft.executeStrategy();
        }
        if (e.getSource() == alignRightMenuItem) {
            AlignStrategyContext strategyAlignRight = new AlignStrategyContext(new AlignRight(), textPane);
            strategyAlignRight.executeStrategy();
        }
    }

    public static void main(String[] args) {
        new Notepad();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        highlighter.removeAllHighlights();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}