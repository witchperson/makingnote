
import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * Created by Kangwoo on 2017-07-24.
 */

class Menu extends JPanel {

    JFrame myFrame, about_noteplus;
    JMenu menu;
    JMenuBar menuBar;
    JTextField textField;
    JTextArea textArea;
    String file_name;
    GridBagConstraints c;
    private int width, height;

    public Menu() {
        super(new GridBagLayout());
        c = new GridBagConstraints();
        this.width = 1024;
        this.height = 768;
        textArea = new JTextArea(width, height);
        textArea.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);


    }

    private ActionListener new_Note() {
        ActionListener new_note = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getNewNote();
            }
        };
        return new_note;
    }

    private ActionListener open_File() {
        // TODO Auto-generated method stub
        ActionListener open_file = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                open();
            }
        };
        return open_file;
    }

    private ActionListener save_File() {
        ActionListener save = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                save();
            }
        };
        return save;
    }

    private ActionListener exit_File() {
        ActionListener exit = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ask_saveornot();
            }
        };
        return exit;
    }

    private ActionListener help() {
        // TODO Auto-generated method stub
        ActionListener help = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                about_noteplus();
            }

        };
        return help;
    }

    private void createMenubar() {
        menuBar = new JMenuBar();// making a menu bar
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription("The menu in this program that can open and save the file");
        menuBar.add(menu); // a group of JMenuItems
        JMenuItem menuItem = new JMenuItem("New Note", KeyEvent.VK_T);
        // menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("This makes a new note");
        menuItem.addActionListener(new_Note());
        menu.add(menuItem);

        JMenuItem menuItem1 = new JMenuItem("Open Note", KeyEvent.VK_T);
        menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.SHIFT_MASK));
        menuItem1.getAccessibleContext().setAccessibleDescription("This opens the note");
        menuItem1.addActionListener(open_File());
        menu.add(menuItem1);

        JMenuItem menuItem2 = new JMenuItem("Save Note", KeyEvent.VK_T);
        menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menuItem2.getAccessibleContext().setAccessibleDescription("This saves the note");
        menuItem2.addActionListener(save_File());
        menu.add(menuItem2);

        JMenuItem menuItem3 = new JMenuItem("Exit", KeyEvent.VK_T);
        menuItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.SHIFT_MASK));
        menuItem3.getAccessibleContext().setAccessibleDescription("Close the note");
        menuItem3.addActionListener(exit_File());
        menu.add(menuItem3);

        menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
        menuBar.add(menu);

        JMenuItem menuItem4 = new JMenuItem("About Note+", KeyEvent.VK_H);
        menuItem4.getAccessibleContext().setAccessibleDescription("Help & Information");
        menuItem4.addActionListener(help());
        menu.add(menuItem4);
    }

    public void getNewNote() {

        createMenubar();
        file_name = "New Note";
        myFrame = new JFrame(file_name);
        myFrame.setSize(width, height);
        myFrame.add(new Menu());
        myFrame.setJMenuBar(menuBar);
        myFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        myFrame.setVisible(true);

    }


    private void ask_saveornot() {
        int choice = JOptionPane.showConfirmDialog(null, "Save the File?");
        if (choice == JOptionPane.YES_OPTION) {
            save();
            System.exit(0);
        } else if (choice == JOptionPane.NO_OPTION) {
            System.exit(0);
        } else {
            myFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }
    }

    private void open() {
        // TODO Auto-generated method stub
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            try {
                myFrame.setTitle(f.getName().replaceAll(".txt", ""));
                textArea.read(new FileReader(f.getAbsolutePath()), null);

            } catch (IOException ex) {
                JOptionPane.showConfirmDialog(null, ex.getMessage());
            }

        }
    }

    private void save() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                File f = chooser.getSelectedFile();
                FileWriter fw = new FileWriter(f.getPath()+".txt");
                fw.write(textArea.getText());
                fw.flush();
                fw.close();
                myFrame.setTitle(f.getName());
            } catch (IOException ex) {
                JOptionPane.showConfirmDialog(null, ex.getMessage());
            }

        }

    }

    private void about_noteplus() {
        // TODO Auto-generated method stub
        about_noteplus = new JFrame();
        Font f = new Font("Arial", Font.ITALIC, 15);
        String content = "Developed by Kangwoo Choi \n Thank you for Using this program!";
        about_noteplus.setTitle("About Note+");
        about_noteplus.setSize(width/2, height/2);
        about_noteplus.setLocation(width/4, height/4);
        about_noteplus.getContentPane().setBackground(Color.gray);
        textField = new JTextField(50);
        textField.setFont(f);
        textField.setText(content);
        textField.setEditable(false);
        about_noteplus.add(textField);
        about_noteplus.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        about_noteplus.setVisible(true);


    }


}