package jap.gui;

import jap.JudgeDocument;
import jap.excel.JudgeXls;
import jap.image.JudgeImage;
import jap.pdf.JudgePdf;
import jap.word.JudgeDoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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

	private JudgeDocument judgeDocument;
	private File file;

	public PreviewGUI() {
	}

	public PreviewGUI(File file) {
		this.file = file;
		String lowerCaseFilePath = file.getAbsolutePath().toLowerCase();
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
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				previewPanel.getComponent(0).setVisible(false);
			}
		});
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				picturePanel.nextPage();
			}
		});
		this.frame = new JFrame("预览");
		frame.setContentPane(this.previewPanel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public PreviewGUI(String filepath) {
		this(new File(filepath));
	}

	// 测试用
	public static void main(String[] args) {
		PreviewGUI gui = new PreviewGUI("F:/test.jpg");
	}

	public JPanel getPreviewPanel() {
		return previewPanel;
	}

	private void createUIComponents() {
		this.picturePanel = new PicturePanel();
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
				pagination = judgeDocument.isColored().length;
			}
		}

		@Override
		public void paint(Graphics g) {
			Image picture = new ImageIcon(judgeDocument.getFile().getAbsolutePath()).getImage();
			this.setSize(picture.getWidth(null), picture.getHeight(null));
			g.drawImage(picture, 0, 0, 500, 780, this);
			super.paint(g);
		}

		@Override
		public void repaint() {
			if (picturePanel != null) {
				File previousImageFile = new File(
						System.getProperty("java.io.tmpdir") + "japTemp/pdf/pdfimage" + (picturePanel.getPageNo() - 1) +
								".jpg");
				File nextImageFile = new File(
						System.getProperty("java.io.tmpdir") + "japTemp/pdf/pdfimage" + (picturePanel.getPageNo() + 1) +
								".jpg");
				if (previousImageFile.exists()) {
					previousButton.setEnabled(true);
				} else {
					previousButton.setEnabled(false);
				}
				if (nextImageFile.exists()) {
					nextButton.setEnabled(true);
				} else {
					nextButton.setEnabled(false);
				}
			}
			super.repaint();
		}

		public int getPageNo() {
			return this.pageNo;
		}

		public void nextPage() {
			// TODO
			File imageFile = judgeDocument.getFile();
			int index = imageFile.getAbsolutePath().indexOf("pdfimage");
			System.out.println(imageFile.getAbsolutePath().charAt(index + 8));
			pageNo++;
		}

		public void previousPage() {
			// TODO
		}
	}
}
