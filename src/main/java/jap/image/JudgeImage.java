package jap.image;

import jap.JudgeDocument;
import jap.utils.MathFunctions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JudgeImage extends JudgeDocument {

	private int height;
	private int width;
	private double serious;

	public JudgeImage() {
		super(null);
		this.height = 0;
		this.width = 0;
		this.serious = 100.0;
	}

	public JudgeImage(File file) {
		super(file);
		this.height = 0;
		this.width = 0;
		this.serious = 100.0;
	}

	public double getSerious() {
		return serious;
	}

	public void setSerious(double serious) {
		this.serious = serious;
	}

	@Override
	public void setFile(File file) {
		if (file != null &&
				!file.toString().toLowerCase().endsWith(".jpg") &&
				!file.toString().toLowerCase().endsWith(".gif") &&
				!file.toString().toLowerCase().endsWith(".png")) {
			this.showFileFormatErrorPane();
			this.setFile(null);
			return;
		}
		super.setFile(file);
	}

	private int[][][] readRGBImage() throws IOException { //get the 3D array of an RGB image
		//filePath: the path where to load the image
		int[][][] result = null;
		if (!this.getFile().exists()) {
			throw new IOException("没有设置要分析的文件");
		}
		try {
			BufferedImage bufImg = ImageIO.read(this.getFile());
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
		int[][][] integerRGB = new int[1][1][1];        //存储所有点的RGB值
		try {
			integerRGB = readRGBImage();
		} catch (IOException ex) {
			logger.error(ex.getMessage());
		}
		double[] DoubleRGB = new double[3];    //存储一个点的RGB值
		double varRGB;    //存储每一个点的方差
		int count = 0;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				DoubleRGB[0] = integerRGB[i][j][0];
				DoubleRGB[1] = integerRGB[i][j][1];
				DoubleRGB[2] = integerRGB[i][j][2];
				varRGB = MathFunctions.getVariance(DoubleRGB);    //计算一个点的方差
				// logger.debug(varRGB + " " + count);
				if (varRGB >= this.serious) {
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