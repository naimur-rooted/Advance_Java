package interfaces;

import entity.User;
import java.util.List;

/**
 * DAO contract for User operations.
 */
public interface UserDAO {
    User authenticate(String username, String password);
    boolean addUser(User user);
    boolean deleteUser(int userId);
    List<User> getAllUsers();

    // New method for guest registration
    boolean registerGuest(String username, String password);
}
