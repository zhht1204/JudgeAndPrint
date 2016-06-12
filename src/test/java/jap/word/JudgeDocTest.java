package jap.word;

import jap.utils.FileSelector;
import jap.word.JudgeDoc;
import org.junit.Test;

import java.io.File;

public class JudgeDocTest {

	@Test
	public void test() throws Exception {
		FileSelector selector = new FileSelector();
		File file = selector.selectWord();
		if (file != null) {
			JudgeDoc judge = new JudgeDoc(file);
			judge.setOPENOFFICE_HOME();
			boolean[] result = judge.isColored();
			for (boolean b : result) {
				System.out.println(b);
			}
		}
	}
}