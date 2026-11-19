package Booking.Services;

import Rooms.entite.Room;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BookingPriceCalculator {

    public BigDecimal calculate(
            Room room,
            LocalDate checkInDate,
            LocalDate checkOutDate) {

        long numberOfNights = ChronoUnit.DAYS.between(
                checkInDate,
                checkOutDate
        );

        return room.getRoomPrice()
                .multiply(BigDecimal.valueOf(numberOfNights));
    }
}

