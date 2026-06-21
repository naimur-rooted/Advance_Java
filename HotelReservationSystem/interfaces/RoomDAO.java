package interfaces;

import entity.Room;
import java.util.List;

/**
 * DAO contract for Room operations.
 */
public interface RoomDAO {
    boolean addRoom(Room room);
    boolean updateRoom(Room room);
    boolean updateStatus(int roomId, String status);
    boolean deleteRoom(int roomId);
    List<Room> getAllRooms();
    List<Room> getAvailableRooms();
}