package Rooms.service;

import Rooms.Enums.RoomStatus;
import Rooms.entite.Room;

public interface RoomService {
    void addRoom(Room room);
    void displayAllRooms();
    Room searchRoom(int roomNumber);
    void deleteRoom(int roomNumber);
    void updateRoom(Room room);
    void changeRoomStatus(int roomNumber, RoomStatus roomStatus);

}
