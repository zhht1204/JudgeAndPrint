package jap.config;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class ConfigIOTest {
	String configPath;
	String rootPath;
	ConfigIO io;
	Logger logger = LoggerFactory.getLogger(ConfigIOTest.class);

	@Before
	public void setUp() throws Exception {
		configPath = ConfigIO.class.getClassLoader().getResource("judge_and_print.xml").getPath();
		File configPathFile = new File(configPath);
		rootPath = configPathFile.getParent();
		io = new ConfigIO();
	}

	@After
	public void tearDown() throws Exception {
		configPath = null;
		rootPath = null;
		io = null;
	}

	@Test
	public void writeContent() throws Exception {
		io.writeContent("", "test");
	}

	@Test
	public void readContent() throws Exception {
		String content = io.readContent("open-office.home");
		logger.info(content);
	}

}