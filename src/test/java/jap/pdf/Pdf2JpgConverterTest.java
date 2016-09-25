package jap.pdf;

import jap.AbstractTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Pdf2JpgConverterTest extends AbstractTest {
	Pdf2JpgConverter converter;

	@Before
	public void setUp() {
		this.converter = new Pdf2JpgConverter();
		logger.debug(System.getProperty("java.io.tmpdir"));
	}

	@After
	public void tearDown() {
		this.converter = null;
	}

	@Test
	public void testConvertPDF2JPG() throws Exception {
		String pdfFile = Pdf2JpgConverterTest.class.getClassLoader().getResource("test.pdf").getFile();
		logger.debug(pdfFile);
		logger.debug("folder:" + System.getProperty("java.io.tmpdir") + "japTemp/");
		converter.convert(pdfFile);
	}
}