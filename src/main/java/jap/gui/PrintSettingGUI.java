package jap.gui;

import jap.config.JapConfig;
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
	private JCheckBox previewCheckBox;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu helpMenu;
	private JMenuItem openFileMenuItem;
	private JMenuItem selectOpenOfficeMenuItem;
	private JMenuItem quitMenuItem;

	private HelloDialog dialog;

	public PrintSettingGUI() {
		//第一次使用显示欢迎
		if (JapConfig.isFirstCome()) {
			this.dialog = HelloDialog.getInstance();
			this.dialog.pack();
			this.dialog.setVisible(true);
		}

		frame = new JFrame("设置打印文件");
		frame.setContentPane(jPanel);
		buildUpMenuBar();
		frame.setJMenuBar(menuBar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		selectFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = FileSelector.selectAppropriateFile();
				if (file != null) {
					filePathTextField.setText(file.toString());
				}
			}
		});

		//检查、创建临时文件夹
		File tempFolder = new File(System.getProperty("java.io.tmpdir") + "japTemp");
		if (!tempFolder.exists() || !tempFolder.isDirectory()) {
			tempFolder.mkdir();
		}
		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (previewCheckBox.isSelected()) {
					showPrintPreview();
				} else {
					print();
				}
			}
		});
	}

	/**
	 * 打印选取的内容
	 */
	private void print() {

	}

	/**
	 * 显示打印预览
	 */
	private void showPrintPreview() {
		JFrame frame = new JFrame("预览");
		PreviewWindow previewWindow = new PreviewWindow(new File(this.filePathTextField.getText()));
		frame.setContentPane(previewWindow.getPreviewPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
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
				HelpDialog.getInstance().pack();
				HelpDialog.getInstance().setVisible(true);
			}
		});
		openFileMenuItem = new JMenuItem("打开文件");
		fileMenu.add(openFileMenuItem);
		selectOpenOfficeMenuItem = new JMenuItem("选择OpenOffice路径（"
				+ (JapConfig.getOpenOfficeHome().equals("") ?
				"无" : (JapConfig.getOpenOfficeHome().length() > 5 ?
				(JapConfig.getOpenOfficeHome().substring(0, 5) + "...") : JapConfig.getOpenOfficeHome())
		) + "）");
		selectOpenOfficeMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("请选择OpenOffice的安装路径");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setMultiSelectionEnabled(false);
				chooser.setCurrentDirectory(new File(JapConfig.getOpenOfficeHome()));

				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					JapConfig.setOpenOfficeHome(file.getAbsolutePath());
					selectOpenOfficeMenuItem.setText("选择OpenOffice路径（"
							+ (file.getAbsolutePath().length() > 5 ? (file.getAbsolutePath().substring(0, 5) + "...") :
							file.getAbsolutePath())
							+ "）");
				}
			}
		});
		fileMenu.add(selectOpenOfficeMenuItem);
		quitMenuItem = new JMenuItem("退出");
		fileMenu.add(quitMenuItem);
		quitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}
}
