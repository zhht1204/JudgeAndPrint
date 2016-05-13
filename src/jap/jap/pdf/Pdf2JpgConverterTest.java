package jap.jap.pdf;

import jap.pdf.Pdf2JpgConverter;
import org.junit.Test;

public class Pdf2JpgConverterTest {

    @Test
    public void testConvertPDF2JPG() throws Exception {
        Pdf2JpgConverter converter = new Pdf2JpgConverter();
        converter.convertPdf2Jpg("F:/test.pdf");
    }
}