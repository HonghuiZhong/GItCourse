package cn.smbms.service;

import cn.smbms.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
   /*返回User对象,跟数据库沟通*/
   User login(String userCode,String userPassword);
//   动态查询数量
   int count( String userCode, Integer roleId);
   /**
    *
    * @param userCode 用户名
    * @param roleId 用户id
    * @param pageNo 页码(当前页)
    * @param pageSize 页面容量(页面大小)
    * @return
    */

   List<User> list(String userCode,Integer roleId,Integer pageNo,Integer pageSize);

  User getUserById(Integer id);

   User getUserByUserCode(String userCode);
//用户添加
    void save(User user);

    void modify(User user);

    void deleteById(Integer id);

    User getUserByName(String username);
}
