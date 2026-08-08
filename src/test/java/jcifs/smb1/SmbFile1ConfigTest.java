package jcifs.smb1;

import org.junit.Test;
import static org.junit.Assert.*;

public class SmbFile1ConfigTest {

    @Test
    public void shouldHaveDefaultValues() {
        SmbFile1Config config = new SmbFile1Config();
        assertEquals("anonymous", config.getUsername());
        assertNull(config.getHost());
        assertNull(config.getPassword());
        assertNull(config.getDomain());
        assertNull(config.getSharedDir());
        assertTrue(config.isAllowUserInteraction());
        assertFalse(config.isAutoFlush());
        assertEquals(SmbFile1Config.DEFAULT_BUFFER_SIZE, config.getAutoFlushBlockSize());
        assertEquals(SmbFile1Config.DEFAULT_BUFFER_SIZE, config.getBufferSize());
        assertEquals(SmbFile1Config.DEFAULT_CHANNEL_SIZE, config.getChannelReadBufferSize());
        assertEquals(SmbFile1Config.DEFAULT_CHANNEL_SIZE, config.getChannelWriteBufferSize());
        assertEquals(SmbFile1Config.DEFAULT_CONNECT_TIMEOUT, config.getConnectTimeout());
        assertEquals(SmbFile1Config.DEFAULT_READ_TIMEOUT, config.getReadTimeout());
        assertFalse(config.isLocalBackupAble());
        assertFalse(config.isLogDebug());
        assertFalse(config.isUsecaches());
        assertNull(config.getCopyStreamProcessListener());
        assertNull(config.getCopyStreamProcessListenerName());
    }

    @Test
    public void shouldSetAndGetDomain() {
        SmbFile1Config config = new SmbFile1Config();
        config.setDomain("MYDOMAIN");
        assertEquals("MYDOMAIN", config.getDomain());
    }

    @Test
    public void shouldSetAndGetHost() {
        SmbFile1Config config = new SmbFile1Config();
        config.setHost("10.0.0.1");
        assertEquals("10.0.0.1", config.getHost());
    }

    @Test
    public void shouldSetAndGetUsername() {
        SmbFile1Config config = new SmbFile1Config();
        config.setUsername("testuser");
        assertEquals("testuser", config.getUsername());
    }

    @Test
    public void shouldSetAndGetPassword() {
        SmbFile1Config config = new SmbFile1Config();
        config.setPassword("testpass");
        assertEquals("testpass", config.getPassword());
    }

    @Test
    public void shouldSetAndGetSharedDir() {
        SmbFile1Config config = new SmbFile1Config();
        config.setSharedDir("public");
        assertEquals("public", config.getSharedDir());
    }

    @Test
    public void shouldSetAndAllowUserInteraction() {
        SmbFile1Config config = new SmbFile1Config();
        config.setAllowUserInteraction(false);
        assertFalse(config.isAllowUserInteraction());
    }

    @Test
    public void shouldSetAndGetAutoFlush() {
        SmbFile1Config config = new SmbFile1Config();
        config.setAutoFlush(true);
        assertTrue(config.isAutoFlush());
    }

    @Test
    public void shouldSetAndGetAutoFlushBlockSize() {
        SmbFile1Config config = new SmbFile1Config();
        config.setAutoFlushBlockSize(512);
        assertEquals(512, config.getAutoFlushBlockSize());
    }

    @Test
    public void shouldSetAndGetBufferSize() {
        SmbFile1Config config = new SmbFile1Config();
        config.setBufferSize(4096);
        assertEquals(4096, config.getBufferSize());
    }

    @Test
    public void shouldSetAndGetChannelReadBufferSize() {
        SmbFile1Config config = new SmbFile1Config();
        config.setChannelReadBufferSize(1024);
        assertEquals(1024, config.getChannelReadBufferSize());
    }

    @Test
    public void shouldSetAndGetChannelWriteBufferSize() {
        SmbFile1Config config = new SmbFile1Config();
        config.setChannelWriteBufferSize(1024);
        assertEquals(1024, config.getChannelWriteBufferSize());
    }

    @Test
    public void shouldSetAndGetConnectTimeout() {
        SmbFile1Config config = new SmbFile1Config();
        config.setConnectTimeout(10000);
        assertEquals(10000, config.getConnectTimeout());
    }

    @Test
    public void shouldSetAndGetReadTimeout() {
        SmbFile1Config config = new SmbFile1Config();
        config.setReadTimeout(10000);
        assertEquals(10000, config.getReadTimeout());
    }

    @Test
    public void shouldSetAndGetLocalBackupAble() {
        SmbFile1Config config = new SmbFile1Config();
        config.setLocalBackupAble(true);
        assertTrue(config.isLocalBackupAble());
    }

    @Test
    public void shouldSetAndGetLocalBackupDir() {
        SmbFile1Config config = new SmbFile1Config();
        config.setLocalBackupDir("/var/backup");
        assertEquals("/var/backup", config.getLocalBackupDir());
    }

    @Test
    public void shouldSetAndGetLogDebug() {
        SmbFile1Config config = new SmbFile1Config();
        config.setLogDebug(true);
        assertTrue(config.isLogDebug());
    }

    @Test
    public void shouldSetAndGetUsecaches() {
        SmbFile1Config config = new SmbFile1Config();
        config.setUsecaches(true);
        assertTrue(config.isUsecaches());
    }

    @Test
    public void shouldSetAndGetCopyStreamProcessListener() {
        SmbFile1Config config = new SmbFile1Config();
        jcifs.io.CopyStreamProcessListener listener = new jcifs.io.PrintCopyStreamProcessListener();
        config.setCopyStreamProcessListener(listener);
        assertNotNull(config.getCopyStreamProcessListener());
    }

    @Test
    public void shouldSetAndGetCopyStreamProcessListenerName() {
        SmbFile1Config config = new SmbFile1Config();
        config.setCopyStreamProcessListenerName("com.example.Listener");
        assertEquals("com.example.Listener", config.getCopyStreamProcessListenerName());
    }

    @Test
    public void shouldHaveCorrectDefaultConstants() {
        assertEquals(30000, SmbFile1Config.DEFAULT_CONNECT_TIMEOUT);
        assertEquals(30000, SmbFile1Config.DEFAULT_READ_TIMEOUT);
        assertEquals("anonymous", SmbFile1Config.ANONYMOUS_LOGIN);
        assertEquals(8 * 1024 * 1024, SmbFile1Config.DEFAULT_BUFFER_SIZE);
        assertEquals(2 * 1024 * 1024, SmbFile1Config.DEFAULT_CHANNEL_SIZE);
    }

    @Test
    public void shouldProduceNonEmptyToString() {
        SmbFile1Config config = new SmbFile1Config();
        config.setHost("testhost");
        String str = config.toString();
        assertNotNull(str);
        assertTrue(str.contains("FTPClientConfig"));
    }
}
