import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FileExplorer extends JFrame {
    private JList<String> fileList;
    private DefaultListModel<String> listModel;

    public FileExplorer() {
        super("File Explorer");

        listModel = new DefaultListModel<>();
        fileList = new JList<>(listModel);
        fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fileList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    String selectedItem = fileList.getSelectedValue();
                    File file = new File(selectedItem);
                    if (file.isDirectory()) {
                        listFiles(selectedItem);
                    } else {
                        JOptionPane.showMessageDialog(FileExplorer.this, "Selected: " + selectedItem);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(fileList);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        listFiles(System.getProperty("user.home")); // Starting directory
    }

    private void listFiles(String directory) {
        listModel.clear();
        File[] files = new File(directory).listFiles();
        if (files != null) {
            for (File file : files) {
                listModel.addElement(file.getPath());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FileExplorer());
    }
}

