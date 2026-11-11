package CheckInOut.Services;

public interface CheckInOutService {

    boolean checkIn(int bookingId);

    boolean checkOut(int bookingId);

}