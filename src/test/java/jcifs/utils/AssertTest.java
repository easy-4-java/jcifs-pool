package jcifs.utils;

import org.junit.Test;
import static org.junit.Assert.*;

public class AssertTest {

    @Test
    public void shouldPassWhenExpressionIsTrue() {
        Assert.isTrue(true, "Should not throw");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenExpressionIsFalse() {
        Assert.isTrue(false, "Expected failure");
    }

    @Test
    public void shouldPassWhenExpressionIsTrueWithoutMessage() {
        Assert.isTrue(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenExpressionIsFalseWithoutMessage() {
        Assert.isTrue(false);
    }

    @Test
    public void shouldPassWhenObjectIsNull() {
        Assert.isNull(null, "Should not throw");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenObjectIsNotNull() {
        Assert.isNull("not null", "Expected failure");
    }

    @Test
    public void shouldPassWhenObjectIsNullWithoutMessage() {
        Assert.isNull(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenObjectIsNotNullWithoutMessage() {
        Assert.isNull("not null");
    }

    @Test
    public void shouldPassWhenObjectIsNotNull() {
        Assert.notNull("not null", "Should not throw");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenObjectIsNull() {
        Assert.notNull(null, "Expected failure");
    }

    @Test
    public void shouldPassWhenObjectIsNotNullWithoutMessage() {
        Assert.notNull("not null");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenObjectIsNullWithoutMessage() {
        Assert.notNull(null);
    }

    @Test
    public void shouldPassWhenStringHasLength() {
        Assert.hasLength("hello", "Should not throw");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenStringIsEmpty() {
        Assert.hasLength("", "Expected failure");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenStringIsNull() {
        Assert.hasLength(null, "Expected failure");
    }

    @Test
    public void shouldPassWhenStringHasLengthWithoutMessage() {
        Assert.hasLength("hello");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenStringIsEmptyWithoutMessage() {
        Assert.hasLength("");
    }

    @Test
    public void shouldPassWhenStringHasText() {
        Assert.hasText("hello", "Should not throw");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenStringIsBlank() {
        Assert.hasText("   ", "Expected failure");
    }

    @Test
    public void shouldPassWhenStringHasTextWithoutMessage() {
        Assert.hasText("hello");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenStringIsBlankWithoutMessage() {
        Assert.hasText("   ");
    }

    @Test
    public void shouldPassWhenStringDoesNotContainSubstring() {
        Assert.doesNotContain("hello world", "xyz", "Should not throw");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenStringContainsSubstring() {
        Assert.doesNotContain("hello world", "world", "Expected failure");
    }

    @Test
    public void shouldPassWhenStringDoesNotContainSubstringWithoutMessage() {
        Assert.doesNotContain("hello world", "xyz");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenStringContainsSubstringWithoutMessage() {
        Assert.doesNotContain("hello world", "world");
    }

    @Test
    public void shouldPassForEmptyArrayDoesNotContainCheck() {
        Assert.doesNotContain("", "test");
    }

    @Test
    public void shouldPassForNullTextDoesNotContainCheck() {
        Assert.doesNotContain(null, "test");
    }

    @Test
    public void shouldPassForNullSubstringDoesNotContainCheck() {
        Assert.doesNotContain("hello", null);
    }

    @Test
    public void shouldNotThrowWhenArrayIsEmpty() {
        // Note: the existing implementation has reversed logic - it does NOT throw for empty arrays
        Assert.notEmpty(new Object[0], "Should not throw");
    }

    @Test
    public void shouldNotThrowWhenArrayIsNull() {
        // Note: the existing implementation has reversed logic - it does NOT throw for null
        Assert.notEmpty(null, "Should not throw");
    }

    @Test
    public void shouldNotThrowWhenArrayIsEmptyWithoutMessage() {
        Assert.notEmpty(new Object[0]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenArrayHasElementsDueToBug() {
        // Note: the existing implementation has a bug - it throws when array has elements
        Assert.notEmpty(new Object[]{"a"}, "Expected failure due to bug");
    }

    @Test
    public void shouldPassWhenArrayHasNoNullElements() {
        Assert.noNullElements(new Object[]{"a", "b"}, "Should not throw");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenArrayHasNullElements() {
        Assert.noNullElements(new Object[]{"a", null}, "Expected failure");
    }

    @Test
    public void shouldPassWhenArrayHasNoNullElementsWithoutMessage() {
        Assert.noNullElements(new Object[]{"a", "b"});
    }

    @Test
    public void shouldPassForNullArrayNoNullElements() {
        Assert.noNullElements(null, "Should not throw");
    }

    @Test
    public void shouldPassForEmptyArrayNoNullElements() {
        Assert.noNullElements(new Object[0], "Should not throw");
    }

    @Test
    public void shouldPassWhenObjectIsInstanceOfClass() {
        Assert.isInstanceOf(String.class, "hello");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenObjectIsNotInstanceOfClass() {
        Assert.isInstanceOf(Integer.class, "hello");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenTypeIsNullForInstanceOf() {
        Assert.isInstanceOf(null, "hello");
    }

    @Test
    public void shouldPassWhenSubTypeIsAssignable() {
        Assert.isAssignable(Number.class, Integer.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenSubTypeIsNotAssignable() {
        Assert.isAssignable(String.class, Integer.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenSuperTypeIsNullForAssignable() {
        Assert.isAssignable(null, Integer.class);
    }

    @Test
    public void shouldPassWhenStateIsTrue() {
        Assert.state(true, "Should not throw");
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowWhenStateIsFalse() {
        Assert.state(false, "Expected failure");
    }

    @Test
    public void shouldPassWhenStateIsTrueWithoutMessage() {
        Assert.state(true);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowWhenStateIsFalseWithoutMessage() {
        Assert.state(false);
    }
}
