package jcifs.smb1.pool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import jcifs.smb1.SmbFile1;
/**
 * Configuration for the SMB1 file connection pool, extending Apache Commons Pool2's
 * {@link GenericObjectPoolConfig}. Includes an additional flag to enable or disable the pool.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 3.0.0
 * @see SmbFilePool
 * @see SmbFilePooledFactory
 */
public class SmbFilePoolConfig extends GenericObjectPoolConfig<SmbFile1> {
	
	/**
	 * If the SMBClient Pool should be enabled or not
	 */
	private boolean enabled = false;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
}
