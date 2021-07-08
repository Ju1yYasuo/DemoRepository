package com.example.demo.modules.test.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.modules.test.entity.Luoxtest;
import com.example.demo.modules.test.mapper.LuoxtestMapper;
import com.example.demo.modules.test.service.LuoxtestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luox
 * @since 2021-06-15
 */
@Service
public class LuoxtestServiceImpl extends ServiceImpl<LuoxtestMapper, Luoxtest> implements LuoxtestService {

    @Autowired
    private LuoxtestMapper luoxtestMapper;

    @Override
    public List<Luoxtest> getAllLuoxtest() {
        return luoxtestMapper.selectList(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveLuoxtest(Luoxtest luoxtest) {
        save(luoxtest);
        return 1l;
//        return luoxtestMapper.insertLuoxtest(luoxtest);
    }

    @Override
    public String getNameById(Long id) {
        return luoxtestMapper.getNameById(id);
    }

    @Override
    public List<Luoxtest> getLuoxtestByName(Page page,String name) {
        IPage<Luoxtest> iPage = luoxtestMapper.getLuoxtestByName(page,name);
        return iPage.getRecords();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateLuoxtestNameById(Long id, String name) throws Exception {
        Luoxtest luoxtestUpdate = new Luoxtest();
        luoxtestUpdate.setId(id);
        luoxtestUpdate.setName(name);
        Boolean result = saveOrUpdate(luoxtestUpdate);
        return result;
    }




}
