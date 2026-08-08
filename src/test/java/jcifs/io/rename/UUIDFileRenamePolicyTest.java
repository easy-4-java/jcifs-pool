package jcifs.io.rename;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;

public class UUIDFileRenamePolicyTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void shouldCreateInstance() {
        UUIDFileRenamePolicy policy = new UUIDFileRenamePolicy();
        assertNotNull(policy);
    }

    @Test
    public void shouldCreateInstanceWithBackupDir() throws IOException {
        File backupDir = tempFolder.newFolder("backup");
        UUIDFileRenamePolicy policy = new UUIDFileRenamePolicy(backupDir);
        assertNotNull(policy);
    }

    @Test
    public void shouldRenameStringWithUUID() {
        UUIDFileRenamePolicy policy = new UUIDFileRenamePolicy();
        String renamed = policy.rename("test.txt");
        assertNotNull(renamed);
        assertTrue(renamed.endsWith(".txt"));
        // UUID format: 8-4-4-4-12 hex chars
        String nameWithoutExt = renamed.substring(0, renamed.length() - 4);
        assertTrue(nameWithoutExt.contains("-"));
        assertEquals(36, nameWithoutExt.length());
    }

    @Test
    public void shouldPreserveFileExtension() {
        UUIDFileRenamePolicy policy = new UUIDFileRenamePolicy();
        String renamed = policy.rename("document.pdf");
        assertTrue(renamed.endsWith(".pdf"));
    }

    @Test
    public void shouldGenerateUniqueNames() {
        UUIDFileRenamePolicy policy = new UUIDFileRenamePolicy();
        Set<String> names = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            names.add(policy.rename("test.txt"));
        }
        assertEquals(100, names.size());
    }

    @Test
    public void shouldRenameFileWithoutBackupDir() throws IOException {
        UUIDFileRenamePolicy policy = new UUIDFileRenamePolicy();
        File sourceFile = tempFolder.newFile("original.txt");
        File renamed = policy.rename(sourceFile);
        assertNotNull(renamed);
        assertTrue(renamed.exists());
    }

    @Test
    public void shouldRenameFileWithValidBackupDir() throws IOException {
        File backupDir = tempFolder.newFolder("backup");
        UUIDFileRenamePolicy policy = new UUIDFileRenamePolicy(backupDir);
        File sourceFile = tempFolder.newFile("original.txt");
        File renamed = policy.rename(sourceFile);
        assertNotNull(renamed);
    }

    @Test
    public void shouldHandleFileWithNoExtension() {
        UUIDFileRenamePolicy policy = new UUIDFileRenamePolicy();
        String renamed = policy.rename("Makefile");
        assertNotNull(renamed);
        assertTrue(renamed.endsWith("."));
    }
}
