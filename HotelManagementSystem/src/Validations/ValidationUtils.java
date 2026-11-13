package Validations;

import java.math.BigDecimal;

/**
 * Provides reusable validation methods for checking input values
 * and enforcing common data constraints across the Hotel Management System.
 */
public class ValidationUtils {

    /**
     * Ensures that the specified value is not null.
     *
     * @param value the object to validate
     * @param fieldName the name of the field being validated
     * @throws IllegalArgumentException if the value is null
     */
    public static void requireNotNull(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null");
        }
    }

    /**
     * Ensures that the specified integer value is greater than zero.
     *
     * @param value the integer value to validate
     * @param fieldName the name of the field being validated
     * @throws IllegalArgumentException if the value is less than or equal to zero
     */
    public static void requirePositive(int value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be a positive integer");
        }
    }

    /**
     * Ensures that the specified string is neither null nor blank.
     *
     * @param value the string to validate
     * @param fieldName the name of the field being validated
     * @throws IllegalArgumentException if the value is null or blank
     */
    public static void requireNotBlank(String value, String fieldName) {

        requireNotNull(value, fieldName);

        if (value.isBlank()) {
            throw new IllegalArgumentException(
                    fieldName + " cannot be blank"
            );
        }
    }

    /**
     * Ensures that the specified BigDecimal value is greater than zero.
     *
     * @param value the decimal value to validate
     * @param fieldName the name of the field being validated
     * @throws IllegalArgumentException if the value is null, zero, or negative
     */
    public static void requirePositive(BigDecimal value, String fieldName) {
        requireNotNull(value, fieldName);

        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(fieldName + " must be positive");
        }
    }

    /**
     * Ensures that the specified BigDecimal value is greater than or equal to zero.
     *
     * @param value the decimal value to validate
     * @param fieldName the name of the field being validated
     * @throws IllegalArgumentException if the value is null or negative
     */
    public static void requireNonNegative(BigDecimal value, String fieldName) {

        requireNotNull(value, fieldName);

        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(fieldName + " must be non-negative");
        }
    }

    /**
     * Ensures that the specified integer value falls within an inclusive range.
     *
     * @param value the integer value to validate
     * @param min the minimum allowed value
     * @param max the maximum allowed value
     * @param fieldName the name of the field being validated
     * @throws IllegalArgumentException if the minimum value is greater than the maximum,
     *                                  or if the value is outside the specified range
     */
    public static void requireInRange(int value, int min, int max, String fieldName) {

        if (min > max) {
            throw new IllegalArgumentException("Invalid range: min cannot be greater than max");
        }

        if (value < min || value > max) {
            throw new IllegalArgumentException(
                    fieldName + " must be between " + min + " and " + max
            );
        }
    }
}