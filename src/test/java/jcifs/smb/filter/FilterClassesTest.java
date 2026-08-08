package jcifs.smb.filter;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class FilterClassesTest {

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
        List<Smb2FileFilter> filters = new ArrayList<>();
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
        assertTrue(filter.getFileFilters().isEmpty());
    }

    @Test
    public void shouldSetFileFiltersOnAndFilter() {
        AndFileFilter filter = new AndFileFilter();
        List<Smb2FileFilter> filters = new ArrayList<>();
        filters.add(TrueFileFilter.INSTANCE);
        filters.add(FalseFileFilter.INSTANCE);
        filter.setFileFilters(filters);
        assertEquals(2, filter.getFileFilters().size());
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
        assertTrue(filter.getFileFilters().isEmpty());
    }

    @Test
    public void shouldCreateOrFileFilterWithList() {
        List<Smb2FileFilter> filters = new ArrayList<>();
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

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingOrFilterWithNullSecondFilter() {
        new OrFileFilter(TrueFileFilter.INSTANCE, null);
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
        assertTrue(filter.getFileFilters().isEmpty());
    }

    @Test
    public void shouldSetFileFiltersOnOrFilter() {
        OrFileFilter filter = new OrFileFilter();
        List<Smb2FileFilter> filters = new ArrayList<>();
        filters.add(TrueFileFilter.INSTANCE);
        filter.setFileFilters(filters);
        assertEquals(1, filter.getFileFilters().size());
    }

    @Test
    public void shouldReturnFalseForEmptyOrFilter() throws Exception {
        OrFileFilter filter = new OrFileFilter();
        assertFalse(filter.accept(null));
    }

    @Test
    public void shouldHaveNonEmptyToStringForOrFilter() {
        OrFileFilter filter = new OrFileFilter();
        assertNotNull(filter.toString());
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

    @Test
    public void shouldHaveNonEmptyToStringForDelegateFilter() {
        DelegateFileFilter filter = new DelegateFileFilter(new org.codelibs.jcifs.smb.impl.SmbFileFilter() {
            @Override
            public boolean accept(org.codelibs.jcifs.smb.impl.SmbFile file) {
                return true;
            }
        });
        assertNotNull(filter.toString());
    }

    // --- PrefixFileFilter ---
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingPrefixFilterWithNullString() {
        new PrefixFileFilter((String) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingPrefixFilterWithNullStringArray() {
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
    public void shouldCreatePrefixFilterWithArray() {
        PrefixFileFilter filter = new PrefixFileFilter(new String[]{"test", "demo"});
        assertNotNull(filter);
    }

    @Test
    public void shouldCreatePrefixFilterWithList() {
        List<String> prefixes = new ArrayList<>();
        prefixes.add("test");
        PrefixFileFilter filter = new PrefixFileFilter(prefixes);
        assertNotNull(filter);
    }

    @Test
    public void shouldHaveNonEmptyToStringForPrefixFilter() {
        PrefixFileFilter filter = new PrefixFileFilter("test");
        assertNotNull(filter.toString());
        assertTrue(filter.toString().contains("test"));
    }

    @Test
    public void shouldHaveNonEmptyToStringForPrefixFilterWithArray() {
        PrefixFileFilter filter = new PrefixFileFilter(new String[]{"a", "b"});
        assertNotNull(filter.toString());
    }

    // --- SuffixFileFilter ---
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingSuffixFilterWithNullString() {
        new SuffixFileFilter((String) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingSuffixFilterWithNullStringArray() {
        new SuffixFileFilter((String[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingSuffixFilterWithNullList() {
        new SuffixFileFilter((List<String>) null);
    }

    @Test
    public void shouldCreateSuffixFilterWithSingleSuffix() {
        SuffixFileFilter filter = new SuffixFileFilter(".txt");
        assertNotNull(filter);
    }

    @Test
    public void shouldCreateSuffixFilterWithArray() {
        SuffixFileFilter filter = new SuffixFileFilter(new String[]{".txt", ".pdf"});
        assertNotNull(filter);
    }

    @Test
    public void shouldCreateSuffixFilterWithList() {
        List<String> suffixes = new ArrayList<>();
        suffixes.add(".txt");
        SuffixFileFilter filter = new SuffixFileFilter(suffixes);
        assertNotNull(filter);
    }

    @Test
    public void shouldHaveNonEmptyToStringForSuffixFilter() {
        SuffixFileFilter filter = new SuffixFileFilter(".txt");
        assertNotNull(filter.toString());
        assertTrue(filter.toString().contains(".txt"));
    }

    // --- NameFileFilter ---
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingNameFilterWithNullString() {
        new NameFileFilter((String) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingNameFilterWithNullStringArray() {
        new NameFileFilter((String[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingNameFilterWithNullList() {
        new NameFileFilter((List<String>) null);
    }

    @Test
    public void shouldCreateNameFilterWithSingleName() {
        NameFileFilter filter = new NameFileFilter("test.txt");
        assertNotNull(filter);
    }

    @Test
    public void shouldCreateNameFilterWithArray() {
        NameFileFilter filter = new NameFileFilter(new String[]{"test.txt", "demo.txt"});
        assertNotNull(filter);
    }

    @Test
    public void shouldCreateNameFilterWithList() {
        List<String> names = new ArrayList<>();
        names.add("test.txt");
        NameFileFilter filter = new NameFileFilter(names);
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
    public void shouldCreateSizeFilterWithDefaultAcceptLarger() {
        SizeFileFilter filter = new SizeFileFilter(100);
        assertNotNull(filter);
    }

    @Test
    public void shouldCreateSizeFilterWithAcceptLarger() {
        SizeFileFilter filter = new SizeFileFilter(100, true);
        assertNotNull(filter);
    }

    @Test
    public void shouldCreateSizeFilterWithAcceptSmaller() {
        SizeFileFilter filter = new SizeFileFilter(100, false);
        assertNotNull(filter);
    }

    @Test
    public void shouldHaveNonEmptyToStringForSizeFilter() {
        SizeFileFilter filter = new SizeFileFilter(100);
        assertNotNull(filter.toString());
        assertTrue(filter.toString().contains("100"));
    }

    // --- AgeFileFilter ---
    @Test
    public void shouldCreateAgeFilterWithCutoff() {
        AgeFileFilter filter = new AgeFileFilter(System.currentTimeMillis());
        assertNotNull(filter);
    }

    @Test
    public void shouldCreateAgeFilterWithCutoffAndAcceptOlder() {
        AgeFileFilter filter = new AgeFileFilter(System.currentTimeMillis(), true);
        assertNotNull(filter);
    }

    @Test
    public void shouldCreateAgeFilterWithCutoffAndAcceptNewer() {
        AgeFileFilter filter = new AgeFileFilter(System.currentTimeMillis(), false);
        assertNotNull(filter);
    }

    @Test
    public void shouldCreateAgeFilterWithDate() {
        AgeFileFilter filter = new AgeFileFilter(new java.util.Date());
        assertNotNull(filter);
    }

    @Test
    public void shouldCreateAgeFilterWithDateAndAcceptOlder() {
        AgeFileFilter filter = new AgeFileFilter(new java.util.Date(), true);
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

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingMagicNumberFilterWithEmptyString() {
        new MagicNumberFileFilter("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingMagicNumberFilterWithNegativeOffset() {
        new MagicNumberFileFilter(new byte[]{0x01}, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenCreatingMagicNumberFilterWithStringAndNegativeOffset() {
        new MagicNumberFileFilter("test", -1);
    }

    @Test
    public void shouldCreateMagicNumberFilterWithBytes() {
        MagicNumberFileFilter filter = new MagicNumberFileFilter(new byte[]{(byte) 0xCA, (byte) 0xFE});
        assertNotNull(filter);
    }

    @Test
    public void shouldCreateMagicNumberFilterWithString() {
        MagicNumberFileFilter filter = new MagicNumberFileFilter("<?xml");
        assertNotNull(filter);
    }

    @Test
    public void shouldCreateMagicNumberFilterWithBytesAndOffset() {
        MagicNumberFileFilter filter = new MagicNumberFileFilter(new byte[]{0x01, 0x02}, 10);
        assertNotNull(filter);
    }

    @Test
    public void shouldCreateMagicNumberFilterWithStringAndOffset() {
        MagicNumberFileFilter filter = new MagicNumberFileFilter("ustar", 257);
        assertNotNull(filter);
    }

    @Test
    public void shouldHaveNonEmptyToStringForMagicNumberFilter() {
        MagicNumberFileFilter filter = new MagicNumberFileFilter("test");
        assertNotNull(filter.toString());
    }

    // --- AbstractFileFilter toString ---
    @Test
    public void shouldHaveNonEmptyToStringForAbstractFileFilter() {
        AbstractFileFilter filter = new AbstractFileFilter() {};
        assertNotNull(filter.toString());
    }
}
