package jap.word;

import jap.JudgeDocument;
import jap.config.JapConfig;
import jap.pdf.JudgePdf;
import jap.pdf.Pdf2JpgConverter;
import jap.utils.DocumentConverter;
import jap.utils.FileManager;

import javax.swing.*;
import java.io.File;

public class JudgeDoc extends JudgeDocument {
	private String OPENOFFICE_HOME;

	public JudgeDoc() {
		this.OPENOFFICE_HOME = JapConfig.getOpenOfficeHome();
	}

	public JudgeDoc(File file) {
		super(file);
		this.OPENOFFICE_HOME = JapConfig.getOpenOfficeHome();
	}

	@Override
	public void setFile(File file) {
		if (file != null &&
				!file.toString().toLowerCase().endsWith(".doc") &&
				!file.toString().toLowerCase().endsWith(".docx")) {
			this.showFileFormatErrorPane();
			super.setFile(null);
			return;
		}
		super.setFile(file);
	}

	@Override
	public boolean[] isColored() {
		if (this.OPENOFFICE_HOME != null) {
			DocumentConverter ptpc = new DocumentConverter();
			File tempFolder = new File(System.getProperty("java.io.tmpdir") + "japTemp/doc");
			if (tempFolder.exists() && tempFolder.isDirectory()) {
				FileManager.deleteDir(tempFolder);
			}
			new File(System.getProperty("java.io.tmpdir") + "japTemp").mkdir();
			tempFolder.mkdir();
			ptpc.convert(this.getFile(), new File(tempFolder.getPath() + "/temp.pdf"));
			Pdf2JpgConverter conv = new Pdf2JpgConverter();
			conv.convert(tempFolder.getPath() + "/temp.pdf");
			JudgePdf jpdf = new JudgePdf(new File(tempFolder.getPath() + "/temp.pdf"));
			return jpdf.isColored();
		} else {
			return null;
		}
	}

	public void setOPENOFFICE_HOME() {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("请选择OpenOffice的安装路径");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setMultiSelectionEnabled(false);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			this.OPENOFFICE_HOME = file.getAbsolutePath();
		} else {
			this.OPENOFFICE_HOME = JapConfig.getOpenOfficeHome();
		}
	}

	public void setOPENOFFICE_HOME(String OPENOFFICE_HOME) {
		this.OPENOFFICE_HOME = OPENOFFICE_HOME;
	}


}
