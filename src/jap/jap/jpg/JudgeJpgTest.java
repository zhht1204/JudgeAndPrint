package jap.jap.jpg;

import jap.jpg.JudgeJpg;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class JudgeJpgTest {
    JudgeJpg judgeJpg;

    @Before
    public void setUp() throws Exception {
        judgeJpg = new JudgeJpg();
    }

    @After
    public void tearDown() throws Exception {
        judgeJpg = null;
    }

    @Test
    public void testIsColored() throws Exception {
        try {
            //填写测试图片的路径（绝对路径、相对路径均可）
            String[] testImages = {"F:/test6.jpg"};
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