package jcifs.io;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A concrete implementation of {@link CopyStreamProcessListener} that logs file upload/download
 * progress information using SLF4J, including bytes transferred and percentage completion.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see CopyStreamProcessListener
 */
public class PrintCopyStreamProcessListener extends CopyStreamProcessListener {

	protected static Logger LOG = LoggerFactory.getLogger(PrintCopyStreamProcessListener.class);
	protected BigDecimal hundred  = new BigDecimal(100);
	
	@Override
	public void bytesTransferred(long totalBytesTransferred,int bytesTransferred, long streamSize) {
		LOG.info("此次拷贝：" + bytesTransferred + "字节,已拷贝:" + totalBytesTransferred + " 字节.");
		//-1表示不知道总大小
		if(streamSize != -1){
			//已完成百分比
			float percentum = new BigDecimal(totalBytesTransferred).divide(new BigDecimal(streamSize),10, BigDecimal.ROUND_HALF_UP).multiply(hundred).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
			LOG.info("文件【 " + fileName + "】 上传/下载 进度：" + percentum + "%");
		}
	}

}
