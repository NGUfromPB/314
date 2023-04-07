package nur.service;

import nur.models.User;
import java.util.List;

public interface UserServ {

    List<User> getAllUsers ();
    User findById(Long id);
    void addUser(User user);
    void removeUser(Long id);
    void updateUser(User user);
    User findByUsername(String username);
    User passwordCoder(User user);
}