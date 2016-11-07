package jap.config;

import org.apache.log4j.PropertyConfigurator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class ConfigIO {
	static Logger logger = LoggerFactory.getLogger(ConfigIO.class);
	String confFilePath;

	public ConfigIO() {
		confFilePath = "judge_and_print.xml";
		PropertyConfigurator.configure(confFilePath);
	}

	public void writeContent(String nodePosition, String value) {
		String[] nodePositions = nodePosition.split("\\.");
		try {
			StringBuilder builder = new StringBuilder();
			builder.delete(0, builder.length());
			String line;
			BufferedReader br = new BufferedReader(new FileReader(confFilePath));
			while ((line = br.readLine()) != null) {
				builder.append(line);
			}
			br.close();
			Document document = DocumentHelper.parseText(builder.toString());
			Element rootElement = document.getRootElement();
			Element targetElement = rootElement;
			if (nodePositions.length >= 1) {
				if (!"".equals(nodePositions[0])) {
					for (String node : nodePositions) {
						targetElement = targetElement.element(node);
					}
				}
			}
			targetElement.setText(value);
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			XMLWriter writer = new XMLWriter(new FileWriter(confFilePath), format);
			writer.write(document);
			writer.close();
		} catch (DocumentException e1) {
			logger.error(e1.getMessage());
		} catch (IOException e1) {
			logger.error(e1.getMessage());
		}
	}

	public String readContent(String nodePosition) {
		String[] nodePositions = nodePosition.split("\\.");
		String content = "";
		try {
			StringBuilder builder = new StringBuilder();
			builder.delete(0, builder.length());
			String line;
			BufferedReader br = new BufferedReader(new FileReader(confFilePath));
			while ((line = br.readLine()) != null) {
				builder.append(line);
			}
			br.close();
			Document document = DocumentHelper.parseText(builder.toString());
			Element rootElement = document.getRootElement();
			Element targetElement = rootElement;
			for (String node : nodePositions) {
				targetElement = targetElement.element(node);
			}
			content = targetElement.getStringValue();
		} catch (DocumentException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return content;
	}
}
