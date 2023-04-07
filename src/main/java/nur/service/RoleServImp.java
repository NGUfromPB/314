package nur.service;

import nur.dao.RoleDao;
import nur.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServImp implements RoleServ{
    private final RoleDao roleDao;
    @Autowired
    public RoleServImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.findAll();
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        roleDao.save(role);
    }

    @Override
    public Set<Role> getRolesById(List<Long> roles) {
        return new HashSet<>(roleDao.findAllById(roles));

    }

}
