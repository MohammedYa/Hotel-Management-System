
package Rooms.service;

import Rooms.Enums.RoomStatus;
import Rooms.entite.Room;

import java.util.List;

public interface RoomService {

    boolean addRoom(Room room);

    List<Room> getAllRooms();

    Room searchRoom(int roomNumber);

    boolean deleteRoom(int roomNumber);

    boolean updateRoom(Room room);

    boolean changeRoomStatus(int roomNumber, RoomStatus roomStatus);
}
