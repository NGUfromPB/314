package nur.Controller;

import nur.models.User;
import nur.service.UserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api")
public class AdminController {

    private final UserServ userServ;

    @Autowired
    public AdminController(UserServ userServ) {
        this.userServ = userServ;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userServ.getAllUsers(),HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        userServ.addUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> pageDelete(@PathVariable("id") Long id) {
        userServ.removeUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUser (@PathVariable("id") Long id) {
        User user = userServ.findById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByUsername (Principal principal) {
        User user = userServ.findByUsername(principal.getName());
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> pageEdit(@PathVariable("id") Long id,
                                      @Valid @RequestBody User user) {
        String oldPassword = userServ.findById(id).getPassword();
        if (oldPassword.equals(user.getPassword())) {
            System.out.println("TRUE");
            user.setPassword(oldPassword);
            userServ.updateUser(user);
        } else {
            System.out.println("FALSE");
            userServ.addUser(user);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
