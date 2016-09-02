package jap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.File;

public abstract class JudgeDocument {
	public Logger logger = LoggerFactory.getLogger(JudgeDocument.class);
	private File file;

	public JudgeDocument() {
		this.setFile(null);
	}

	public JudgeDocument(File file) {
		this.setFile(file);
	}

	abstract public boolean[] isColored();

	public File getFile() {
		return this.file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void showFileFormatErrorPane() {
		JOptionPane.showMessageDialog(null, "文件格式错误", "请检查", JOptionPane.ERROR_MESSAGE);
	}
}
