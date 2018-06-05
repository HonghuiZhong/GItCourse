package cn.smbms.dao.user;

import cn.smbms.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 调用xml文件的增删该查方法
 */
public interface UserMapper {

    User getUserByUserCode(@Param("userCode") String userCode);

    int count(@Param("userCode") String userCode,@Param("roleId")Integer roleId);

    List<User> list(@Param("userCode") String userCode,@Param("roleId")Integer roleId,@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);

    User getUserById(@Param("id") Integer id);

    void save(User user);

    void modify(User user);

    void deleteById(@Param("id") Integer id);

    User getUserByName(@Param("userName")String userName);
}
