package jap.gui;

import jap.config.JapConfig;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HelloDialog extends JDialog {
	private static HelloDialog helloDialog;
	private JPanel outerPanel;
	private JButton buttonOK;
	private JPanel contentPanel;
	private JPanel bottomPanel;
	private JTextArea contentTextArea;
	private JCheckBox notShowCheckBox;

	public HelloDialog() {
		setContentPane(outerPanel);
		setModal(true);
		getRootPane().setDefaultButton(buttonOK);
		setTitle("欢迎使用");

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				onOK();
			}
		});

		buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onOK();
			}
		});
	}

	public static HelloDialog getInstance() {
		if (helloDialog == null) {
			helloDialog = new HelloDialog();
		}
		return helloDialog;
	}

	public static void main(String[] args) {
		HelloDialog dialog = new HelloDialog();
		dialog.pack();
		dialog.setVisible(true);
		System.exit(0);
	}

	private void onOK() {
		if (this.notShowCheckBox.isSelected()) {
			JapConfig.setFirstCome(false);
		}
		this.setVisible(false);
	}
}
