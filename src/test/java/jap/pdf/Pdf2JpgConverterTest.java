package jap.pdf;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Pdf2JpgConverterTest {
	Pdf2JpgConverter converter;

	@Before
	public void setUp() {
		this.converter = new Pdf2JpgConverter();
		System.out.println(System.getProperty("java.io.tmpdir"));
	}

	@After
	public void tearDown() {
		this.converter = null;
	}

	@Test
	public void testConvertPDF2JPG() throws Exception {
		String pdfFile = Pdf2JpgConverterTest.class.getClassLoader().getResource("test.pdf").getFile();
		converter.convert(pdfFile);
	}
}