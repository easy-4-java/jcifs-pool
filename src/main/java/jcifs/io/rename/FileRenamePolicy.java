package jcifs.io.rename;

import java.io.File;

/**
 * Strategy interface for renaming files. Implementations provide different
 * naming conventions for resolving file name conflicts during file operations.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 3.0.0
 * @see DateFileRenamePolicy
 * @see UUIDFileRenamePolicy
 */
public interface FileRenamePolicy {

	/**
	 * Generates a new filename based on the original filename.
	 *
	 * @param filename the original filename to be renamed
	 * @return the new renamed filename
	 */
	public abstract String rename(String filename);

	/**
	 * Renames the given file and returns the renamed file.
	 *
	 * @param file the original file to be renamed
	 * @return the renamed file
	 */
	public abstract File rename(File file);

}



