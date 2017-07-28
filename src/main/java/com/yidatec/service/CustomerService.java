package com.yidatec.service;

import com.yidatec.mapper.CustomerMapper;
import com.yidatec.model.Contact;
import com.yidatec.model.Customer;
import com.yidatec.model.Dictionary;
import com.yidatec.model.User;
import com.yidatec.vo.CustomerVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/7/19.
 */
@Service("customerService")
public class CustomerService {
    @Autowired
    CustomerMapper customerMapper;


    public CustomerVO selectCustomer(String id){
        CustomerVO customerVO = new CustomerVO();
        Customer customer = customerMapper.selectCustomer(id);
        if(customer!=null){
            BeanUtils.copyProperties(customer, customerVO);
            customerVO.setUserList(customerMapper.selectContact(id));
        }
        return customerVO;
    }
    /**
     * 查询客户数据
     *
     * @return
     */
    public List<Customer> selectCustomerList(CustomerVO customerVO) {
        return customerMapper.selectCustomerList(customerVO);
    }

    public int countDictionaryList(CustomerVO customerVO) {
        return customerMapper.countCustomerList(customerVO);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void createCustomer(CustomerVO customer1, User user ){

        customerMapper.create(customer1);
        for (int i=0;i<customer1.getUserList().size();i++){
            customer1.getUserList().get(i).setId(UUID.randomUUID().toString().toLowerCase());
            customer1.getUserList().get(i).setCreatorId(user.getId());
            customer1.getUserList().get(i).setCreateTime(LocalDateTime.now());
            customer1.getUserList().get(i).setModifierId(user.getCreatorId());
            customer1.getUserList().get(i).setModifyTime(LocalDateTime.now());
            customerMapper.createContact(customer1.getUserList().get(i));
            customerMapper.createRelation(customer1.getId(),customer1.getUserList().get(i).getId());
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void updateCustomer(CustomerVO customer,User user ) {
        customerMapper.deleteContact(customer.getId());
        customerMapper.deleteRelation(customer.getId());
        customerMapper.update(customer);
        for (int i=0;i<customer.getUserList().size();i++){
            customer.getUserList().get(i).setId(UUID.randomUUID().toString().toLowerCase());
            customer.getUserList().get(i).setCreatorId(user.getId());
            customer.getUserList().get(i).setCreateTime(LocalDateTime.now());
            customer.getUserList().get(i).setModifierId(user.getCreatorId());
            customer.getUserList().get(i).setModifyTime(LocalDateTime.now());
            customerMapper.createContact(customer.getUserList().get(i));
            customerMapper.createRelation(customer.getId(),customer.getUserList().get(i).getId());
        }
    }

    public List<Dictionary> getCompanyNature(String id) {
       return customerMapper.getCompanyNature(id);
    }

    public List<Contact> getContact(String id){
        return customerMapper.getContact(id);
    }
}
