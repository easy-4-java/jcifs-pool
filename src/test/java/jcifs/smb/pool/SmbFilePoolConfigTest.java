package jcifs.smb.pool;

import org.junit.Test;
import static org.junit.Assert.*;

public class SmbFilePoolConfigTest {

    @Test
    public void shouldCreateInstance() {
        SmbFilePoolConfig config = new SmbFilePoolConfig();
        assertNotNull(config);
    }

    @Test
    public void shouldInheritFromGenericObjectPoolConfig() {
        SmbFilePoolConfig config = new SmbFilePoolConfig();
        // GenericObjectPoolConfig has default values
        assertTrue(config.getMaxTotal() > 0);
        assertTrue(config.getMaxIdle() > 0);
    }

    @Test
    public void shouldAllowSettingMaxTotal() {
        SmbFilePoolConfig config = new SmbFilePoolConfig();
        config.setMaxTotal(50);
        assertEquals(50, config.getMaxTotal());
    }

    @Test
    public void shouldAllowSettingMaxIdle() {
        SmbFilePoolConfig config = new SmbFilePoolConfig();
        config.setMaxIdle(20);
        assertEquals(20, config.getMaxIdle());
    }

    @Test
    public void shouldAllowSettingMinIdle() {
        SmbFilePoolConfig config = new SmbFilePoolConfig();
        config.setMinIdle(5);
        assertEquals(5, config.getMinIdle());
    }
}
