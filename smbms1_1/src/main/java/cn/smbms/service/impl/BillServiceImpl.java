package cn.smbms.service.impl;

import cn.smbms.dao.bill.BillMapper;
import cn.smbms.pojo.Bill;
import cn.smbms.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillMapper billMapper;
    public List<Bill> list() {
        return billMapper.list();
    }

    @Override
    public List<Bill> selectByNameOrPro(String productName, Integer providerId, Integer isPayment) {
        return billMapper.selectByNameOrPro(productName,providerId,isPayment);
    }
}
