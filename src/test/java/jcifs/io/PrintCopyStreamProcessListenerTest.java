package jcifs.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class PrintCopyStreamProcessListenerTest {

    @Test
    public void shouldCreateInstance() {
        PrintCopyStreamProcessListener listener = new PrintCopyStreamProcessListener();
        assertNotNull(listener);
    }

    @Test
    public void shouldSetFileName() {
        PrintCopyStreamProcessListener listener = new PrintCopyStreamProcessListener();
        listener.setFileName("test.txt");
        assertEquals("test.txt", listener.fileName);
    }

    @Test
    public void shouldHandleBytesTransferredWithKnownStreamSize() {
        PrintCopyStreamProcessListener listener = new PrintCopyStreamProcessListener();
        listener.setFileName("test.txt");
        // Should not throw
        listener.bytesTransferred(100, 50, 200);
    }

    @Test
    public void shouldHandleBytesTransferredWithUnknownStreamSize() {
        PrintCopyStreamProcessListener listener = new PrintCopyStreamProcessListener();
        listener.setFileName("test.txt");
        // streamSize == -1 means unknown, should not calculate percentage
        listener.bytesTransferred(100, 50, -1);
    }

    @Test
    public void shouldHandleZeroBytesTransferred() {
        PrintCopyStreamProcessListener listener = new PrintCopyStreamProcessListener();
        listener.setFileName("test.txt");
        listener.bytesTransferred(0, 0, 1000);
    }

    @Test
    public void shouldHandleCompleteTransfer() {
        PrintCopyStreamProcessListener listener = new PrintCopyStreamProcessListener();
        listener.setFileName("test.txt");
        listener.bytesTransferred(1000, 100, 1000);
    }

    @Test
    public void shouldHandleNullFileName() {
        PrintCopyStreamProcessListener listener = new PrintCopyStreamProcessListener();
        listener.bytesTransferred(100, 50, 200);
    }

    @Test
    public void shouldHaveHundredConstant() {
        PrintCopyStreamProcessListener listener = new PrintCopyStreamProcessListener();
        assertNotNull(listener.hundred);
        assertEquals(100, listener.hundred.intValue());
    }
}
