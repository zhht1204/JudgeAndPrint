package jap.image;

import jap.JudgeDocument;
import jap.utils.MathFunctions;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class JudgeImage extends JudgeDocument {

	private String filePath = null;
	private int height = 0;
	private int width = 0;

	@Override
	public void setFile(File file) {
		this.filePath = file.getPath();
	}

	private int[][][] readRGBImage() { //get the 3D array of an RGB image
		//filePath: the path where to load the image
		File file = new File(filePath);
		int[][][] result = null;
		if (!file.exists()) {
			return result;
		}
		try {
			BufferedImage bufImg = ImageIO.read(file);
			height = bufImg.getHeight();
			width = bufImg.getWidth();
			result = new int[height][width][3];
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					//System.out.println(bufImg.getRGB(i, j) & 0xFFFFFF);
					int rgb = bufImg.getRGB(j, i);//visiting sequence for image: column, row
					result[i][j][0] = (rgb & 0xff0000) >> 16;
					result[i][j][1] = (rgb & 0xff00) >> 8;
					result[i][j][2] = (rgb & 0xff);
				}
			}
		} catch (IOException e) {
			logger.error("Image reading error!");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 区别黑白彩色图片
	 * 计算每一个像素点RGB的方差，统计所有方差的平均值
	 */
	@Override
	public boolean[] isColored() {
		int[][][] IntegerRGB = readRGBImage();        //存储所有点的RGB值
		double[] DoubleRGB = new double[3];    //存储一个点的RGB值
		double varRGB;    //存储每一个点的方差
		int count = 0;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				DoubleRGB[0] = IntegerRGB[i][j][0];
				DoubleRGB[1] = IntegerRGB[i][j][1];
				DoubleRGB[2] = IntegerRGB[i][j][2];
				varRGB = MathFunctions.getVariance(DoubleRGB);    //计算一个点的方差
				//System.out.println(varRGB + " " + count);
				if (varRGB >= 300.0) {
					count++;
				}
				if (count > 100) {
					return new boolean[]{true};
				}
			}
		}
		return new boolean[]{false};
	}

}