package nur.init;

import nur.models.Role;
import nur.models.User;
import nur.service.RoleServImp;
import nur.service.UserServImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Userinit {

    private final RoleServImp roleService;
    private final UserServImpl userService;
    @Autowired
    public Userinit(UserServImpl userService,RoleServImp roleService) {
        this.userService = userService;
        this.roleService=roleService;
    }

    @PostConstruct
    public void init() {
        Role role2 = new Role("ROLE_USER");
        Role role1 = new Role("ROLE_ADMIN");

        roleService.addRole(role1);
        roleService.addRole(role2);

        Set<Role> roleAdmin = new HashSet<>();
        Set<Role> roleUser = new HashSet<>();

        roleAdmin.add(role1);
        roleUser.add(role2);

        User user1 = new User("user@gmail.com", "user", roleUser);
        User user2 = new User("admin@gmail.com", "admin", roleAdmin);
        userService.addUser(user1);
        userService.addUser(user2);
    }
}