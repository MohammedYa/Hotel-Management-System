package Booking.Enums;

/**
 * Represents the possible statuses of a booking throughout its lifecycle.
 */
public enum BookingStatus {

    /** Indicates that the booking has been confirmed. */
    CONFIRMED,

    /** Indicates that the booking has been cancelled. */
    CANCELLED,

    /** Indicates that the booking has been completed successfully. */
    COMPLETED,

    /** Indicates that the customer did not show up for the booking. */
    NO_SHOW
}