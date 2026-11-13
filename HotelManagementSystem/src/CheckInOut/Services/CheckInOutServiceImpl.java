package CheckInOut.Services;

import Booking.Entite.Booking;
import Booking.Enums.BookingStatus;
import Booking.Packages.BookingRepository;
import Rooms.Enums.RoomStatus;
import Rooms.entite.Room;
import Rooms.pakages.RoomRepository;
import java.time.LocalDate;

public class CheckInOutServiceImpl implements CheckInOutService {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository ;
    public CheckInOutServiceImpl(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }
    @Override
    public boolean checkIn(int bookingId) {

       Booking booking =bookingRepository.searchBooking(bookingId);
       if(booking==null){
           return false;
       }
       if (booking.getBookingStatus()!=BookingStatus.CONFIRMED){
           return false;
       }
        if (LocalDate.now().isBefore(booking.getCheckInDate())) {
            return false;
        }
        Room room = roomRepository.searchRoom(booking.getRoomNumber());
        if(room==null){
            return false;
        }

        RoomStatus   roomStatus  =room.getRoomStatus();
        if(roomStatus!= RoomStatus.AVAILABLE){
           return false;
       }
        room.setRoomStatus(RoomStatus.OCCUPIED);
        return true;
    }

    @Override
    public boolean checkOut(int bookingId) {
        Booking booking =bookingRepository.searchBooking(bookingId);
        if(booking==null){
            return false;
        }
        if (booking.getBookingStatus()!=BookingStatus.CONFIRMED){
            return false;
        }
        Room room = roomRepository.searchRoom(booking.getRoomNumber());
        if(room==null){
            return false;
        }
        RoomStatus   roomStatus  =room.getRoomStatus();
        if(roomStatus!= RoomStatus.OCCUPIED){
            return false;
        }
        if (LocalDate.now().isBefore(booking.getCheckInDate())) {
            return false;
        }


        booking.setBookingStatus(BookingStatus.COMPLETED);
        room.setRoomStatus(RoomStatus.CLEANING);
        return true;
    }
}
