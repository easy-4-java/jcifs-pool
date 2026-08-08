package jcifs.smb1.filter;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class Smb1FilterClassesTest {

    // --- TrueFileFilter ---
    @Test
    public void shouldReturnTrueForTrueFileFilter() throws Exception {
        assertTrue(TrueFileFilter.TRUE.accept(null));
    }

    @Test
    public void shouldReturnTrueForTrueFileFilterWithDir() throws Exception {
        assertTrue(TrueFileFilter.INSTANCE.accept(null, "test"));
    }

    // --- FalseFileFilter ---
    @Test
    public void shouldReturnFalseForFalseFileFilter() throws Exception {
        assertFalse(FalseFileFilter.FALSE.accept(null));
    }

    @Test
    public void shouldReturnFalseForFalseFileFilterWithDir() throws Exception {
        assertFalse(FalseFileFilter.INSTANCE.accept(null, "test"));
    }

    // --- AndFileFilter ---
    @Test
    public void shouldCreateEmptyAndFileFilter() {
        AndFileFilter filter = new AndFileFilter();
        assertNotNull(filter);
        assertTrue(filter.getFileFilters().isEmpty());
    }

    @Test
    public void shouldCreateAndFileFilterWithList() {
        List<Smb1FileFilter> filters = new ArrayList<>();
        filters.add(TrueFileFilter.INSTANCE);
        AndFileFilter filter = new AndFileFilter(filters);
        assertEquals(1, filter.getFileFilters().size());
    }

    @Test
    public void shouldCreateAndFileFilterWithNullList() {
        AndFileFilter filter = new AndFileFilter(null);
        assertTrue(filter.getFileFilters().isEmpty());
    }

    @Test
    public void shouldCreateAndFileFilterWithTwoFilters() {
        AndFileFilter filter = new AndFileFilter(TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
        assertEquals(2, filter.getFileFilters().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingAndFilterWithNullFirstFilter() {
        new AndFileFilter(null, TrueFileFilter.INSTANCE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingAndFilterWithNullSecondFilter() {
        new AndFileFilter(TrueFileFilter.INSTANCE, null);
    }

    @Test
    public void shouldAddFileFilterToAndFilter() {
        AndFileFilter filter = new AndFileFilter();
        filter.addFileFilter(TrueFileFilter.INSTANCE);
        assertEquals(1, filter.getFileFilters().size());
    }

    @Test
    public void shouldRemoveFileFilterFromAndFilter() {
        AndFileFilter filter = new AndFileFilter();
        filter.addFileFilter(TrueFileFilter.INSTANCE);
        assertTrue(filter.removeFileFilter(TrueFileFilter.INSTANCE));
    }

    @Test
    public void shouldSetFileFiltersOnAndFilter() {
        AndFileFilter filter = new AndFileFilter();
        List<Smb1FileFilter> filters = new ArrayList<>();
        filters.add(TrueFileFilter.INSTANCE);
        filter.setFileFilters(filters);
        assertEquals(1, filter.getFileFilters().size());
    }

    @Test
    public void shouldReturnFalseForEmptyAndFilter() throws Exception {
        AndFileFilter filter = new AndFileFilter();
        assertFalse(filter.accept(null));
    }

    @Test
    public void shouldReturnFalseForEmptyAndFilterWithDir() throws Exception {
        AndFileFilter filter = new AndFileFilter();
        assertFalse(filter.accept(null, "test"));
    }

    @Test
    public void shouldHaveNonEmptyToStringForAndFilter() {
        AndFileFilter filter = new AndFileFilter();
        assertNotNull(filter.toString());
    }

    // --- OrFileFilter ---
    @Test
    public void shouldCreateEmptyOrFileFilter() {
        OrFileFilter filter = new OrFileFilter();
        assertNotNull(filter);
    }

    @Test
    public void shouldCreateOrFileFilterWithList() {
        List<Smb1FileFilter> filters = new ArrayList<>();
        filters.add(TrueFileFilter.INSTANCE);
        OrFileFilter filter = new OrFileFilter(filters);
        assertEquals(1, filter.getFileFilters().size());
    }

    @Test
    public void shouldCreateOrFileFilterWithNullList() {
        OrFileFilter filter = new OrFileFilter(null);
        assertTrue(filter.getFileFilters().isEmpty());
    }

    @Test
    public void shouldCreateOrFileFilterWithTwoFilters() {
        OrFileFilter filter = new OrFileFilter(TrueFileFilter.INSTANCE, FalseFileFilter.INSTANCE);
        assertEquals(2, filter.getFileFilters().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingOrFilterWithNullFirstFilter() {
        new OrFileFilter(null, TrueFileFilter.INSTANCE);
    }

    @Test
    public void shouldAddFileFilterToOrFilter() {
        OrFileFilter filter = new OrFileFilter();
        filter.addFileFilter(TrueFileFilter.INSTANCE);
        assertEquals(1, filter.getFileFilters().size());
    }

    @Test
    public void shouldRemoveFileFilterFromOrFilter() {
        OrFileFilter filter = new OrFileFilter();
        filter.addFileFilter(TrueFileFilter.INSTANCE);
        assertTrue(filter.removeFileFilter(TrueFileFilter.INSTANCE));
    }

    @Test
    public void shouldReturnFalseForEmptyOrFilter() throws Exception {
        OrFileFilter filter = new OrFileFilter();
        assertFalse(filter.accept(null));
    }

    // --- NotFileFilter ---
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingNotFilterWithNull() {
        new NotFileFilter(null);
    }

    @Test
    public void shouldHaveNonEmptyToStringForNotFilter() {
        NotFileFilter filter = new NotFileFilter(TrueFileFilter.INSTANCE);
        assertNotNull(filter.toString());
    }

    // --- DelegateFileFilter ---
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingDelegateFilterWithNull() {
        new DelegateFileFilter(null);
    }

    // --- PrefixFileFilter ---
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingPrefixFilterWithNullString() {
        new PrefixFileFilter((String) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingPrefixFilterWithNullArray() {
        new PrefixFileFilter((String[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingPrefixFilterWithNullList() {
        new PrefixFileFilter((List<String>) null);
    }

    @Test
    public void shouldCreatePrefixFilterWithSinglePrefix() {
        PrefixFileFilter filter = new PrefixFileFilter("test");
        assertNotNull(filter);
    }

    @Test
    public void shouldHaveNonEmptyToStringForPrefixFilter() {
        PrefixFileFilter filter = new PrefixFileFilter("test");
        assertNotNull(filter.toString());
    }

    // --- SuffixFileFilter ---
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingSuffixFilterWithNullString() {
        new SuffixFileFilter((String) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingSuffixFilterWithNullArray() {
        new SuffixFileFilter((String[]) null);
    }

    @Test
    public void shouldCreateSuffixFilterWithSingleSuffix() {
        SuffixFileFilter filter = new SuffixFileFilter(".txt");
        assertNotNull(filter);
    }

    @Test
    public void shouldHaveNonEmptyToStringForSuffixFilter() {
        SuffixFileFilter filter = new SuffixFileFilter(".txt");
        assertNotNull(filter.toString());
    }

    // --- NameFileFilter ---
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingNameFilterWithNullString() {
        new NameFileFilter((String) null);
    }

    @Test
    public void shouldCreateNameFilterWithSingleName() {
        NameFileFilter filter = new NameFileFilter("test.txt");
        assertNotNull(filter);
    }

    @Test
    public void shouldHaveNonEmptyToStringForNameFilter() {
        NameFileFilter filter = new NameFileFilter("test.txt");
        assertNotNull(filter.toString());
    }

    // --- SizeFileFilter ---
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingSizeFilterWithNegativeSize() {
        new SizeFileFilter(-1);
    }

    @Test
    public void shouldCreateSizeFilter() {
        SizeFileFilter filter = new SizeFileFilter(100);
        assertNotNull(filter);
    }

    @Test
    public void shouldHaveNonEmptyToStringForSizeFilter() {
        SizeFileFilter filter = new SizeFileFilter(100);
        assertNotNull(filter.toString());
    }

    // --- AgeFileFilter ---
    @Test
    public void shouldCreateAgeFilterWithCutoff() {
        AgeFileFilter filter = new AgeFileFilter(System.currentTimeMillis());
        assertNotNull(filter);
    }

    @Test
    public void shouldHaveNonEmptyToStringForAgeFilter() {
        AgeFileFilter filter = new AgeFileFilter(System.currentTimeMillis());
        assertNotNull(filter.toString());
    }

    // --- MagicNumberFileFilter ---
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingMagicNumberFilterWithNullBytes() {
        new MagicNumberFileFilter((byte[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingMagicNumberFilterWithEmptyBytes() {
        new MagicNumberFileFilter(new byte[0]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingMagicNumberFilterWithNullString() {
        new MagicNumberFileFilter((String) null);
    }

    @Test
    public void shouldCreateMagicNumberFilterWithBytes() {
        MagicNumberFileFilter filter = new MagicNumberFileFilter(new byte[]{0x01});
        assertNotNull(filter);
    }

    @Test
    public void shouldCreateMagicNumberFilterWithString() {
        MagicNumberFileFilter filter = new MagicNumberFileFilter("test");
        assertNotNull(filter);
    }

    @Test
    public void shouldHaveNonEmptyToStringForMagicNumberFilter() {
        MagicNumberFileFilter filter = new MagicNumberFileFilter("test");
        assertNotNull(filter.toString());
    }

    // --- AbstractFileFilter ---
    @Test
    public void shouldHaveNonEmptyToStringForAbstractFileFilter() {
        AbstractFileFilter filter = new AbstractFileFilter() {};
        assertNotNull(filter.toString());
    }
}
