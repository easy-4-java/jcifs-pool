package jcifs.io;

/**
 * Abstract listener for monitoring data transfer progress during stream copy operations.
 * Extend this class to implement custom progress tracking for file uploads and downloads.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 3.0.0
 * @see PrintCopyStreamProcessListener
 */
public abstract class CopyStreamProcessListener {

	/** The name of the file being transferred. */
	protected String fileName;

	/**
	 * Constructs a new CopyStreamProcessListener with default settings.
	 */
	public CopyStreamProcessListener(){

	}

	/**
	 * Sets the name of the file currently being transferred.
	 *
	 * @param fileName the name of the file being transferred
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	 /**
     * This method is not part of the JavaBeans model and is used by the
     * static methods in the org.apache.commons.io.Util class for efficiency.
     * It is invoked after a block of bytes to inform the listener of the
     * transfer.
     * @param totalBytesTransferred  The total number of bytes transferred
     *         so far by the copy operation.
     * @param bytesTransferred  The number of bytes copied by the most recent
     *          write.
     * @param streamSize The number of bytes in the stream being copied.
     *        This may be equal to CopyStreamEvent.UNKNOWN_STREAM_SIZE if
     *        the size is unknown.
     */
    public abstract void bytesTransferred(long totalBytesTransferred,
                                 int bytesTransferred,
                                 long streamSize);
	
}
