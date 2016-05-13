package jap.jap.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MathFunctionsTest {
    double[] testData;
    @Before
    public void setUp() {
        testData = new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    }

    @Test
    public void testGetMax() throws Exception {
        System.out.println("最大值：" + MathFunctions.getMax(testData));
    }

    @Test
    public void testGetMin() throws Exception {
        System.out.println("最小值：" + MathFunctions.getMin(testData));
    }

    @Test
    public void testGetSum() throws Exception {
        System.out.println("求和：" + MathFunctions.getSum(testData));
    }

    @Test
    public void testGetCount() throws Exception {
        System.out.println("计数：" + MathFunctions.getCount(testData));
    }

    @Test
    public void testGetAverage() throws Exception {
        System.out.println("求平均：" + MathFunctions.getAverage(testData));
    }

    @Test
    public void testGetSquareSum() throws Exception {
        System.out.println("平方和：" + MathFunctions.getSquareSum(testData));
    }

    @Test
    public void testGetVariance() throws Exception {
        System.out.println("方差：" + MathFunctions.getVariance(testData));
    }

    @Test
    public void testGetStandardDeviation() throws Exception {
        System.out.println("标准差：" + MathFunctions.getStandardDeviation(testData));
    }

    @After
    public void tearDown() {
        testData = null;
    }
}