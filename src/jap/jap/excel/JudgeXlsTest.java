package jap.jap.excel;

import org.junit.Test;

import static org.junit.Assert.*;

public class JudgeXlsTest {

    @Test
    public void testIsColored() throws Exception {
        JudgeXls jxls = new JudgeXls("F:/动画.xls");
        jxls.setOPENOFFICE_HOME();
        System.out.println(System.getProperty("java.io.tmpdir"));
        boolean[] result = jxls.isColored();
        for(boolean b : result) {
            System.out.println(b);
        }
    }

}