package nur.service;

import nur.models.Role;

import java.util.List;
import java.util.Set;

public interface RoleServ {
    void addRole(Role role);
    Set<Role> getRolesById(List<Long> roles);
    List<Role> getAllRoles();

}
