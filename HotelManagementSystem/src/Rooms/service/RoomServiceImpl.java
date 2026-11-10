
package Rooms.service;

import Rooms.Enums.RoomStatus;
import Rooms.entite.Room;
import Rooms.pakages.RoomRepository;

import java.util.List;

public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
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
