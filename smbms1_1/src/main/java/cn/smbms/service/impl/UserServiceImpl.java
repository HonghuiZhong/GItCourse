package cn.smbms.service.impl;

import cn.smbms.dao.user.UserMapper;
import cn.smbms.pojo.User;
import cn.smbms.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

//    需要userMapper调用方法()
            @Resource
        private  UserMapper userMapper;

    /**
     * 用户登录实现业务
     * @param userCode
     * @param userPassword
     * @return
     */
    public User login(String userCode, String userPassword) {
        User user=userMapper.getUserByUserCode(userCode);//根据用户名获取用户信息
        //判断密码是否正确
        if (user!=null&&user.getUserPassword().equals(userPassword)){
            return user;
        }
        return null;
    }



    public int count(String userCode, Integer roleId) {


        return userMapper.count(userCode,roleId);
    }

    public List<User> list(String userCode, Integer roleId, Integer pageNo, Integer pageSize) {


        return userMapper.list(userCode,roleId,pageNo, pageSize);
    }

    public User getUserById(Integer id) {

        return userMapper.getUserById(id);
    }

    public User getUserByUserCode(String userCode) {
        return userMapper.getUserByUserCode(userCode);
    }

    public void save(User user) {
        userMapper.save(user);
    }

    public void modify(User user) {
        userMapper.modify(user);
    }

    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    public User getUserByName(String username) {
        return userMapper.getUserByName(username);
    }


}
