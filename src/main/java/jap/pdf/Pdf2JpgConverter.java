package jap.pdf;

import jap.utils.FileManager;
import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class Pdf2JpgConverter {
	public void convert(String src, String folder) {
		File tempFolder = new File(folder);
		if (tempFolder.exists() && tempFolder.isDirectory()) {
			FileManager.deleteDir(tempFolder);
		}
		tempFolder.mkdir();
		try {
			Document document = new Document();
			document.setFile(src);
			File file;
			int i;
			for (i = 0; i < document.getNumberOfPages(); i++) {
				BufferedImage image = (BufferedImage)
						(document.getPageImage(i, GraphicsRenderingHints.SCREEN, Page.BOUNDARY_CROPBOX, 0f, 2f));
				RenderedImage rendImage = image;
				// capture the page image to file
				file = new File(folder + "/pdfimage" + i + ".jpg");
				ImageIO.write(rendImage, "jpg", file);
				image.flush();
			}
			// clean up resources
			document.dispose();

		} catch (IOException ie) {
			ie.printStackTrace();
			JOptionPane.showMessageDialog(null, "Please check file.", "IO Error", JOptionPane.ERROR_MESSAGE);
		} catch (PDFSecurityException e) {
			e.printStackTrace();
		} catch (PDFException e) {
			e.printStackTrace();
		}
	}

	public void convert(String src) {
		String tempFolderString = System.getProperty("java.io.tmpdir") + "japTemp/pdf";
		new File(System.getProperty("java.io.tmpdir") + "japTemp").mkdir();
		new File(tempFolderString).mkdir();
		this.convert(src, tempFolderString);
	}

}