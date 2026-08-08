package jcifs.smb;

import org.junit.Test;
import static org.junit.Assert.*;

public class SmbFile2ConfigTest {

    @Test
    public void shouldHaveDefaultValues() {
        SmbFile2Config config = new SmbFile2Config();
        assertEquals("anonymous", config.getUsername());
        assertNull(config.getHost());
        assertNull(config.getPassword());
        assertNull(config.getDomain());
        assertNull(config.getSharedDir());
        assertTrue(config.isAllowUserInteraction());
        assertFalse(config.isAutoFlush());
        assertEquals(SmbFile2Config.DEFAULT_BUFFER_SIZE, config.getAutoFlushBlockSize());
        assertEquals(SmbFile2Config.DEFAULT_BUFFER_SIZE, config.getBufferSize());
        assertEquals(SmbFile2Config.DEFAULT_CHANNEL_SIZE, config.getChannelReadBufferSize());
        assertEquals(SmbFile2Config.DEFAULT_CHANNEL_SIZE, config.getChannelWriteBufferSize());
        assertEquals(SmbFile2Config.DEFAULT_CONNECT_TIMEOUT, config.getConnectTimeout());
        assertEquals(SmbFile2Config.DEFAULT_READ_TIMEOUT, config.getReadTimeout());
        assertFalse(config.isLocalBackupAble());
        assertFalse(config.isLogDebug());
        assertFalse(config.isUsecaches());
        assertNull(config.getCopyStreamProcessListener());
        assertNull(config.getCopyStreamProcessListenerName());
    }

    @Test
    public void shouldSetAndGetDomain() {
        SmbFile2Config config = new SmbFile2Config();
        config.setDomain("WORKGROUP");
        assertEquals("WORKGROUP", config.getDomain());
    }

    @Test
    public void shouldSetAndGetHost() {
        SmbFile2Config config = new SmbFile2Config();
        config.setHost("192.168.1.1");
        assertEquals("192.168.1.1", config.getHost());
    }

    @Test
    public void shouldSetAndGetUsername() {
        SmbFile2Config config = new SmbFile2Config();
        config.setUsername("admin");
        assertEquals("admin", config.getUsername());
    }

    @Test
    public void shouldSetAndGetPassword() {
        SmbFile2Config config = new SmbFile2Config();
        config.setPassword("secret");
        assertEquals("secret", config.getPassword());
    }

    @Test
    public void shouldSetAndGetSharedDir() {
        SmbFile2Config config = new SmbFile2Config();
        config.setSharedDir("documents");
        assertEquals("documents", config.getSharedDir());
    }

    @Test
    public void shouldSetAndAllowUserInteraction() {
        SmbFile2Config config = new SmbFile2Config();
        config.setAllowUserInteraction(false);
        assertFalse(config.isAllowUserInteraction());
    }

    @Test
    public void shouldSetAndGetAutoFlush() {
        SmbFile2Config config = new SmbFile2Config();
        config.setAutoFlush(true);
        assertTrue(config.isAutoFlush());
    }

    @Test
    public void shouldSetAndGetAutoFlushBlockSize() {
        SmbFile2Config config = new SmbFile2Config();
        config.setAutoFlushBlockSize(1024);
        assertEquals(1024, config.getAutoFlushBlockSize());
    }

    @Test
    public void shouldSetAndGetBufferSize() {
        SmbFile2Config config = new SmbFile2Config();
        config.setBufferSize(4096);
        assertEquals(4096, config.getBufferSize());
    }

    @Test
    public void shouldSetAndGetChannelReadBufferSize() {
        SmbFile2Config config = new SmbFile2Config();
        config.setChannelReadBufferSize(2048);
        assertEquals(2048, config.getChannelReadBufferSize());
    }

    @Test
    public void shouldSetAndGetChannelWriteBufferSize() {
        SmbFile2Config config = new SmbFile2Config();
        config.setChannelWriteBufferSize(2048);
        assertEquals(2048, config.getChannelWriteBufferSize());
    }

    @Test
    public void shouldSetAndGetConnectTimeout() {
        SmbFile2Config config = new SmbFile2Config();
        config.setConnectTimeout(60000);
        assertEquals(60000, config.getConnectTimeout());
    }

    @Test
    public void shouldSetAndGetReadTimeout() {
        SmbFile2Config config = new SmbFile2Config();
        config.setReadTimeout(60000);
        assertEquals(60000, config.getReadTimeout());
    }

    @Test
    public void shouldSetAndGetLocalBackupAble() {
        SmbFile2Config config = new SmbFile2Config();
        config.setLocalBackupAble(true);
        assertTrue(config.isLocalBackupAble());
    }

    @Test
    public void shouldSetAndGetLocalBackupDir() {
        SmbFile2Config config = new SmbFile2Config();
        config.setLocalBackupDir("/tmp/backup");
        assertEquals("/tmp/backup", config.getLocalBackupDir());
    }

    @Test
    public void shouldSetAndGetLogDebug() {
        SmbFile2Config config = new SmbFile2Config();
        config.setLogDebug(true);
        assertTrue(config.isLogDebug());
    }

    @Test
    public void shouldSetAndGetUsecaches() {
        SmbFile2Config config = new SmbFile2Config();
        config.setUsecaches(true);
        assertTrue(config.isUsecaches());
    }

    @Test
    public void shouldSetAndGetCopyStreamProcessListener() {
        SmbFile2Config config = new SmbFile2Config();
        jcifs.io.CopyStreamProcessListener listener = new jcifs.io.PrintCopyStreamProcessListener();
        config.setCopyStreamProcessListener(listener);
        assertNotNull(config.getCopyStreamProcessListener());
    }

    @Test
    public void shouldSetAndGetCopyStreamProcessListenerName() {
        SmbFile2Config config = new SmbFile2Config();
        config.setCopyStreamProcessListenerName("com.example.MyListener");
        assertEquals("com.example.MyListener", config.getCopyStreamProcessListenerName());
    }

    @Test
    public void shouldHaveCorrectDefaultConstants() {
        assertEquals(30000, SmbFile2Config.DEFAULT_CONNECT_TIMEOUT);
        assertEquals(30000, SmbFile2Config.DEFAULT_READ_TIMEOUT);
        assertEquals("anonymous", SmbFile2Config.ANONYMOUS_LOGIN);
        assertEquals(8 * 1024 * 1024, SmbFile2Config.DEFAULT_BUFFER_SIZE);
        assertEquals(2 * 1024 * 1024, SmbFile2Config.DEFAULT_CHANNEL_SIZE);
    }

    @Test
    public void shouldProduceNonEmptyToString() {
        SmbFile2Config config = new SmbFile2Config();
        config.setHost("testhost");
        String str = config.toString();
        assertNotNull(str);
        assertTrue(str.contains("FTPClientConfig"));
    }
}
