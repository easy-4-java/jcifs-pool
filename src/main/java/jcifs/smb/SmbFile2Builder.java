package jcifs.smb;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.Builder;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jcifs.context.BaseContext;
import jcifs.io.CopyStreamProcessListener;
import jcifs.utils.SMBPathUtils;

/**
 *  SmbFile对象构建器
 * @author [@Loong Wan](https://github.com/loong10k)
 */
public class SmbFile2Builder implements Builder<SmbFile2> {
	
	protected static Logger LOG = LoggerFactory.getLogger(SmbFile2Builder.class);
	private BaseContext context;
	private SmbFile2Config clientConfig = new SmbFile2Config();
	 
	@SuppressWarnings("unchecked")
	public SmbFile2 build() {
		
		//基于smb协议的共享文件访问对象
		SmbFile2 smbClient = null;
		
		try {
			
			//共享目录访问路径（用户名密码内嵌在URL中）
			String sharedURL = SMBPathUtils.getSharedURL(clientConfig.getUsername(), clientConfig.getPassword(), clientConfig.getHost(), clientConfig.getSharedDir());
			//创建基于smb协议的共享文件访问对象
			smbClient = new SmbFile2(sharedURL);
			//启用或禁用用户交互（例如弹出一个验证对话框）的上下文中对此 URL 进行检查
			smbClient.setAllowUserInteraction(clientConfig.isAllowUserInteraction());
			//设置一个指定的超时值（以毫秒为单位），该值将在打开到此 URLConnection 引用的资源的通信链接时使用
			smbClient.setConnectTimeout(clientConfig.getConnectTimeout());
			//数据读取超时时间，以毫秒为单位
			smbClient.setReadTimeout(clientConfig.getReadTimeout());
			//启用或禁用在条件允许情况下允许协议使用缓存
			smbClient.setDefaultUseCaches(clientConfig.isUsecaches());
			smbClient.setUseCaches(clientConfig.isUsecaches());
			
			smbClient.setAutoFlush(clientConfig.isAutoFlush());
			smbClient.setAutoFlushBlockSize(clientConfig.getAutoFlushBlockSize());
			smbClient.setBufferSize(clientConfig.getBufferSize());
			smbClient.setChannelReadBufferSize(clientConfig.getChannelReadBufferSize());
			smbClient.setChannelWriteBufferSize(clientConfig.getChannelWriteBufferSize());
			smbClient.setLogDebug(clientConfig.isLogDebug());
			
			//进行存储时/检索操作时数据处理进度监听对象
			if(StringUtils.isNotEmpty(clientConfig.getCopyStreamProcessListenerName())){
				Class<CopyStreamProcessListener> listenerClazz = (Class<CopyStreamProcessListener>) Class.forName(clientConfig.getCopyStreamProcessListenerName());
				smbClient.setCopyStreamProcessListener((CopyStreamProcessListener) ConstructorUtils.invokeConstructor(listenerClazz));
			}else{
				smbClient.setCopyStreamProcessListener(clientConfig.getCopyStreamProcessListener());
			}
		} catch (Exception e) {
			LOG.error(ExceptionUtils.getStackTrace(e));
		}
		
		return smbClient;
	}

	public void shutdown() throws IOException {

	}

	public SmbFile2Config getConfiguration() {
		return clientConfig;
	}
	
}
