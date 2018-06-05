package cn.smbms.dao.bill;

import cn.smbms.pojo.Bill;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface BillMapper {
//   查询所有的供应商数据
    List<Bill> list();
    //连表模糊查询账单信息
    List<Bill> selectByNameOrPro(@Param("productName")String productName,@Param("providerId")Integer providerId,@Param("isPayment")Integer isPayment);
}
