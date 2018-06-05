package cn.smbms.dao.provider;

import cn.smbms.pojo.Provider;

import java.util.List;

public interface ProviderMapper {
        //查询所有的供应商信息
    List<Provider> list();
}
