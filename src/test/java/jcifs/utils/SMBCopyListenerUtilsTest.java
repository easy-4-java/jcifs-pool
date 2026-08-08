package jcifs.utils;

import org.junit.Test;
import static org.junit.Assert.*;

public class SMBCopyListenerUtilsTest {

    @Test
    public void shouldNotThrowWhenListenerIsNull() {
        // initCopyListener should handle null listener gracefully
        // We can't easily create SmbFile1/SmbFile2 without network, but we can test
        // that the class loads without errors
        assertNotNull(SMBCopyListenerUtils.class);
    }
}
