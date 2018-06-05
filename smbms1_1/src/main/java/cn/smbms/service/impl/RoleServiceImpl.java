package cn.smbms.service.impl;

import cn.smbms.dao.role.RoleMapper;
import cn.smbms.pojo.Role;
import cn.smbms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    public List<Role> list() {
        return roleMapper.list();
    }
}
