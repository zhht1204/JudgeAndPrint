package jap.utils;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

import javax.swing.*;
import java.io.File;

public class DocumentConverter {
    String OPENOFFICE_HOME;

    public DocumentConverter(String OPENOFFICE_HOME) {
        this.OPENOFFICE_HOME = OPENOFFICE_HOME;
    }

    public void converter(File inputFile, File outputFile) {
        if(OPENOFFICE_HOME.length() <= 0) {
            JOptionPane.showMessageDialog(null, "未安装OpenOffice，请检查", "需要OpenOffice", JOptionPane.ERROR_MESSAGE);
        } else {
            DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();
            try {
                config.setOfficeHome(OPENOFFICE_HOME);
            } catch(IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "未安装OpenOffice，请检查", "需要OpenOffice", JOptionPane.ERROR_MESSAGE);
                return;
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
