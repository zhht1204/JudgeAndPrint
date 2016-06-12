package jap.excel;

import jap.JudgeDocument;
import jap.image.JudgeImage;
import jap.pdf.Pdf2JpgConverter;
import jap.utils.DocumentConverter;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class JudgeXls extends JudgeDocument {
	private File file;
	private String OPENOFFICE_HOME;

	public JudgeXls(String filepath) {
		setFile(new File(filepath));
	}

	public JudgeXls(File file) {
		setFile(file);
	}

	@Override
	public void setFile(File file) {
		this.file = file;
		if (!file.toString().toLowerCase().endsWith(".xls") && !file.toString().toLowerCase().endsWith(".xlsx")) {
			JOptionPane.showMessageDialog(null, "文件格式错误", "请检查", JOptionPane.ERROR_MESSAGE);
			this.file = null;
			return;
		}
	}

	@Override
	public boolean[] isColored() {
		if (this.OPENOFFICE_HOME != null) {
			DocumentConverter ptpc = new DocumentConverter();
			File tempFolder = new File(System.getProperty("java.io.tmpdir") + "japTemp");
			if (!tempFolder.exists() || !tempFolder.isDirectory()) {
				tempFolder.mkdir();
			}
			ptpc.convert(file, new File(tempFolder.getPath() + "/temp.pdf"));
			Pdf2JpgConverter conv = new Pdf2JpgConverter();
			conv.convert(tempFolder.getPath() + "/temp.pdf");
			ArrayList<Boolean> resultList = new ArrayList<Boolean>();
			int count = 0;
			File jpgFile = new File(tempFolder.getPath() + "/pdfimage" + count + ".image");
			JudgeImage jjpg = new JudgeImage();
			while (jpgFile.exists()) {
				jjpg.setFile(jpgFile);
				resultList.add(jjpg.isColored()[0]);
				count++;
				jpgFile = new File(tempFolder.getPath() + "/pdfimage" + count + ".image");
			}
			boolean[] result = new boolean[resultList.size()];
			for (count = 0; count < resultList.size(); count++) {
				result[count] = resultList.get(count);
			}
			tempFolder.deleteOnExit();
			return result;
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
			this.OPENOFFICE_HOME = "E:/OpenOffice 4";
		}
	}

	public void setOPENOFFICE_HOME(String OPENOFFICE_HOME) {
		this.OPENOFFICE_HOME = OPENOFFICE_HOME;
	}
}
