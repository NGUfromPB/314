package nur.Controller;

import nur.models.User;
import nur.service.UserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;


@RestController
@RequestMapping("/api")
@RolesAllowed({"ADMIN","USER"})
public class UserController {
    private final UserServ userServ;

    @Autowired
    public UserController(UserServ userServ) {
        this.userServ = userServ;
    }
    @GetMapping("/user")
    public ResponseEntity<User> getUserByUsername (Principal principal) {
        User user = userServ.findByUsername(principal.getName());
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

}
