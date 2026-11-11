
package Rooms.service;

import Booking.services.BookingService;
import Rooms.Enums.RoomStatus;
import Rooms.entite.Room;
import Rooms.pakages.RoomRepository;

import java.util.List;

public class RoomServiceImpl implements RoomService {
    private final BookingService bookingService;
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository, BookingService bookingService) {
        this.roomRepository = roomRepository;
        this.bookingService = bookingService;
    }

    @Override
    public boolean addRoom(Room room) {
        return roomRepository.addRoom(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.getAllRooms();
    }

    @Override
    public Room searchRoom(int roomNumber) {
        return roomRepository.searchRoom(roomNumber);
    }

    @Override
    public boolean deleteRoom(int roomNumber) {
        if(roomRepository.searchRoom(roomNumber)==null){
            return false;

        }
        bookingService.cancelBookingsByRoomNumber(roomNumber);
        return roomRepository.deleteRoom(roomNumber);
    }

    @Override
    public boolean updateRoom(Room room) {
        return roomRepository.updateRoom(room);
    }

    @Override
    public boolean changeRoomStatus(int roomNumber, RoomStatus roomStatus) {
        return roomRepository.changeRoomStatus(roomNumber, roomStatus);
    }
}
