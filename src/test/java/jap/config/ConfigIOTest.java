package jap.config;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class ConfigIOTest {
	private String configPath;
	private String rootPath;
	private ConfigIO io;
	private Logger logger;

	@Before
	public void setUp() throws Exception {
		configPath = ConfigIO.class.getClassLoader().getResource("judge_and_print.xml").getPath();
		File configPathFile = new File(configPath);
		rootPath = configPathFile.getParent();
		io = new ConfigIO();
		logger = LoggerFactory.getLogger(ConfigIOTest.class);
	}

	@After
	public void tearDown() throws Exception {
		configPath = null;
		rootPath = null;
		io = null;
		logger = null;
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

	@Test
	public void isFirstReadTest() {
		logger.info(io.readContent("first-come"));
	}

	@Test
	public void isFirstWriteTest() {
		io.writeContent("first-come", "false");
		logger.info(io.readContent("first-come"));
		assert !Boolean.valueOf(io.readContent("first-come"));
	}

}