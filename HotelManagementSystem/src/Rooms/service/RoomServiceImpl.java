package Rooms.service;

import Rooms.Enums.RoomStatus;
import Rooms.entite.Room;

import java.util.ArrayList;

public class RoomServiceImpl implements RoomService {
    private ArrayList<Room> rooms = new ArrayList<>();

    @Override
    public void addRoom(Room room) {

        rooms.add(room);
    }

    @Override
    public void displayAllRooms() {
        System.out.print("\nDisplay All Rooms Page\n");

        if (rooms.size()>0){
            rooms.forEach(room -> System.out.print(room));
        }
        else{
            System.out.println("No Rooms Found");
        }


    }

    @Override
    public Room searchRoom(int roomNumber) {
        return rooms.stream()
                .filter(r -> r.getRoomNumber() == roomNumber)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteRoom(int roomNumber) {

        boolean ExistRoom =rooms.removeIf(r->r.getRoomNumber()==roomNumber);
        if(ExistRoom){
            System.out.println("Room Deleted Successfully");
        }
        else {
            System.out.println("Room Not Found");
        }
    }

    @Override
    public void updateRoom(Room room) {
        Room existingRoom = searchRoom(room.getRoomNumber());

        if (existingRoom != null) {
            // Update room properties here

            existingRoom.setRoomTybe(room.getRoomTybe());
            existingRoom.setRoomStatus(room.getRoomStatus());
            existingRoom.setRoomPrice(room.getRoomPrice());
            existingRoom.setRoomCapacity(room.getRoomCapacity());
            System.out.println("Room Updated Successfully");
        } else {
            System.out.println("Room Not Found");
        }
    }

    @Override
    public void changeRoomStatus(int roomNumber, RoomStatus roomStatus) {
        Room existingRoom = searchRoom(roomNumber);
         if(existingRoom!=null){
             existingRoom.setRoomStatus(roomStatus);
             System.out.println("Room Status Updated Successfully");

         }
         else {
            System.out.println("Room Not Found");
         }

    }

}
