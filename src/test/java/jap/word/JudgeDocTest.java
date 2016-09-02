package jap.word;

import jap.AbstractTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class JudgeDocTest extends AbstractTest {
	JudgeDoc judgeDoc;
	JudgeDoc judgeDocx;

	@Before
	public void setUp() {
		String docFileString = JudgeDocTest.class.getClassLoader().getResource("test.doc").getFile();
		String docxFileString = JudgeDocTest.class.getClassLoader().getResource("test.docx").getFile();
		judgeDoc = new JudgeDoc(new File(docFileString));
		judgeDocx = new JudgeDoc(new File(docxFileString));
	}

	@After
	public void tearDown() {
		judgeDoc = null;
		judgeDocx = null;
	}

	@Test
	public void isColored() throws Exception {
		boolean[] result = judgeDoc.isColored();
		boolean[] resultx = judgeDocx.isColored();

		for (int i = 0; i < result.length; i++) {
			logger.debug("doc 第" + i + "页是否彩色：" + result[i]);
		}
		for (int i = 0; i < resultx.length; i++) {
			logger.debug("docx 第" + i + "页是否彩色：" + result[i]);
		}
	}
}