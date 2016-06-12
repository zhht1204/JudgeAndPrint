package jap.image;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class JudgeImageTest {
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
			for (String testImage : testImages) {
				System.out.print(testImage + "\t\t是彩色图片吗\t\t");
				judgeJpg.setFile(new File(testImage));
				System.out.println(judgeJpg.isColored()[0]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}