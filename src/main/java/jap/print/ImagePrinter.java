package jap.print;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import java.io.FileInputStream;
import java.io.IOException;

public class ImagePrinter {
	/**
	 * @param filePaths 图片路径数组
	 */
	public void drawImage(String[] filePaths) {
		try {
			DocFlavor dof = null;
			// 根据用户选择不同的图片格式获得不同的打印设备
			if (filePaths[0].toLowerCase().endsWith(".gif")) {
				dof = DocFlavor.INPUT_STREAM.GIF;
			} else if (filePaths[0].toLowerCase().endsWith(".jpg")) {
				dof = DocFlavor.INPUT_STREAM.JPEG;
			} else if (filePaths[0].toLowerCase().endsWith(".png")) {
				dof = DocFlavor.INPUT_STREAM.PNG;
			}
			if (dof != null) {
				// 字节流获取图片信息
				FileInputStream fis = new FileInputStream(filePaths[0]);
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
				PrintService service = ServiceUI.printDialog(null, 50, 50, pss, ps, dof, pras);

				for (String filePath : filePaths) {
					service = PrintServiceLookup.lookupPrintServices(dof, service.getAttributes())[0];
					if (service != null) {
						// 开始打印
						// TODO 一次打印多份
						fis = new FileInputStream(filePath);
						doc = new SimpleDoc(fis, dof, null);
						job = service.createPrintJob();
						job.print(doc, pras);
						fis.close();
					} else {
						throw new PrintException();
					}
				}
			}
		} catch (IOException ie) {
			// 捕获io异常
			ie.printStackTrace();
		} catch (PrintException pe) {
			// 捕获打印异常
			pe.printStackTrace();
		}

	}

}