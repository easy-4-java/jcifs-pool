package jcifs.smb.filter;

import org.junit.Test;
import java.util.Date;
import static org.junit.Assert.*;

public class Smb2FileFiltersTest {

    @Test
    public void shouldReturnPrefixFileFilter() {
        Smb2FileFilter filter = Smb2FileFilters.prefixFileFilter("test");
        assertNotNull(filter);
        assertTrue(filter instanceof PrefixFileFilter);
    }

    @Test
    public void shouldReturnPrefixFileFilterWithCaseSensitivity() {
        Smb2FileFilter filter = Smb2FileFilters.prefixFileFilter("test", org.apache.commons.io.IOCase.INSENSITIVE);
        assertNotNull(filter);
    }

    @Test
    public void shouldReturnSuffixFileFilter() {
        Smb2FileFilter filter = Smb2FileFilters.suffixFileFilter(".txt");
        assertNotNull(filter);
        assertTrue(filter instanceof SuffixFileFilter);
    }

    @Test
    public void shouldReturnSuffixFileFilterWithCaseSensitivity() {
        Smb2FileFilter filter = Smb2FileFilters.suffixFileFilter(".txt", org.apache.commons.io.IOCase.INSENSITIVE);
        assertNotNull(filter);
    }

    @Test
    public void shouldReturnNameFileFilter() {
        Smb2FileFilter filter = Smb2FileFilters.nameFileFilter("test.txt");
        assertNotNull(filter);
        assertTrue(filter instanceof NameFileFilter);
    }

    @Test
    public void shouldReturnNameFileFilterWithCaseSensitivity() {
        Smb2FileFilter filter = Smb2FileFilters.nameFileFilter("test.txt", org.apache.commons.io.IOCase.INSENSITIVE);
        assertNotNull(filter);
    }

    @Test
    public void shouldReturnDirectoryFileFilter() {
        Smb2FileFilter filter = Smb2FileFilters.directoryFileFilter();
        assertNotNull(filter);
    }

    @Test
    public void shouldReturnFileFileFilter() {
        Smb2FileFilter filter = Smb2FileFilters.fileFileFilter();
        assertNotNull(filter);
        assertTrue(filter instanceof FileFileFilter);
    }

    @Test
    public void shouldReturnAndFilter() {
        Smb2FileFilter filter = Smb2FileFilters.and(TrueFileFilter.INSTANCE, FalseFileFilter.INSTANCE);
        assertNotNull(filter);
        assertTrue(filter instanceof AndFileFilter);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenAndFilterWithNullArray() {
        Smb2FileFilters.and((Smb2FileFilter[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenAndFilterWithNullElement() {
        Smb2FileFilters.and(new Smb2FileFilter[]{null});
    }

    @Test
    public void shouldReturnOrFilter() {
        Smb2FileFilter filter = Smb2FileFilters.or(TrueFileFilter.INSTANCE, FalseFileFilter.INSTANCE);
        assertNotNull(filter);
        assertTrue(filter instanceof OrFileFilter);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenOrFilterWithNullArray() {
        Smb2FileFilters.or((Smb2FileFilter[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenOrFilterWithNullElement() {
        Smb2FileFilters.or(new Smb2FileFilter[]{null});
    }

    @Test
    public void shouldReturnNotFilter() {
        Smb2FileFilter filter = Smb2FileFilters.notFileFilter(TrueFileFilter.INSTANCE);
        assertNotNull(filter);
        assertTrue(filter instanceof NotFileFilter);
    }

    @Test
    public void shouldReturnTrueFilter() {
        Smb2FileFilter filter = Smb2FileFilters.trueFileFilter();
        assertNotNull(filter);
        assertTrue(filter instanceof TrueFileFilter);
    }

    @Test
    public void shouldReturnFalseFilter() {
        Smb2FileFilter filter = Smb2FileFilters.falseFileFilter();
        assertNotNull(filter);
        assertTrue(filter instanceof FalseFileFilter);
    }

    @Test
    public void shouldReturnAsFileFilter() {
        Smb2FileFilter filter = Smb2FileFilters.asFileFilter(TrueFileFilter.INSTANCE);
        assertNotNull(filter);
        assertTrue(filter instanceof DelegateFileFilter);
    }

    @Test
    public void shouldReturnAgeFileFilterWithCutoff() {
        Smb2FileFilter filter = Smb2FileFilters.ageFileFilter(System.currentTimeMillis());
        assertNotNull(filter);
        assertTrue(filter instanceof AgeFileFilter);
    }

    @Test
    public void shouldReturnAgeFileFilterWithCutoffAndAcceptOlder() {
        Smb2FileFilter filter = Smb2FileFilters.ageFileFilter(System.currentTimeMillis(), true);
        assertNotNull(filter);
    }

    @Test
    public void shouldReturnAgeFileFilterWithDate() {
        Smb2FileFilter filter = Smb2FileFilters.ageFileFilter(new Date());
        assertNotNull(filter);
    }

    @Test
    public void shouldReturnAgeFileFilterWithDateAndAcceptOlder() {
        Smb2FileFilter filter = Smb2FileFilters.ageFileFilter(new Date(), true);
        assertNotNull(filter);
    }

    @Test
    public void shouldReturnAgeFileFilterWithFile() throws Exception {
        java.io.File tempFile = java.io.File.createTempFile("test", ".txt");
        try {
            Smb2FileFilter filter = Smb2FileFilters.ageFileFilter(tempFile);
            assertNotNull(filter);
        } finally {
            tempFile.delete();
        }
    }

    @Test
    public void shouldReturnAgeFileFilterWithFileAndAcceptOlder() throws Exception {
        java.io.File tempFile = java.io.File.createTempFile("test", ".txt");
        try {
            Smb2FileFilter filter = Smb2FileFilters.ageFileFilter(tempFile, false);
            assertNotNull(filter);
        } finally {
            tempFile.delete();
        }
    }

    @Test
    public void shouldReturnSizeFileFilter() {
        Smb2FileFilter filter = Smb2FileFilters.sizeFileFilter(100);
        assertNotNull(filter);
        assertTrue(filter instanceof SizeFileFilter);
    }

    @Test
    public void shouldReturnSizeFileFilterWithAcceptLarger() {
        Smb2FileFilter filter = Smb2FileFilters.sizeFileFilter(100, false);
        assertNotNull(filter);
    }

    @Test
    public void shouldReturnSizeRangeFileFilter() {
        Smb2FileFilter filter = Smb2FileFilters.sizeRangeFileFilter(100, 1000);
        assertNotNull(filter);
        assertTrue(filter instanceof AndFileFilter);
    }

    @Test
    public void shouldReturnMagicNumberFileFilterWithString() {
        Smb2FileFilter filter = Smb2FileFilters.magicNumberFileFilter("test");
        assertNotNull(filter);
        assertTrue(filter instanceof MagicNumberFileFilter);
    }

    @Test
    public void shouldReturnMagicNumberFileFilterWithStringAndOffset() {
        Smb2FileFilter filter = Smb2FileFilters.magicNumberFileFilter("test", 10);
        assertNotNull(filter);
    }

    @Test
    public void shouldReturnMagicNumberFileFilterWithBytes() {
        Smb2FileFilter filter = Smb2FileFilters.magicNumberFileFilter(new byte[]{0x01});
        assertNotNull(filter);
    }

    @Test
    public void shouldReturnMagicNumberFileFilterWithBytesAndOffset() {
        Smb2FileFilter filter = Smb2FileFilters.magicNumberFileFilter(new byte[]{0x01}, 5);
        assertNotNull(filter);
    }

    @Test
    public void shouldReturnToList() {
        Smb2FileFilter[] filters = {TrueFileFilter.INSTANCE, FalseFileFilter.INSTANCE};
        java.util.List<Smb2FileFilter> list = Smb2FileFilters.toList(filters);
        assertEquals(2, list.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenToListWithNullArray() {
        Smb2FileFilters.toList(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenToListWithNullElement() {
        Smb2FileFilters.toList(new Smb2FileFilter[]{null});
    }

    @Test
    public void shouldMakeCVSAwareWithNull() {
        Smb2FileFilter filter = Smb2FileFilters.makeCVSAware(null);
        assertNotNull(filter);
    }

    @Test
    public void shouldMakeCVSAwareWithFilter() {
        Smb2FileFilter filter = Smb2FileFilters.makeCVSAware(TrueFileFilter.INSTANCE);
        assertNotNull(filter);
    }

    @Test
    public void shouldMakeSVNAwareWithNull() {
        Smb2FileFilter filter = Smb2FileFilters.makeSVNAware(null);
        assertNotNull(filter);
    }

    @Test
    public void shouldMakeSVNAwareWithFilter() {
        Smb2FileFilter filter = Smb2FileFilters.makeSVNAware(TrueFileFilter.INSTANCE);
        assertNotNull(filter);
    }

    @Test
    public void shouldMakeDirectoryOnlyWithNull() {
        Smb2FileFilter filter = Smb2FileFilters.makeDirectoryOnly(null);
        assertNotNull(filter);
    }

    @Test
    public void shouldMakeDirectoryOnlyWithFilter() {
        Smb2FileFilter filter = Smb2FileFilters.makeDirectoryOnly(TrueFileFilter.INSTANCE);
        assertNotNull(filter);
    }

    @Test
    public void shouldMakeFileOnlyWithNull() {
        Smb2FileFilter filter = Smb2FileFilters.makeFileOnly(null);
        assertNotNull(filter);
    }

    @Test
    public void shouldMakeFileOnlyWithFilter() {
        Smb2FileFilter filter = Smb2FileFilters.makeFileOnly(TrueFileFilter.INSTANCE);
        assertNotNull(filter);
    }

    @Test
    public void shouldHaveNonNullConstants() {
        assertNotNull(Smb2FileFilters.ALL);
        assertNotNull(Smb2FileFilters.NON_NULL);
        assertNotNull(Smb2FileFilters.DIRECTORIES);
    }
}
