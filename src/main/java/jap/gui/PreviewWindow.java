package jap.gui;

import jap.JudgeDocument;
import jap.excel.JudgeXls;
import jap.image.JudgeImage;
import jap.pdf.JudgePdf;
import jap.word.JudgeDoc;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * 预览窗口
 */
public class PreviewWindow {
	private JPanel previewPanel;
	private JButton cancelButton;
	private JButton previousButton;
	private JButton nextButton;
	private JButton printButton;
	private JPanel picturePanel;

	private JudgeDocument judgeDocument;
	private File file;

	public PreviewWindow() {
	}

	public PreviewWindow(File file) {
		this.file = file;
		String lowerCaseFilePath = file.getAbsolutePath().toLowerCase();
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
	}

	public PreviewWindow(String file) {
		this(new File(file));
	}

	// 测试用
	public static void main(String[] args) {
		JFrame frame = new JFrame("PreviewWindow");
		frame.setContentPane(new PreviewWindow("F:/test.jpg").previewPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public JPanel getPreviewPanel() {
		return previewPanel;
	}

	private void createUIComponents() {
		this.picturePanel = new PicturePanel();
		// TODO:调整图片位置和框架大小
	}

	private class PicturePanel extends JPanel {
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
			g.drawImage(picture, 0, 0, picture.getWidth(this), picture.getHeight(this), this);
			super.paint(g);
		}

		private void nextPage() {
			// TODO
		}

		private void previousPage() {
			// TODO
		}
	}
}
