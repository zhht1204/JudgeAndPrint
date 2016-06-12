package jap.utils;

import jap.config.JapConfig;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

import javax.swing.*;
import java.io.File;

public class DocumentConverter {
	String openOfficeHome;

	public DocumentConverter() {
		this.openOfficeHome = JapConfig.getOpenOfficeHome();
	}

	public void convert(File inputFile, File outputFile) {
		if (this.openOfficeHome.length() <= 0) {
			JOptionPane.showMessageDialog(null, "未指定OpenOffice，请检查", "需要OpenOffice", JOptionPane.ERROR_MESSAGE);
		} else {
			DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();
			try {
				config.setOfficeHome(openOfficeHome);
			} catch (IllegalArgumentException ex) {
				JOptionPane.showMessageDialog(null, "未指定OpenOffice，请检查", "需要OpenOffice", JOptionPane.ERROR_MESSAGE);
			}
			config.setPortNumber(8100);

			OfficeManager officeManager = config.buildOfficeManager();
			officeManager.start();

			OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
			converter.convert(inputFile, outputFile);

			officeManager.stop();
		}
	}

}
