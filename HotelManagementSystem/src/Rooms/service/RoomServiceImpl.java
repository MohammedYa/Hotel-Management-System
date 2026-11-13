
package Rooms.service;

import Booking.services.BookingService;
import Rooms.Enums.RoomStatus;
import Rooms.entite.Room;
import Rooms.pakages.RoomRepository;
import Validations.ValidationUtils;

import java.util.List;

/**
 * Provides business logic for managing hotel rooms.
 * Handles room creation, searching, deletion, updating,
 * and changing room status.
 *
 * Also coordinates with BookingService when a room is deleted.
 */
public class RoomServiceImpl implements RoomService {

    private final BookingService bookingService;
    private final RoomRepository roomRepository;

    /**
     * Constructs a RoomServiceImpl with the required dependencies.
     *
     * @param roomRepository repository used to manage room data
     * @param bookingService service used to manage related bookings
     */
    public RoomServiceImpl(RoomRepository roomRepository,
                           BookingService bookingService) {
        this.roomRepository = roomRepository;
        this.bookingService = bookingService;
    }

    /**
     * Adds a new room after validating the room object
     * and checking that its room number does not already exist.
     *
     * @param room room to be added
     * @return true if the room was added successfully, otherwise false
     * @throws IllegalArgumentException if room is null or already exists
     */
    @Override
    public boolean addRoom(Room room) {
        ValidationUtils.requireNotNull(room, "Room");

        if (roomRepository.searchRoom(room.getRoomNumber()) != null) {
            throw new IllegalArgumentException("Room already exists");
        }

        return roomRepository.addRoom(room);
    }

    /**
     * Retrieves all rooms from the repository.
     *
     * @return list containing all rooms
     */
    @Override
    public List<Room> getAllRooms() {
        return roomRepository.getAllRooms();
    }

    /**
     * Searches for a room using its room number.
     *
     * @param roomNumber unique number of the room
     * @return the matching room, or null if no room exists
     * @throws IllegalArgumentException if roomNumber is not positive
     */
    @Override
    public Room searchRoom(int roomNumber) {
        ValidationUtils.requirePositive(roomNumber, "Room Number");
        return roomRepository.searchRoom(roomNumber);
    }

    /**
     * Deletes a room after checking that it exists.
     * Related bookings are cancelled before deleting the room.
     *
     * @param roomNumber number of the room to be deleted
     * @return true if the room was deleted successfully, otherwise false
     * @throws IllegalArgumentException if roomNumber is not positive
     */
    @Override
    public boolean deleteRoom(int roomNumber) {
        ValidationUtils.requirePositive(roomNumber, "Room Number");

        if (roomRepository.searchRoom(roomNumber) == null) {
            return false;
        }

        bookingService.cancelBookingsByRoomNumber(roomNumber);

        return roomRepository.deleteRoom(roomNumber);
    }

    /**
     * Updates the information of an existing room.
     *
     * @param room room containing the updated information
     * @return true if the room was updated successfully, otherwise false
     * @throws IllegalArgumentException if room is null
     */
    @Override
    public boolean updateRoom(Room room) {
        ValidationUtils.requireNotNull(room, "Room");

        return roomRepository.updateRoom(room);
    }

    /**
     * Changes the status of a specific room.
     *
     * @param roomNumber number of the room
     * @param roomStatus new status to be assigned to the room
     * @return true if the status was changed successfully, otherwise false
     * @throws IllegalArgumentException if roomNumber is not positive
     *         or roomStatus is null
     */
    @Override
    public boolean changeRoomStatus(int roomNumber, RoomStatus roomStatus) {
        ValidationUtils.requirePositive(roomNumber, "Room Number");
        ValidationUtils.requireNotNull(roomStatus, "Room Status");

        return roomRepository.changeRoomStatus(roomNumber, roomStatus);
    }
}
