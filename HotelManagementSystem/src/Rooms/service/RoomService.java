package Rooms.service;

import Rooms.Enums.RoomStatus;
import Rooms.entite.Room;

import java.util.List;

/**
 * Defines the operations available for managing hotel rooms.
 * Provides methods for adding, retrieving, searching, updating,
 * deleting rooms, and changing their statuses.
 */
public interface RoomService {

    /**
     * Adds a new room to the hotel system.
     *
     * @param room the room to be added
     * @return {@code true} if the room was successfully added;
     *         {@code false} otherwise
     */
    boolean addRoom(Room room);

    /**
     * Retrieves all rooms available in the hotel system.
     *
     * @return a list containing all rooms
     */
    List<Room> getAllRooms();

    /**
     * Searches for a room using its room number.
     *
     * @param roomNumber the number of the room to search for
     * @return the matching room if found; {@code null} otherwise
     */
    Room searchRoom(int roomNumber);

    /**
     * Deletes a room using its room number.
     *
     * @param roomNumber the number of the room to delete
     * @return {@code true} if the room was successfully deleted;
     *         {@code false} otherwise
     */
    boolean deleteRoom(int roomNumber);

    /**
     * Updates the information of an existing room.
     *
     * @param room the room containing the updated information
     * @return {@code true} if the room was successfully updated;
     *         {@code false} otherwise
     */
    boolean updateRoom(Room room);

    /**
     * Changes the status of a specific room.
     *
     * @param roomNumber the number of the room whose status will be changed
     * @param roomStatus the new status to assign to the room
     * @return {@code true} if the status was successfully changed;
     *         {@code false} otherwise
     */
    boolean changeRoomStatus(int roomNumber, RoomStatus roomStatus);
}