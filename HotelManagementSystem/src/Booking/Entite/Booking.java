
package Booking.Entite;

import Booking.Enums.BookingStatus;
import Validations.ValidationUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents a hotel booking.
 *
 * Stores booking information including customer, room,
 * stay dates, booking status, and total price.
 */
public class Booking {

    private int bookingId;
    private int customerId;
    private int roomNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BookingStatus bookingStatus;
    private BigDecimal totalPrice;

    /**
     * Constructs a Booking with the specified information.
     *
     * @param bookingId unique booking identifier
     * @param customerId ID of the customer
     * @param roomNumber number of the booked room
     * @param checkInDate beginning of the stay
     * @param checkOutDate end of the stay
     * @param bookingStatus current booking status
     * @param totalPrice total booking price
     * @throws IllegalArgumentException if any field violates validation rules
     */
    public Booking(int bookingId, int customerId, int roomNumber,
                   LocalDate checkInDate, LocalDate checkOutDate,
                   BookingStatus bookingStatus, BigDecimal totalPrice) {

        setBookingId(bookingId);
        setCustomerId(customerId);
        setRoomNumber(roomNumber);
        setCheckInDate(checkInDate);
        setCheckOutDate(checkOutDate);
        setBookingStatus(bookingStatus);
        setTotalPrice(totalPrice);
    }

    /**
     * Gets the booking ID.
     *
     * @return booking ID
     */
    public int getBookingId() {
        return bookingId;
    }

    /**
     * Sets the booking ID.
     *
     * @param bookingId unique booking identifier
     * @throws IllegalArgumentException if bookingId is not positive
     */
    public void setBookingId(int bookingId) {
        ValidationUtils.requirePositive(bookingId, "Booking ID");
        this.bookingId = bookingId;
    }

    /**
     * Gets the customer ID.
     *
     * @return customer ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID.
     *
     * @param customerId customer identifier
     * @throws IllegalArgumentException if customerId is not positive
     */
    public void setCustomerId(int customerId) {
        ValidationUtils.requirePositive(customerId, "Customer ID");
        this.customerId = customerId;
    }

    /**
     * Gets the room number.
     *
     * @return room number
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Sets the room number.
     *
     * @param roomNumber room identifier
     * @throws IllegalArgumentException if roomNumber is not positive
     */
    public void setRoomNumber(int roomNumber) {
        ValidationUtils.requirePositive(roomNumber, "Room Number");
        this.roomNumber = roomNumber;
    }

    /**
     * Gets the check-in date.
     *
     * @return check-in date
     */
    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    /**
     * Sets the check-in date.
     *
     * @param checkInDate beginning of the stay
     * @throws IllegalArgumentException if checkInDate is null
     *         or is not before the current check-out date
     */
    public void setCheckInDate(LocalDate checkInDate) {
        ValidationUtils.requireNotNull(checkInDate, "Check-in Date");

        if (checkOutDate != null && !checkOutDate.isAfter(checkInDate)) {
            throw new IllegalArgumentException(
                    "Check-out Date must be after Check-in Date"
            );
        }

        this.checkInDate = checkInDate;
    }

    /**
     * Gets the check-out date.
     *
     * @return check-out date
     */
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * Sets the check-out date.
     *
     * @param checkOutDate end of the stay
     * @throws IllegalArgumentException if checkOutDate is null
     *         or is not after the current check-in date
     */
    public void setCheckOutDate(LocalDate checkOutDate) {
        ValidationUtils.requireNotNull(checkOutDate, "Check-out Date");

        if (checkInDate != null && !checkOutDate.isAfter(checkInDate)) {
            throw new IllegalArgumentException(
                    "Check-out Date must be after Check-in Date"
            );
        }

        this.checkOutDate = checkOutDate;
    }

    /**
     * Gets the booking status.
     *
     * @return booking status
     */
    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    /**
     * Sets the booking status.
     *
     * @param bookingStatus current booking status
     * @throws IllegalArgumentException if bookingStatus is null
     */
    public void setBookingStatus(BookingStatus bookingStatus) {
        ValidationUtils.requireNotNull(bookingStatus, "Booking Status");
        this.bookingStatus = bookingStatus;
    }

    /**
     * Gets the total booking price.
     *
     * @return total booking price
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the total booking price.
     *
     * @param totalPrice total price of the booking
     * @throws IllegalArgumentException if totalPrice is null or not positive
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        ValidationUtils.requirePositive(totalPrice, "Total Price");
        this.totalPrice = totalPrice;
    }

    /**
     * Compares this booking with another object based on booking ID.
     *
     * @param obj object to compare
     * @return true if both objects represent the same booking
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Booking other = (Booking) obj;
        return bookingId == other.bookingId;
    }

    /**
     * Generates a hash code based on booking ID.
     *
     * @return hash code of this booking
     */
    @Override
    public int hashCode() {
        return this.bookingId;
    }

    /**
     * Returns a formatted representation of booking details.
     *
     * @return formatted booking information
     */
    @Override
    public String toString() {
        return "========== Booking DETAILS ==========\n" +
                "Booking Number : " + this.bookingId + "\n" +
                "Room Number    : " + this.roomNumber + "\n" +
                "Customer ID    : " + this.customerId + "\n" +
                "Check In Date  : " + this.checkInDate + "\n" +
                "Check Out Date : " + this.checkOutDate + "\n" +
                "Booking Status : " + this.bookingStatus + "\n" +
                "Booking Costs  : " + this.totalPrice + "\n" +
                "==================================";
    }
}
