package entity;

import java.time.LocalDate;

/**
 * Entity representing a reservation/booking.
 */
public class Reservation {
    private int resId;
    private int roomId;
    private int userId;
    private String guestName;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Reservation() {}

    public Reservation(int resId, int roomId, int userId, String guestName,
                       LocalDate checkIn, LocalDate checkOut) {
        this.resId = resId;
        this.roomId = roomId;
        this.userId = userId;
        this.guestName = guestName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    // Getters & Setters
    public int getResId() { return resId; }
    public void setResId(int resId) { this.resId = resId; }

    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getGuestName() { return guestName; }
    public void setGuestName(String guestName) { this.guestName = guestName; }

    public LocalDate getCheckIn() { return checkIn; }
    public void setCheckIn(LocalDate checkIn) { this.checkIn = checkIn; }

    public LocalDate getCheckOut() { return checkOut; }
    public void setCheckOut(LocalDate checkOut) { this.checkOut = checkOut; }
}