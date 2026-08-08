package jcifs.io.rename;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * A {@link FileRenamePolicy} that renames files using a random UUID while
 * preserving the original file extension. Optionally supports local backup
 * by copying the renamed file to a specified backup directory.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see FileRenamePolicy
 * @see DateFileRenamePolicy
 */
public class UUIDFileRenamePolicy implements FileRenamePolicy {
	
	protected File backupDir;
	
	public UUIDFileRenamePolicy() {
		super();
	}
	
	public UUIDFileRenamePolicy(File backupDir) {
		super();
		this.backupDir = backupDir;
	}
	
	@Override
	public String rename(String filename) {
		return StringUtils.join(new String[] { UUID.randomUUID().toString(), ".", FilenameUtils.getExtension(filename) });
	}
	
	@Override
	public File rename(File file) {
		String renamedName = this.rename(file.getName());
		// 是否开启本地备份功能
		if (Objects.nonNull(backupDir) && backupDir.exists() && backupDir.canWrite()) {
			try {
				// 尝试本地进行备份（如果已经开启）
				File renameFile = new File(backupDir, renamedName);
				FileUtils.copyFile(file, renameFile);
				return renameFile;
			} catch (IOException e) {
				// 忽略失败的情况
			}
		} else {
			try {
				File renameFile = new File(file.getParentFile(), renamedName);
				FileUtils.copyFile(file, renameFile);
				return renameFile;
			} catch (IOException e) {
				// 忽略失败的情况
			}
		}
		return file;
	}


}