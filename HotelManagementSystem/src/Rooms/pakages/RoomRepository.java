package Rooms.pakages;

import Rooms.entite.Room;
import Rooms.Enums.RoomStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages the storage and retrieval of hotel rooms.
 * Provides operations for adding, searching, updating, deleting,
 * and changing the status of rooms.
 */
public class RoomRepository {

    private final List<Room> rooms = new ArrayList<>();

    /**
     * Adds a room to the repository if no room with the same room number exists.
     *
     * @param room the room to be added
     * @return {@code true} if the room was successfully added;
     *         {@code false} if a room with the same number already exists
     */
    public boolean addRoom(Room room) {
        if (searchRoom(room.getRoomNumber()) != null) {
            return false;
        }

        return rooms.add(room);
    }

    /**
     * Searches for a room using its room number.
     *
     * @param roomNumber the number of the room to search for
     * @return the matching room if found; {@code null} otherwise
     */
    public Room searchRoom(int roomNumber) {
        return rooms.stream()
                .filter(room -> room.getRoomNumber() == roomNumber)
                .findFirst()
                .orElse(null);
    }

    /**
     * Removes a room from the repository using its room number.
     *
     * @param roomNumber the number of the room to delete
     * @return {@code true} if the room was successfully removed;
     *         {@code false} if the room does not exist
     */
    public boolean deleteRoom(int roomNumber) {
        Room room = searchRoom(roomNumber);

        if (room == null) {
            return false;
        }

        return rooms.remove(room);
    }

    /**
     * Updates the details of an existing room.
     * The room is identified by its room number.
     *
     * @param updatedRoom the room containing the updated information
     * @return {@code true} if the room was successfully updated;
     *         {@code false} if the room does not exist
     */
    public boolean updateRoom(Room updatedRoom) {
        Room existingRoom = searchRoom(updatedRoom.getRoomNumber());

        if (existingRoom == null) {
            return false;
        }

        existingRoom.setRoomTybe(updatedRoom.getRoomTybe());
        existingRoom.setRoomStatus(updatedRoom.getRoomStatus());
        existingRoom.setRoomPrice(updatedRoom.getRoomPrice());
        existingRoom.setRoomCapacity(updatedRoom.getRoomCapacity());

        return true;
    }

    /**
     * Changes the status of an existing room.
     *
     * @param roomNumber the number of the room whose status will be changed
     * @param roomStatus the new status to assign to the room
     * @return {@code true} if the room status was successfully changed;
     *         {@code false} if the room does not exist
     */
    public boolean changeRoomStatus(int roomNumber, RoomStatus roomStatus) {
        Room room = searchRoom(roomNumber);

        if (room == null) {
            return false;
        }

        room.setRoomStatus(roomStatus);
        return true;
    }

    /**
     * Retrieves all rooms stored in the repository.
     *
     * @return an unmodifiable list containing all rooms
     */
    public List<Room> getAllRooms() {
        return Collections.unmodifiableList(rooms);
    }
}