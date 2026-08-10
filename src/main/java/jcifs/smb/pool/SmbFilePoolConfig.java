package jcifs.smb.pool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import org.codelibs.jcifs.smb.impl.SmbFile;

/**
 * Configuration for the SMB file connection pool, extending Apache Commons Pool2's
 * {@link GenericObjectPoolConfig}. Use this to tune pool parameters such as
 * max total connections, max idle, min idle, and eviction settings.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 3.0.0
 * @see SmbFilePool
 * @see SmbFilePooledFactory
 */
public class SmbFilePoolConfig extends GenericObjectPoolConfig<SmbFile> {
	
}
