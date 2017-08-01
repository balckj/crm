package com.yidatec.service;

import com.yidatec.mapper.FactoryMapper;
import com.yidatec.model.User;
import com.yidatec.vo.FactoryVO;
import com.yidatec.vo.FactoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by Administrator on 2017/7/27.
 */
@Service("factoryService")
public class FactoryService {

    @Autowired
    FactoryMapper factoryMapper;

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void createFactory(FactoryVO factory, User user ){

        factoryMapper.create(factory);
        factory.getContact().setId(UUID.randomUUID().toString().toLowerCase());
        factory.getContact().setCreatorId(user.getId());
        factory.getContact().setCreateTime(LocalDateTime.now());
        factory.getContact().setModifierId(user.getCreatorId());
        factory.getContact().setModifyTime(LocalDateTime.now());
        factoryMapper.createContact(factory.getContact());
        factoryMapper.createRelation(factory.getId(),factory.getContact().getId());

//        for (int i=0;i<factory.getPhoto().size();i++){
//            factory.getPhoto().get(i).setId(UUID.randomUUID().toString().toLowerCase());
//            factory.getPhoto().get(i).setCreatorId(user.getId());
//            factory.getPhoto().get(i).setCreateTime(LocalDateTime.now());
//            factory.getPhoto().get(i).setModifierId(user.getCreatorId());
//            factory.getPhoto().get(i).setModifyTime(LocalDateTime.now());
//            factoryMapper.savePhoto(factory.getPhoto().get(i));
//        }

    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void updateCustomer(FactoryVO factory,User user ) {
//        factoryMapper.deleteContact(factory.getId());
//        factoryMapper.deleteRelation(factory.getId());
//        factoryMapper.update(factory);
//        for (int i=0;i<factory.getPhoto().size();i++){
//            factory.getPhoto().get(i).setId(UUID.randomUUID().toString().toLowerCase());
//            factory.getPhoto().get(i).setCreatorId(user.getId());
//            factory.getPhoto().get(i).setCreateTime(LocalDateTime.now());
//            factory.getPhoto().get(i).setModifierId(user.getCreatorId());
//            factory.getPhoto().get(i).setModifyTime(LocalDateTime.now());
//            factoryMapper.createContact(factory.getPhoto().get(i));
//            factoryMapper.createRelation(factory.getId(),factory.getPhoto().get(i).getId());
//        }
    }
}
