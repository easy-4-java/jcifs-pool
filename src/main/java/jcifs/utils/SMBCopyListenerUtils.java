package jcifs.utils;

import jcifs.io.CopyStreamProcessListener;
import jcifs.smb.SmbFile2;
import jcifs.smb1.SmbFile1;

/**
 * Utility class for initializing copy stream progress listeners on SMB file objects.
 * Sets the filename on the listener before transfer operations begin.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 3.0.0
 * @see CopyStreamProcessListener
 */
public class SMBCopyListenerUtils {

	public static void initCopyListener(SmbFile1 sharedFile,String filename){
		//进度监听
		CopyStreamProcessListener listener = sharedFile.getCopyStreamProcessListener();
		//判断监听存在
		if(listener != null){
	    	listener.setFileName(filename);
	    }
	}
	
	public static void initCopyListener(SmbFile2 sharedFile,String filename){
		//进度监听
		CopyStreamProcessListener listener = sharedFile.getCopyStreamProcessListener();
		//判断监听存在
		if(listener != null){
	    	listener.setFileName(filename);
	    }
	}
	
}
