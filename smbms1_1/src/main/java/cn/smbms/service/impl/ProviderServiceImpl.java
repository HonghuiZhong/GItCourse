package cn.smbms.service.impl;

import cn.smbms.dao.provider.ProviderMapper;
import cn.smbms.pojo.Provider;
import cn.smbms.service.ProviderSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProviderServiceImpl  implements ProviderSerivce {

    @Autowired
    public ProviderMapper providerMapper;
    public List<Provider> list() {
        return providerMapper.list();
    }
}
