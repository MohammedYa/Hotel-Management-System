package Rooms.entite;

import Rooms.Enums.RoomStatus;
import Rooms.Enums.RoomTybe;

public class Room {
    private int roomNumber;
    private RoomTybe roomTybe;
    private RoomStatus roomStatus;
    private double roomPrice;
    private int roomCapacity;
   public Room(int roomNumber,RoomTybe roomTybe,RoomStatus roomStatus,double roomPrice,int roomCapacity) {

       setRoomNumber(roomNumber);
       setRoomTybe(roomTybe);
       setRoomStatus(roomStatus);
       setRoomPrice(roomPrice);
       setRoomCapacity(roomCapacity);

    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomTybe(RoomTybe roomTybe) {
       this.roomTybe = roomTybe;
    }
    public RoomTybe getRoomTybe() {
       return roomTybe;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
       this.roomStatus = roomStatus;
    }
    public RoomStatus getRoomStatus() {
       return roomStatus;
    }

    public void setRoomPrice(double roomPrice) {
       this.roomPrice = roomPrice;
    }
    public double getRoomPrice() {
       return roomPrice;
    }

    public void setRoomCapacity(int roomCapacity) {
       this.roomCapacity = roomCapacity;
    }
    public int getRoomCapacity() {
       return roomCapacity;
    }


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

    @Override
    public int hashCode() {

        return  this.roomNumber;
    }

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

