package interfaces;

import entity.Reservation;
import java.util.List;

/**
 * DAO contract for Reservation operations.
 */
public interface ReservationDAO {
    boolean addReservation(Reservation reservation);
    boolean deleteReservation(int resId);
    List<Reservation> getAllReservations();
}