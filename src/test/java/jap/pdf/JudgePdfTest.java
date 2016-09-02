package jap.pdf;

import jap.AbstractTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class JudgePdfTest extends AbstractTest {
	JudgePdf judgePdf;

	@Before
	public void setUp() {
		String pdfFile = JudgePdfTest.class.getClassLoader().getResource("test.pdf").getFile();
		judgePdf = new JudgePdf(new File(pdfFile));
	}

	@After
	public void tearDown() {
		judgePdf = null;
	}

	@Test
	public void isColored() throws Exception {
		boolean[] result = judgePdf.isColored();

		for (int i = 0; i < result.length; i++) {
			logger.debug("第" + i + "页是否彩色：" + result[i]);
		}
	}

}