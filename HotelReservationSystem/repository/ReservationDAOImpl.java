package repository;

import entity.Reservation;
import interfaces.ReservationDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC implementation of ReservationDAO.
 */
public class ReservationDAOImpl implements ReservationDAO {

    @Override
    public boolean addReservation(Reservation r) {
        String sql = "INSERT INTO reservation (roomId, userId, guestName, checkIn, checkOut) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, r.getRoomId());
            ps.setInt(2, r.getUserId());
            ps.setString(3, r.getGuestName());
            ps.setDate(4, Date.valueOf(r.getCheckIn()));
            ps.setDate(5, Date.valueOf(r.getCheckOut()));

            boolean inserted = ps.executeUpdate() > 0;

            // Mark the room as OCCUPIED upon successful reservation
            if (inserted) {
                RoomDAOImpl roomDAO = new RoomDAOImpl(con);
                roomDAO.updateStatus(r.getRoomId(), "OCCUPIED");
            }
            return inserted;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteReservation(int resId) {
        String sql = "DELETE FROM reservation WHERE resId = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, resId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservation";
        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Reservation r = new Reservation();
                r.setResId(rs.getInt("resId"));
                r.setRoomId(rs.getInt("roomId"));
                r.setUserId(rs.getInt("userId"));
                r.setGuestName(rs.getString("guestName"));
                r.setCheckIn(rs.getDate("checkIn").toLocalDate());
                r.setCheckOut(rs.getDate("checkOut").toLocalDate());
                reservations.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }
}
