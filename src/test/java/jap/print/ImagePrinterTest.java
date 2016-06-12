package jap.print;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ImagePrinterTest {
	ImagePrinter ip;

	@Before
	public void setUp() {
		ip = new ImagePrinter();
	}

	@After
	public void tearDown() {
		ip = null;
	}

	@Test
	public void drawImage() throws Exception {
		ip.drawImage(new String[]{
				ImagePrinterTest.class.getClassLoader().getResource("test1.jpg").getFile(),
				ImagePrinterTest.class.getClassLoader().getResource("test2.jpg").getFile(),
				ImagePrinterTest.class.getClassLoader().getResource("test3.jpg").getFile()
		});
	}

}