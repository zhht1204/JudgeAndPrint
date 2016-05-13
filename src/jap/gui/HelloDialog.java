package jap.gui;

import javax.swing.*;
import java.awt.event.*;

public class HelloDialog extends JDialog {
    private static HelloDialog helloDialog;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextPane helloTextPane;

    public HelloDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        this.setVisible(false);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static HelloDialog getInstance() {
        if(helloDialog == null) {
            helloDialog = new HelloDialog();
        }
        return helloDialog;
    }

    public static void main(String[] args) {
        HelloDialog dialog = HelloDialog.getInstance();
        dialog.pack();
        dialog.setVisible(true);
    }
}
