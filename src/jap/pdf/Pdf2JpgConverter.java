package jap.pdf;

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
    public void convertPdf2Jpg(String src, String folder) {
        try {
            System.out.println("Please wait...");
            Document document = new Document();
            document.setFile(src);
            File file;
            int i;
            for (i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage image = (BufferedImage)
                        (document.getPageImage(i, GraphicsRenderingHints.SCREEN,Page.BOUNDARY_CROPBOX, 0f, 2f));
                RenderedImage rendImage = image;
                // capture the page image to file
                file = new File(folder + "/pdfimage" + i + ".jpg");
                ImageIO.write(rendImage, "jpg", file);
                image.flush();
            }
            // clean up resources
            document.dispose();

            System.out.println("Conversion complete");
        } catch (IOException ie) {
            ie.printStackTrace();
            JOptionPane.showMessageDialog(null, "Please check file.", "IO Error", JOptionPane.ERROR_MESSAGE);
        } catch (PDFSecurityException e) {
            e.printStackTrace();
        } catch (PDFException e) {
            e.printStackTrace();
        }
    }

    public void convertPdf2Jpg(String src) {
        String tempFolder = System.getProperty("java.io.tmpdir") + "japTemp";
        this.convertPdf2Jpg(src, tempFolder);
    }

}