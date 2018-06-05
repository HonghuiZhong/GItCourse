package cn.smbms.service;

import cn.smbms.pojo.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillService {
    List<Bill> list();
    List<Bill> selectByNameOrPro(String productName, Integer providerId, Integer isPayment);
}
