package jap.gui;

import javax.swing.*;
import java.awt.event.*;

public class HelpDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextPane helpTextPane;
    private JPanel bottomPanel;
    private JPanel contentPanel;
    private JLabel helpLabel;

    private static HelpDialog helpDialog;

    public HelpDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("使用说明");

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });


// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onOK();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        dispose();
    }

    public static HelpDialog getInstance() {
        if (helpDialog == null) {
            helpDialog = new HelpDialog();
        }
        return helpDialog;
    }


    public static void main(String[] args) {
        HelpDialog dialog = new HelpDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
