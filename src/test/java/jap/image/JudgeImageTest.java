package jap.image;

import jap.AbstractTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class JudgeImageTest extends AbstractTest {
	JudgeImage judgeJpg;

	@Before
	public void setUp() throws Exception {
		judgeJpg = new JudgeImage();
	}

	@After
	public void tearDown() throws Exception {
		judgeJpg = null;
	}

	@Test
	public void testIsColored() throws Exception {
		try {
			//填写测试图片的路径（绝对路径、相对路径均可）
			String[] testImages = {
					JudgeImageTest.class.getClassLoader().getResource("test1.jpg").getFile(),
					JudgeImageTest.class.getClassLoader().getResource("test2.jpg").getFile(),
					JudgeImageTest.class.getClassLoader().getResource("test3.jpg").getFile(),
					JudgeImageTest.class.getClassLoader().getResource("test4.jpg").getFile()
			};
			judgeJpg.setFile(new File(testImages[0]));
			logger.debug("[" + testImages[0] + "]是彩色图片吗：" + judgeJpg.isColored()[0]);
			Assert.assertTrue(judgeJpg.isColored()[0]);

			judgeJpg.setFile(new File(testImages[1]));
			logger.debug("[" + testImages[1] + "]是彩色图片吗：" + judgeJpg.isColored()[0]);
			Assert.assertFalse(judgeJpg.isColored()[0]);

			judgeJpg.setFile(new File(testImages[2]));
			logger.debug("[" + testImages[2] + "]是彩色图片吗：" + judgeJpg.isColored()[0]);
			Assert.assertTrue(judgeJpg.isColored()[0]);

			judgeJpg.setFile(new File(testImages[3]));
			logger.debug("[" + testImages[3] + "]是彩色图片吗：" + judgeJpg.isColored()[0]);
			Assert.assertFalse(judgeJpg.isColored()[0]);
		} catch (Exception ex) {
			logger.error(ex.getClass().getName() + "：" + ex.getMessage());
		}
	}
}