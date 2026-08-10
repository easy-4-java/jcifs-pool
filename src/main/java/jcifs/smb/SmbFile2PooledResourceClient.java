package jcifs.smb;

import jcifs.smb.pool.SmbFilePool;

/**
 * SMB2 resource client implementation backed by an Apache Commons Pool2 connection pool.
 * Manages SMB2 client lifecycle by borrowing from and returning to the pool.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see SmbFile2ResourceClient
 * @see jcifs.smb.pool.SmbFilePool
 */
public class SmbFile2PooledResourceClient extends SmbFile2ResourceClient{
	
	private SmbFilePool clientPool = null;
	private SmbFile2Config clientConfig = null;
	
	public SmbFile2PooledResourceClient(SmbFilePool clientPool, SmbFile2Config clientConfig){
		 this.clientPool = clientPool;
		 this.clientConfig = clientConfig;
	} 
	
	public SmbFile2PooledResourceClient(){
		 
	}
	 
	@Override
	public SmbFile2 getSMBClient() throws Exception {
		//从对象池获取SMBClient对象
		return new SmbFile2(clientPool.borrowObject().getURL().toString());
	}
 
	@Override
	public void releaseClient(SmbFile2 smbClient) throws Exception{
		
		try {
			//释放SMBClient到对象池
			if(smbClient !=null){
				clientPool.returnObject(smbClient);
			}
		} catch (Throwable e) {
			 
		}
		
	}
	
	public SmbFile2Config getClientConfig() {
		return clientConfig;
	}
	
}
