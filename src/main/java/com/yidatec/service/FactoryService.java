package com.yidatec.service;

import com.yidatec.mapper.CaseMapper;
import com.yidatec.mapper.ContactMapper;
import com.yidatec.mapper.CustomerMapper;
import com.yidatec.mapper.FactoryMapper;
import com.yidatec.model.FactoryEntity;
import com.yidatec.model.User;
import com.yidatec.vo.FactoryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/7/27.
 */
@Service("factoryService")
public class FactoryService {

    @Autowired
    FactoryMapper factoryMapper;

    @Autowired
    ContactMapper contactMapper;

    @Autowired
    CaseMapper caseMapper;

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void createFactory(FactoryEntity factory){

        factoryMapper.create(factory);
        for (int i=0;i<factory.getUserList().size();i++){
            factory.getUserList().get(i).setId(UUID.randomUUID().toString().toLowerCase());
            factory.getUserList().get(i).setCreatorId(factory.getId());
            factory.getUserList().get(i).setCreateTime(LocalDateTime.now());
            factory.getUserList().get(i).setModifierId(factory.getCreatorId());
            factory.getUserList().get(i).setModifyTime(LocalDateTime.now());
            contactMapper.createContact(factory.getUserList().get(i));
            factoryMapper.createRelation(factory.getId(),factory.getUserList().get(i).getId());
        }
        for (int i=0;i<factory.getCaseList().size();i++){
            factory.getCaseList().get(i).setId(UUID.randomUUID().toString().toLowerCase());
            factory.getCaseList().get(i).setType(1);
            caseMapper.createCase(factory.getCaseList().get(i));
            factoryMapper.createCaseRelation(factory.getId(),factory.getCaseList().get(i).getId());
        }

    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void updateFactory(FactoryEntity factory) {
        contactMapper.deleteFactoryContact(factory.getId());
        factoryMapper.deleteFactoryRelation(factory.getId());
        caseMapper.deleteCase(factory.getId());
        factoryMapper.deleteCaseRelation(factory.getId());
        factoryMapper.update(factory);

        for (int i=0;i<factory.getUserList().size();i++){
            factory.getUserList().get(i).setId(UUID.randomUUID().toString().toLowerCase());
            factory.getUserList().get(i).setCreatorId(factory.getId());
            factory.getUserList().get(i).setCreateTime(LocalDateTime.now());
            factory.getUserList().get(i).setModifierId(factory.getCreatorId());
            factory.getUserList().get(i).setModifyTime(LocalDateTime.now());
            contactMapper.createContact(factory.getUserList().get(i));
            factoryMapper.createRelation(factory.getId(),factory.getUserList().get(i).getId());
        }
        for (int i=0;i<factory.getCaseList().size();i++){
            factory.getCaseList().get(i).setId(UUID.randomUUID().toString().toLowerCase());
            factory.getCaseList().get(i).setType(1);
            caseMapper.createCase(factory.getCaseList().get(i));
            factoryMapper.createCaseRelation(factory.getId(),factory.getCaseList().get(i).getId());
        }

    }

    public List<FactoryEntity> selectFactoryList(FactoryVO factory) {
        return factoryMapper.selectFactoryList(factory);
    }

    public int countFactoryList(FactoryVO factory) {
        return factoryMapper.countFactoryList(factory);
    }

    public FactoryEntity selectFactory(String id){
        FactoryVO factoryVO = new FactoryVO();
        FactoryEntity factory = factoryMapper.selectFactory(id);
        if (factory!=null){
            BeanUtils.copyProperties(factory, factoryVO);
            factoryVO.setUserList(factoryMapper.getContact(id));
            factoryVO.setCaseList(caseMapper.getCase(id));
        }
        return factoryVO;
    }
}
