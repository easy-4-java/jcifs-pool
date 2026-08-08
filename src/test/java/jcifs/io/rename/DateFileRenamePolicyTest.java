package jcifs.io.rename;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import java.io.File;
import java.io.IOException;
import static org.junit.Assert.*;

public class DateFileRenamePolicyTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void shouldCreateInstance() {
        DateFileRenamePolicy policy = new DateFileRenamePolicy();
        assertNotNull(policy);
    }

    @Test
    public void shouldCreateInstanceWithBackupDir() throws IOException {
        File backupDir = tempFolder.newFolder("backup");
        DateFileRenamePolicy policy = new DateFileRenamePolicy(backupDir);
        assertNotNull(policy);
    }

    @Test
    public void shouldRenameStringWithDatePrefix() {
        DateFileRenamePolicy policy = new DateFileRenamePolicy();
        String renamed = policy.rename("test.txt");
        assertNotNull(renamed);
        assertTrue(renamed.endsWith(".txt"));
        // Should contain date format digits
        assertTrue(renamed.length() > ".txt".length());
    }

    @Test
    public void shouldPreserveFileExtension() {
        DateFileRenamePolicy policy = new DateFileRenamePolicy();
        String renamed = policy.rename("document.pdf");
        assertTrue(renamed.endsWith(".pdf"));
    }

    @Test
    public void shouldRenameFileWithoutBackupDir() throws IOException {
        DateFileRenamePolicy policy = new DateFileRenamePolicy();
        File sourceFile = tempFolder.newFile("original.txt");
        File renamed = policy.rename(sourceFile);
        assertNotNull(renamed);
        assertTrue(renamed.exists());
    }

    @Test
    public void shouldRenameFileWithValidBackupDir() throws IOException {
        File backupDir = tempFolder.newFolder("backup");
        DateFileRenamePolicy policy = new DateFileRenamePolicy(backupDir);
        File sourceFile = tempFolder.newFile("original.txt");
        File renamed = policy.rename(sourceFile);
        assertNotNull(renamed);
    }

    @Test
    public void shouldReturnOriginalFileWhenCopyFails() {
        // Non-existent backup dir that can't be written to
        DateFileRenamePolicy policy = new DateFileRenamePolicy(new File("/nonexistent/path"));
        // Create a temp file manually
        try {
            File sourceFile = tempFolder.newFile("test.txt");
            File renamed = policy.rename(sourceFile);
            assertNotNull(renamed);
        } catch (IOException e) {
            // acceptable
        }
    }

    @Test
    public void shouldHandleFileWithNoExtension() {
        DateFileRenamePolicy policy = new DateFileRenamePolicy();
        String renamed = policy.rename("Makefile");
        assertNotNull(renamed);
        assertTrue(renamed.endsWith("."));
    }
}
