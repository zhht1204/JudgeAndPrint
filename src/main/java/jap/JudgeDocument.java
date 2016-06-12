package jap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public abstract class JudgeDocument {
	public Logger logger = LoggerFactory.getLogger(JudgeDocument.class);

	abstract public boolean[] isColored();

	abstract public void setFile(File file);
}
