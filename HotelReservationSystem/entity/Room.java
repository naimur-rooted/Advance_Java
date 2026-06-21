package entity;

/**
 * Entity representing a hotel room.
 */
public class Room {
    private int roomId;
    private String type;
    private double price;
    private String status;  // AVAILABLE, OCCUPIED, MAINTENANCE

    public Room() {}

    public Room(int roomId, String type, double price, String status) {
        this.roomId = roomId;
        this.type = type;
        this.price = price;
        this.status = status;
    }

    // Getters & Setters
    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        // Used in combo boxes during reservation
        return "Room " + roomId + " - " + type + " (₱" + price + ")";
    }
}