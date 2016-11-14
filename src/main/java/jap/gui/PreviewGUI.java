package jap.gui;

import jap.JudgeDocument;
import jap.excel.JudgeXls;
import jap.image.JudgeImage;
import jap.pdf.JudgePdf;
import jap.print.ImagePrinter;
import jap.word.JudgeDoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * 预览窗口
 */
public class PreviewGUI {
	public JFrame frame;
	private JPanel previewPanel;
	private JButton cancelButton;
	private JButton previousButton;
	private JButton nextButton;
	private JButton printButton;
	private PicturePanel picturePanel;
	private JLabel pageNumberLabel;

	private JudgeDocument judgeDocument;
	private File file;
	private boolean[] isColored;

	public PreviewGUI(File input) {
		this.file = input;

		this.frame = new JFrame("预览");

		this.cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		this.previousButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				picturePanel.previousPage();
			}
		});
		this.nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				picturePanel.nextPage();
			}
		});
		this.printButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (file.exists() && file.canRead()) {
					if (file.getAbsolutePath().toLowerCase().endsWith("jpg") ||
							file.getAbsolutePath().toLowerCase().endsWith("gif") ||
							file.getAbsolutePath().toLowerCase().endsWith("png")) {
						ImagePrinter.drawImage(new String[]{file.getAbsolutePath()});
					} else {
						String lowerCaseFilePath = file.getAbsolutePath().toLowerCase();
						JudgeDocument judgeDocument;
						if (lowerCaseFilePath.endsWith("pdf")) {
							judgeDocument = new JudgePdf(file);
						} else if (lowerCaseFilePath.endsWith("doc") || lowerCaseFilePath.endsWith("docx")) {
							judgeDocument = new JudgeDoc(file);
						} else if (lowerCaseFilePath.endsWith("xls") || lowerCaseFilePath.endsWith("xlsx")) {
							judgeDocument = new JudgeXls(file);
						} else {
							JOptionPane.showMessageDialog(null, "文件格式错误", "错误", JOptionPane.ERROR_MESSAGE);
							return;
						}
						String imageFileString =
								System.getProperty("java.io.tmpdir") + "japTemp/pdf/pdfimage";
						java.util.List<String> bwFiles = new ArrayList<>();
						java.util.List<String> colorFiles = new ArrayList<>();
						for (int i = 0; i < isColored.length; i++) {
							if (isColored[i]) {
								colorFiles.add(imageFileString + i + ".jpg");
							} else {
								bwFiles.add(imageFileString + i + ".jpg");
							}
						}
						if (bwFiles.size() >= 0) {
							JOptionPane
									.showMessageDialog(null, "请选择【黑白】文件使用的打印机", "请选择", JOptionPane.INFORMATION_MESSAGE);
							ImagePrinter.drawImage(bwFiles);
						}
						if (colorFiles.size() >= 0) {
							JOptionPane
									.showMessageDialog(null, "请选择【彩色】文件使用的打印机", "请选择", JOptionPane.INFORMATION_MESSAGE);
							ImagePrinter.drawImage(colorFiles);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "文件读取错误，请检查", "错误", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		if (this.judgeDocument != null) {
			if (this.isColored != null &&
					!(this.judgeDocument.getFile().getAbsolutePath().toLowerCase().endsWith("jpg") ||
							this.judgeDocument.getFile().getAbsolutePath().toLowerCase().endsWith("png") ||
							this.judgeDocument.getFile().getAbsolutePath().toLowerCase().endsWith("gif"))) {
				String tempFileString = System.getProperty("java.io.tmpdir") + "japTemp/pdf/pdfimage1.jpg";
				File nextImageFile = new File(tempFileString);
				if (nextImageFile.exists()) {
					nextButton.setEnabled(true);
				}
			}

			this.frame.setContentPane(this.previewPanel);
			this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.frame.pack();
			this.frame.setVisible(true);
		}
	}

	public PreviewGUI(String filepath) {
		this(new File(filepath));
	}

	// 测试用
	public static void main(String[] args) {
		PreviewGUI gui = new PreviewGUI("F:/test.jpg");
	}

	public JPanel getPreviewPanel() {
		return this.previewPanel;
	}

	private void createUIComponents() {
		String lowerCaseFilePath = this.file.getAbsolutePath().toLowerCase();
		try {
			if (lowerCaseFilePath.endsWith("pdf")) {
				judgeDocument = new JudgePdf(file);
			} else if (lowerCaseFilePath.endsWith("doc") || lowerCaseFilePath.endsWith("docx")) {
				judgeDocument = new JudgeDoc(file);
			} else if (lowerCaseFilePath.endsWith("xls") || lowerCaseFilePath.endsWith("xlsx")) {
				judgeDocument = new JudgeXls(file);
			} else if (lowerCaseFilePath.endsWith("jpg") || lowerCaseFilePath.endsWith("png") ||
					lowerCaseFilePath.endsWith("gif")) {
				judgeDocument = new JudgeImage(file);
			} else {
				JudgeDocument.showFileFormatErrorPane();
				this.previewPanel.setVisible(false);
			}
		} catch (Exception ex) {
			frame.setVisible(false);
			return;
		}
		this.isColored = judgeDocument.isColored();
		this.picturePanel = new PicturePanel();
		pageNumberLabel = new JLabel();
		pageNumberLabel
				.setText("第1 / " + picturePanel.getPagination() + "页（" +
						(isColored[picturePanel.getPageNo() - 1] ? "彩色" : "黑白") + "）");
	}

	public class PicturePanel extends JPanel {
		int pagination;
		int pageNo;

		public PicturePanel() {
			super();
			setOpaque(false);
			pageNo = 1;
			pagination = 1;
			if (judgeDocument != null) {
				pagination = isColored.length;
			}
		}

		@Override
		public void paint(Graphics g) {
			g.clearRect(0, 0, 500, 780);
			String imageFileString = "";
			if (judgeDocument.getFile().getAbsolutePath().toLowerCase().endsWith("jpg") ||
					judgeDocument.getFile().getAbsolutePath().toLowerCase().endsWith("png") ||
					judgeDocument.getFile().getAbsolutePath().toLowerCase().endsWith("gif")) {
				imageFileString = judgeDocument.getFile().getAbsolutePath();
			} else {
				imageFileString =
						System.getProperty("java.io.tmpdir") + "japTemp/pdf/pdfimage" + (pageNo - 1) + ".jpg";
			}
			File imageFile = new File(imageFileString);
			Image picture = new ImageIcon(imageFile.getAbsolutePath()).getImage();
			this.setSize(500, 780);
			g.drawImage(picture, 0, 0, 500, 780, this);
			super.paint(g);
		}

		public int getPageNo() {
			return this.pageNo;
		}

		public int getPagination() {
			return this.pagination;
		}

		public void previousPage() {
			String previousFileString =
					System.getProperty("java.io.tmpdir") + "japTemp/pdf/pdfimage" + (pageNo - 2) + ".jpg";
			File previousFile = new File(previousFileString);
			if (previousFile.exists()) {
				pageNo--;
				repaint();
				nextButton.setEnabled(true);
				pageNumberLabel.setText("第" + this.pageNo + " / " + this.pagination + "页（" +
						(isColored[picturePanel.getPageNo() - 1] ? "彩色" : "黑白") + "）");
				previousFileString =
						System.getProperty("java.io.tmpdir") + "japTemp/pdf/pdfimage" + (pageNo - 2) + ".jpg";
				if (new File(previousFileString).exists()) {
					previousButton.setEnabled(true);
				} else {
					previousButton.setEnabled(false);
				}
			}
		}

		public void nextPage() {
			String nextFileString =
					System.getProperty("java.io.tmpdir") + "japTemp/pdf/pdfimage" + pageNo + ".jpg";
			File nextFile = new File(nextFileString);
			if (nextFile.exists()) {
				pageNo++;
				repaint();
				previousButton.setEnabled(true);
				pageNumberLabel.setText("第" + this.pageNo + " / " + this.pagination + "页（" +
						(isColored[picturePanel.getPageNo() - 1] ? "彩色" : "黑白") + "）");
				nextFileString = System.getProperty("java.io.tmpdir") + "japTemp/pdf/pdfimage" + pageNo + ".jpg";
				if (new File(nextFileString).exists()) {
					nextButton.setEnabled(true);
				} else {
					nextButton.setEnabled(false);
				}
			}
		}

	}
}
