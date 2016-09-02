package jap.pdf;

import jap.JudgeDocument;
import jap.image.JudgeImage;
import jap.utils.FileManager;

import java.io.File;
import java.util.ArrayList;

public class JudgePdf extends JudgeDocument {

	public JudgePdf() {
		super();
	}

	public JudgePdf(File file) {
		super(file);
	}

	@Override
	public void setFile(File file) {
		if (file != null &&
				!file.toString().toLowerCase().endsWith(".pdf")) {
			this.showFileFormatErrorPane();
			this.setFile(null);
			return;
		}
		super.setFile(file);
	}

	@Override
	public boolean[] isColored() {
		String tempFolderString = System.getProperty("java.io.tmpdir") + "japTemp/pdf";
		File tempFolder = new File(tempFolderString);
		if (tempFolder.exists() && tempFolder.isDirectory()) {
			FileManager.deleteDir(tempFolder);
		}
		new File(System.getProperty("java.io.tmpdir") + "japTemp").mkdir();
		tempFolder.mkdir();

		Pdf2JpgConverter converter = new Pdf2JpgConverter();
		converter.convert(getFile().getAbsolutePath());
		int i = 0;
		File imageFile = new File(tempFolderString + "/pdfimage" + i + ".jpg");
		ArrayList<Boolean> resultList = new ArrayList<Boolean>();
		resultList.clear();
		JudgeImage judgeImage = new JudgeImage();
		while (imageFile.exists()) {
			judgeImage.setFile(imageFile);
			resultList.add(judgeImage.isColored()[0]);
			i++;
			imageFile = new File(tempFolderString + "/pdfimage" + i + ".jpg");
		}
		boolean[] result = new boolean[resultList.size()];
		for (i = 0; i < resultList.size(); i++) {
			result[i] = resultList.get(i);
		}

		return result;
	}
}
