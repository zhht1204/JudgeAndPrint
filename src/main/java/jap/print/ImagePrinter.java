package jap.print;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * java打印图片
 *
 * @author Administrator
 */
public class ImagePrinter {
    /**
     * @param fileName 图片路径
     */
    public void drawImage(String fileName) {
        try {
            DocFlavor dof = null;
            // 根据用户选择不同的图片格式获得不同的打印设备
            if (fileName.toLowerCase().endsWith(".gif")) {
                dof = DocFlavor.INPUT_STREAM.GIF;
            } else if (fileName.toLowerCase().endsWith(".jpg")) {
                dof = DocFlavor.INPUT_STREAM.JPEG;
            } else if (fileName.toLowerCase().endsWith(".png")) {
                dof = DocFlavor.INPUT_STREAM.PNG;
            }
            if(dof != null) {
                // 字节流获取图片信息
                FileInputStream fis = new FileInputStream(fileName);
                // 获得打印属性
                PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
                // 每一次默认打印一页
                pras.add(new Copies(1));
                // 获得打印设备 ，字节流方式，图片格式
                PrintService pss[] = PrintServiceLookup.lookupPrintServices(dof, pras);
                // 如果没有获取打印机
                if (pss.length == 0) {
                    // 终止程序
                    return;
                }
                // 获取第一个打印机
                PrintService ps = pss[0];
                // 获得打印工作
                DocPrintJob job = ps.createPrintJob();

                // 设置打印内容
                Doc doc = new SimpleDoc(fis, dof, null);
                // 出现设置对话框
                PrintService service = ServiceUI.printDialog(null, 50, 50, pss, ps,
                        dof, pras);
                if (service != null) {
                    // 开始打印
                    job = service.createPrintJob();
                    job.print(doc, pras);
                    fis.close();
                }
            } else {
                throw new PrintException();
            }
        } catch (IOException ie) {
            // 捕获io异常
            ie.printStackTrace();
        } catch (PrintException pe) {
            // 捕获打印异常
            pe.printStackTrace();
        }

    }

    public static void main(String args[]) {
        ImagePrinter dp = new ImagePrinter();
        dp.drawImage("F:/签名.jpg");
    }

}