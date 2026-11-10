package Booking.Entite;

import Booking.Enums.BookingStatus;


import java.time.LocalDate;

public class Booking {

private  int bookingId;
    private int customerId;
    private int roomNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BookingStatus bookingStatus;

    public Booking(int bookingId, int customerId, int roomNumber, LocalDate checkInDate, LocalDate checkOutDate,BookingStatus bookingStatus) {
        setBookingId(bookingId);
        setCustomerId(customerId);
        setRoomNumber(roomNumber);
        setCheckInDate(checkInDate);
        setCheckOutDate(checkOutDate);
        setBookingStatus(bookingStatus);


    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }


    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Booking other = (Booking) obj;
        if (bookingId != other.bookingId)
            return false;
       return true;
    }

    @Override
    public int hashCode() {
        return this.bookingId;
    }

    @Override
    public String toString() {
        return "========== Booking DETAILS ==========\n" +
                "Booking Number : " +this.bookingId  + "\n" +
                "Room Number    : " + this.roomNumber + "\n" +
                "Customer ID  : " + this.customerId + "\n" +
                "Check In Date  : " + this.checkInDate + "\n" +
                "Check Out Date : " + this.checkOutDate + "\n" +
                "Booking Status : " + this.bookingStatus + "\n" +
                "==================================";
    }

}
