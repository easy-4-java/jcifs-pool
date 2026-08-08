package jcifs.utils;

import org.junit.Test;
import java.io.File;
import static org.junit.Assert.*;

public class SMBPathUtilsTest {

    @Test
    public void shouldBuildSharedURLWithCredentials() {
        String url = SMBPathUtils.getSharedURL("user", "pass", "192.168.1.1", "share");
        assertEquals("smb://user:pass@192.168.1.1/share/", url);
    }

    @Test
    public void shouldBuildSharedURLWithEmptyUsername() {
        String url = SMBPathUtils.getSharedURL("", "pass", "192.168.1.1", "share");
        assertEquals("smb://192.168.1.1/share/", url);
    }

    @Test
    public void shouldBuildSharedURLWithNullCredentials() {
        String url = SMBPathUtils.getSharedURL(null, null, "192.168.1.1", "share");
        assertEquals("smb://192.168.1.1/share/", url);
    }

    @Test
    public void shouldBuildSharedURLWithoutFilepath() {
        String url = SMBPathUtils.getSharedURL("user", "pass", "192.168.1.1", "");
        assertEquals("smb://user:pass@192.168.1.1/", url);
    }

    @Test
    public void shouldBuildSharedURLWithNullFilepath() {
        String url = SMBPathUtils.getSharedURL("user", "pass", "192.168.1.1", null);
        assertEquals("smb://user:pass@192.168.1.1/", url);
    }

    @Test
    public void shouldBuildSharedURLHostAndPath() {
        String url = SMBPathUtils.getSharedURL("192.168.1.1", "share/dir");
        assertEquals("smb://192.168.1.1/share/dir/", url);
    }

    @Test
    public void shouldBuildSharedURLHostAndNullPath() {
        String url = SMBPathUtils.getSharedURL("192.168.1.1", null);
        assertEquals("smb://192.168.1.1/", url);
    }

    @Test
    public void shouldBuildSharedURLHostAndEmptyPath() {
        String url = SMBPathUtils.getSharedURL("192.168.1.1", "");
        assertEquals("smb://192.168.1.1/", url);
    }

    @Test
    public void shouldBuildAnonymousSharedURL() {
        String url = SMBPathUtils.getAnonymousSharedURL("192.168.1.1", "share");
        assertEquals("smb://192.168.1.1/share/", url);
    }

    @Test
    public void shouldEnsureTrailingSlashForSharedDir() {
        assertEquals("mydir/", SMBPathUtils.getSharedDir("mydir"));
    }

    @Test
    public void shouldNotDoubleTrailingSlash() {
        assertEquals("mydir/", SMBPathUtils.getSharedDir("mydir/"));
    }

    @Test
    public void shouldExtractEncryptPath() {
        String result = SMBPathUtils.getEncryptPath("/smb/encrypted/path", "/smb/");
        assertEquals("encrypted/path", result);
    }

    @Test
    public void shouldResolvePathRemovingTrailingSlash() {
        String result = SMBPathUtils.getResolvePath("/some/path/");
        assertEquals("/some/path", result);
    }

    @Test
    public void shouldResolvePathNormalizingSlashes() {
        String result = SMBPathUtils.getResolvePath("\\\\server\\share\\");
        assertEquals("/server/share", result);
    }

    @Test
    public void shouldResolvePathDoubleSlashes() {
        String result = SMBPathUtils.getResolvePath("//server//share//");
        assertEquals("/server/share", result);
    }

    @Test
    public void shouldGetPathConvertingBackslashes() throws Exception {
        String result = SMBPathUtils.getPath("path\\to\\file");
        assertEquals("path/to/file", result);
    }

    @Test
    public void shouldGetPathLeavingForwardSlashes() throws Exception {
        String result = SMBPathUtils.getPath("path/to/file");
        assertEquals("path/to/file", result);
    }

    @Test
    public void shouldGetExistDirCreatingIfNecessary() {
        File dir = SMBPathUtils.getExistDir(System.getProperty("java.io.tmpdir") + "/smbtest_" + System.currentTimeMillis());
        assertTrue(dir.exists());
        assertTrue(dir.isDirectory());
        dir.delete();
    }

    @Test
    public void shouldReturnExistingDir() {
        File dir = SMBPathUtils.getExistDir(System.getProperty("java.io.tmpdir"));
        assertTrue(dir.exists());
    }

    @Test
    public void shouldHaveCorrectSlashConstants() {
        assertEquals("\\", SMBPathUtils.SLASHES);
        assertEquals("/", SMBPathUtils.BACKSLASHES);
    }
}
