package jcifs.utils;

import org.junit.Test;
import static org.junit.Assert.*;

public class Smb1FileUtilsTest {

    @Test
    public void shouldConvertExtensionsToSuffixes() {
        String[] extensions = {"java", "xml", "txt"};
        String[] suffixes = Smb1FileUtils.toSuffixes(extensions);
        assertEquals(3, suffixes.length);
        assertEquals(".java", suffixes[0]);
        assertEquals(".xml", suffixes[1]);
        assertEquals(".txt", suffixes[2]);
    }

    @Test
    public void shouldConvertEmptyExtensionsArray() {
        String[] extensions = {};
        String[] suffixes = Smb1FileUtils.toSuffixes(extensions);
        assertEquals(0, suffixes.length);
    }

    @Test
    public void shouldConvertSingleExtension() {
        String[] extensions = {"pdf"};
        String[] suffixes = Smb1FileUtils.toSuffixes(extensions);
        assertEquals(1, suffixes.length);
        assertEquals(".pdf", suffixes[0]);
    }
}
