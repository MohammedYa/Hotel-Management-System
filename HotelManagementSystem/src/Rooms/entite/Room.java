package Rooms.entite;

import Rooms.Enums.RoomStatus;
import Rooms.Enums.RoomTybe;
import Validations.ValidationUtils;

import java.math.BigDecimal;

/**
 * Represents a hotel room with its number, type, status, price, and capacity.
 * Provides methods to access and update room information.
 */
public class Room {

    private int roomNumber;
    private RoomTybe roomTybe;
    private RoomStatus roomStatus;
    private BigDecimal roomPrice;
    private int roomCapacity;

    /**
     * Constructs a new room with the specified details.
     *
     * @param roomNumber the unique number assigned to the room
     * @param roomTybe the type of the room
     * @param roomStatus the current status of the room
     * @param roomPrice the price per night for the room
     * @param roomCapacity the maximum number of guests the room can accommodate
     */
    public Room(int roomNumber, RoomTybe roomTybe, RoomStatus roomStatus,
                BigDecimal roomPrice, int roomCapacity) {

        setRoomNumber(roomNumber);
        setRoomTybe(roomTybe);
        setRoomStatus(roomStatus);
        setRoomPrice(roomPrice);
        setRoomCapacity(roomCapacity);

    }

    /**
     * Sets the room number.
     *
     * @param roomNumber the room number to assign
     * @throws IllegalArgumentException if the room number is not positive
     */
    public void setRoomNumber(int roomNumber) {

        ValidationUtils.requirePositive(roomNumber, "Room Number");
        this.roomNumber = roomNumber;
    }

    /**
     * Retrieves the room number.
     *
     * @return the room number
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Sets the room type.
     *
     * @param roomTybe the room type to assign
     */
    public void setRoomTybe(RoomTybe roomTybe) {
        this.roomTybe = roomTybe;
    }

    /**
     * Retrieves the room type.
     *
     * @return the room type
     */
    public RoomTybe getRoomTybe() {
        return roomTybe;
    }

    /**
     * Sets the current room status.
     *
     * @param roomStatus the room status to assign
     * @throws IllegalArgumentException if the room status is null
     */
    public void setRoomStatus(RoomStatus roomStatus) {
        ValidationUtils.requireNotNull(roomStatus, "Room Status");
        this.roomStatus = roomStatus;
    }

    /**
     * Retrieves the current room status.
     *
     * @return the room status
     */
    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    /**
     * Sets the price per night for the room.
     *
     * @param roomPrice the room price to assign
     * @throws IllegalArgumentException if the room price is not positive
     */
    public void setRoomPrice(BigDecimal roomPrice) {
        ValidationUtils.requirePositive(roomPrice, "Room Price");
        this.roomPrice = roomPrice;
    }

    /**
     * Retrieves the price per night for the room.
     *
     * @return the room price
     */
    public BigDecimal getRoomPrice() {
        return roomPrice;
    }

    /**
     * Sets the maximum guest capacity of the room.
     *
     * @param roomCapacity the room capacity to assign
     * @throws IllegalArgumentException if the room capacity is not positive
     */
    public void setRoomCapacity(int roomCapacity) {
        ValidationUtils.requirePositive(roomCapacity, "Room Capacity");
        this.roomCapacity = roomCapacity;
    }

    /**
     * Retrieves the maximum guest capacity of the room.
     *
     * @return the room capacity
     */
    public int getRoomCapacity() {
        return roomCapacity;
    }

    /**
     * Compares this room with another object based on the room number.
     *
     * @param obj the object to compare with this room
     * @return true if both objects represent the same room number; otherwise, false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Room other = (Room) obj;
        if (roomNumber != other.roomNumber) {
            return false;
        }
        return true;
    }

    /**
     * Generates a hash code based on the room number.
     *
     * @return the room number as the hash code
     */
    @Override
    public int hashCode() {

        return this.roomNumber;
    }

    /**
     * Returns a formatted string containing the room details.
     *
     * @return a string representation of the room
     */
    @Override
    public String toString() {
        return "========== ROOM DETAILS ==========\n" +
                "Room Number     : " + roomNumber + "\n" +
                "Room Type       : " + roomTybe + "\n" +
                "Room Status     : " + roomStatus + "\n" +
                "Price Per Night : " + roomPrice + "\n" +
                "Room Capacity   : " + roomCapacity + "\n" +
                "==================================";
    }

}