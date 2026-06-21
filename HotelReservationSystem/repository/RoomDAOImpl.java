package repository;

import interfaces.RoomDAO;
import entity.Room;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {

    private Connection con;

    // Constructor with connection (preferred)
    public RoomDAOImpl(Connection con) {
        this.con = con;
    }

    // Fallback default constructor (uses DatabaseConnection)
    public RoomDAOImpl() {
        try {
            this.con = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addRoom(Room room) {
        String sql = "INSERT INTO room (roomId, type, price, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, room.getRoomId());
            ps.setString(2, room.getType());
            ps.setDouble(3, room.getPrice());
            ps.setString(4, room.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateRoom(Room room) {
        String sql = "UPDATE room SET type=?, price=?, status=? WHERE roomId=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, room.getType());
            ps.setDouble(2, room.getPrice());
            ps.setString(3, room.getStatus());
            ps.setInt(4, room.getRoomId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateStatus(int roomId, String status) {
        String sql = "UPDATE room SET status=? WHERE roomId=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, roomId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteRoom(int roomId) {
        String sql = "DELETE FROM room WHERE roomId=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, roomId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM room";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Room r = new Room();
                r.setRoomId(rs.getInt("roomId"));
                r.setType(rs.getString("type"));
                r.setPrice(rs.getDouble("price"));
                r.setStatus(rs.getString("status"));
                rooms.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public List<Room> getAvailableRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM room WHERE status='AVAILABLE'";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Room r = new Room();
                r.setRoomId(rs.getInt("roomId"));
                r.setType(rs.getString("type"));
                r.setPrice(rs.getDouble("price"));
                r.setStatus(rs.getString("status"));
                rooms.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }
}
