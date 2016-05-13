package jap.gui;

import jap.utils.FileSelector;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class PrintSettingGUI {
    JFrame frame;
    private JPanel jPanel;
    private JTextField filePathTextField;
    private JButton selectFileButton;
    private JLabel filePathLabel;
    private JButton printButton;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu helpMenu;
    private JMenuItem openFileMenuItem;
    private JMenuItem selectOpenOfficeMenuItem;
    private JMenuItem quitMenuItem;

    private String OPENOFFICE_HOME;

    private HelloDialog dialog;

    public PrintSettingGUI() {
        this.dialog = HelloDialog.getInstance();
        this.dialog.pack();
        this.dialog.setVisible(true);

        frame = new JFrame("设置打印文件");
        frame.setContentPane(jPanel);
        buildUpMenuBar();
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = FileSelector.selectAppropriateFile();
                if(file != null) {
                    filePathTextField.setText(file.toString());
                }
            }
        });

        //检查、创建临时文件夹
        File tempFolder = new File(System.getProperty("java.io.tmpdir") + "japTemp");
        if(!tempFolder.exists() || !tempFolder.isDirectory()) {
            tempFolder.mkdir();
        }
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(filePathTextField.getText() == null || filePathTextField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "请选择要打印的文件", "错误", JOptionPane.ERROR_MESSAGE);
                } else if(OPENOFFICE_HOME == null || OPENOFFICE_HOME.equals("")) {
                    JOptionPane.showMessageDialog(null, "请选择OpenOffice的安装路径", "错误", JOptionPane.ERROR_MESSAGE);
                } else {

                }
            }
        });
    }

    private void buildUpMenuBar() {
        menuBar = new JMenuBar();
        fileMenu = new JMenu("文件");
        menuBar.add(fileMenu);
        helpMenu = new JMenu("帮助");
        menuBar.add(helpMenu);
        helpMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                HelpDialog helpDialog = new HelpDialog();
                helpDialog.pack();
                helpDialog.setVisible(true);
            }
        });
        openFileMenuItem = new JMenuItem("打开文件");
        fileMenu.add(openFileMenuItem);
        selectOpenOfficeMenuItem = new JMenuItem("选择OpenOffice路径（无）");
        selectOpenOfficeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("请选择OpenOffice的安装路径");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setMultiSelectionEnabled(false);

                int returnVal = chooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    OPENOFFICE_HOME = file.toString();
                    selectOpenOfficeMenuItem.setText("选择OpenOffice路径（"
                            + (OPENOFFICE_HOME.length() > 5 ? (OPENOFFICE_HOME.substring(0, 5) + "...") : OPENOFFICE_HOME)
                            + "）");
                }
            }
        });
        fileMenu.add(selectOpenOfficeMenuItem);
        quitMenuItem = new JMenuItem("退出");
        fileMenu.add(quitMenuItem);
        quitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

    private boolean hasSetOpenOffice() {
        return OPENOFFICE_HOME != null;
    }

    public String getOPENOFFICE_HOME() {
        return OPENOFFICE_HOME;
    }

    public static void main(String[] args) {
        PrintSettingGUI gui = new PrintSettingGUI();
    }
}
