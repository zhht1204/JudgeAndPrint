package jap.excel;

import jap.AbstractTest;
import org.junit.Test;

public class JudgeXlsTest extends AbstractTest {

	@Test
	public void testIsColored() throws Exception {
		JudgeXls jxls = new JudgeXls(JudgeXlsTest.class.getClassLoader().getResource("test.xls").getFile());
		logger.debug("test file:" + JudgeXlsTest.class.getClassLoader().getResource("test.xls").getFile());
		logger.debug("folder:" + System.getProperty("java.io.tmpdir") + "japTemp/xls/");
		boolean[] result = jxls.isColored();
		for (int i = 0; i < result.length; i++) {
			logger.debug("第" + i + "页是彩色：" + result[i]);
		}
	}

}