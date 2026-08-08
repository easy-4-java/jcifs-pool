package jcifs.smb1.pool;

import org.junit.Test;
import static org.junit.Assert.*;

public class SmbFile1PoolConfigTest {

    @Test
    public void shouldCreateInstance() {
        SmbFilePoolConfig config = new SmbFilePoolConfig();
        assertNotNull(config);
    }

    @Test
    public void shouldHaveDefaultEnabledFalse() {
        SmbFilePoolConfig config = new SmbFilePoolConfig();
        assertFalse(config.isEnabled());
    }

    @Test
    public void shouldSetAndGetEnabled() {
        SmbFilePoolConfig config = new SmbFilePoolConfig();
        config.setEnabled(true);
        assertTrue(config.isEnabled());
    }

    @Test
    public void shouldInheritFromGenericObjectPoolConfig() {
        SmbFilePoolConfig config = new SmbFilePoolConfig();
        assertTrue(config.getMaxTotal() > 0);
    }
}
