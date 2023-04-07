package nur.service;

import nur.dao.UserDao;
import nur.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServImpl implements UserServ{
    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    public UserServImpl (UserDao userDao,PasswordEncoder  passwordEncoder) {
        this.userDao=userDao;
        this.passwordEncoder=passwordEncoder;
    }
    @Override
    public User passwordCoder(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }
    @Override
    public List<User> getAllUsers() {
        return  userDao.findAll();
    }

    @Override
    public User findById(Long id) {
        User user=null;
        Optional<User> optional = userDao.findById(id);
        if(optional.isPresent()) {
            user= optional.get();
        }
        return user;
    }
    @Override
    @Transactional
    public void addUser(User user) {
        userDao.save(passwordCoder(user));
    }

    @Override
    @Transactional
    public void removeUser(Long id) {
        userDao.deleteById(id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

}
