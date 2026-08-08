package jcifs.smb1.pool;

import org.apache.commons.pool2.impl.GenericObjectPool;

import jcifs.smb1.SmbFile1;


/**
 * SMB1 client connection pool backed by Apache Commons Pool2. Manages a pool of
 * reusable SMB1 file connections to improve performance and resource utilization.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see SmbFilePooledFactory
 * @see SmbFilePoolConfig
 */
public class SmbFilePool extends GenericObjectPool<SmbFile1> {


	/**
	 * Constructs a new SMB1 file pool with the specified factory and configuration.
	 *
	 * @param factory the pooled object factory for creating and managing SMB1 instances
	 * @param config  the pool configuration settings
	 */
	public SmbFilePool(SmbFilePooledFactory factory, SmbFilePoolConfig config){
		super(factory, config);
	}

}