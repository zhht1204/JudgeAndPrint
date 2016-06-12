package jap.pdf;

import jap.JudgeDocument;

import java.io.File;

public class JudgePdf extends JudgeDocument {
	private File file;

	@Override
	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public boolean[] isColored() {
		return new boolean[]{true};
	}
}
