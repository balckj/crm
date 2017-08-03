package com.yidatec.service;

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
    CustomerMapper customerMapper;

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void createFactory(FactoryEntity factory, User user ){

        factoryMapper.create(factory);
        for (int i=0;i<factory.getUserList().size();i++){
            factory.getUserList().get(i).setId(UUID.randomUUID().toString().toLowerCase());
            factory.getUserList().get(i).setCreatorId(user.getId());
            factory.getUserList().get(i).setCreateTime(LocalDateTime.now());
            factory.getUserList().get(i).setModifierId(user.getCreatorId());
            factory.getUserList().get(i).setModifyTime(LocalDateTime.now());
            factoryMapper.createContact(factory.getUserList().get(i));
            factoryMapper.createRelation(factory.getId(),factory.getUserList().get(i).getId());
        }


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

    public List<FactoryEntity> selectFactoryList(FactoryVO factory) {
        return factoryMapper.selectFactoryList(factory);
    }

    public int countFactoryList(FactoryVO factory) {
        return factoryMapper.countFactoryList(factory);
    }

    public FactoryEntity selectFactory(String id){
        FactoryEntity factory = factoryMapper.selectFactory(id);
        factory.setUserList(customerMapper.selectContact(id));
        return factory;
    }
}
