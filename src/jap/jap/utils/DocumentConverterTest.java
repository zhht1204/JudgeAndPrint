package jap.jap.utils;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class DocumentConverterTest {

    @Test
    public void testConverter() throws Exception {
        DocumentConverter doco = new DocumentConverter("E:/OpenOffice 4");
        doco.converter(new File("F:/test.doc"), new File("F:/test.pdf"));
    }
}