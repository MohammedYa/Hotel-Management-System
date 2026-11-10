package Rooms.pakages;



import Rooms.entite.Room;
import Rooms.Enums.RoomStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoomRepository {

    private final List<Room> rooms = new ArrayList<>();

    public boolean addRoom(Room room) {
        if (searchRoom(room.getRoomNumber()) != null) {
            return false;
        }

        return rooms.add(room);
    }

    public Room searchRoom(int roomNumber) {
        return rooms.stream()
                .filter(room -> room.getRoomNumber() == roomNumber)
                .findFirst()
                .orElse(null);
    }

    public boolean deleteRoom(int roomNumber) {
        Room room = searchRoom(roomNumber);

        if (room == null) {
            return false;
        }

        return rooms.remove(room);
    }

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

    public boolean changeRoomStatus(int roomNumber, RoomStatus roomStatus) {
        Room room = searchRoom(roomNumber);

        if (room == null) {
            return false;
        }

        room.setRoomStatus(roomStatus);
        return true;
    }

    public List<Room> getAllRooms() {
        return Collections.unmodifiableList(rooms);
    }
}

