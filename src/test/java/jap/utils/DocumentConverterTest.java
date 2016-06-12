package jap.utils;

import org.junit.Test;

import java.io.File;

public class DocumentConverterTest {

    @Test
    public void testConverter() throws Exception {
        DocumentConverter doco = new DocumentConverter();
        String inFile = DocumentConverterTest.class.getClassLoader().getResource("test.doc").getFile();
        String outPath = DocumentConverterTest.class.getClassLoader().getResource("test.doc").getPath();
        doco.convert(new File(inFile), new File(outPath + "test.pdf"));
    }
}