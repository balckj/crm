package com.yidatec.service;

import com.yidatec.mapper.ContactMapper;
import com.yidatec.mapper.CustomerMapper;
import com.yidatec.model.Contact;
import com.yidatec.model.Customer;
import com.yidatec.model.Dictionary;
import com.yidatec.model.User;
import com.yidatec.vo.CustomerVO;
import com.yidatec.vo.ExhibitionVO;
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
 * Created by Administrator on 2017/7/19.
 */
@Service("customerService")
public class CustomerService {
    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    ContactMapper contactMapper;


    public CustomerVO selectCustomer(String id){
        CustomerVO customerVO = new CustomerVO();
        Customer customer = customerMapper.selectCustomer(id);
        if(customer!=null){
            BeanUtils.copyProperties(customer, customerVO);
            customerVO.setContactList(contactMapper.selectContact(id));
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
    public List<Customer> selectCustomerAll(){
        return  customerMapper.selectCustomerAll();
    }

    public int countCustomerList(CustomerVO customerVO) {
        return customerMapper.countCustomerList(customerVO);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void createCustomer(Customer customer){
        customerMapper.create(customer);
        for (int i=0;i<customer.getContactList().size();i++){
            customer.getContactList().get(i).setId(UUID.randomUUID().toString().toLowerCase());
            customer.getContactList().get(i).setCreatorId(customer.getCreatorId());
            customer.getContactList().get(i).setCreateTime(customer.getCreateTime());
            customer.getContactList().get(i).setModifierId(customer.getModifierId());
            customer.getContactList().get(i).setModifyTime(customer.getModifyTime());
            contactMapper.createContact(customer.getContactList().get(i));
            customerMapper.createRelation(customer.getId(),customer.getContactList().get(i).getId());
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void updateCustomer(Customer customer) {
        contactMapper.deleteCustomerContact(customer.getId());
        customerMapper.deleteCustomerRelation(customer.getId());
        customerMapper.update(customer);
        for (int i=0;i<customer.getContactList().size();i++){
            customer.getContactList().get(i).setId(UUID.randomUUID().toString().toLowerCase());
            customer.getContactList().get(i).setCreatorId(customer.getModifierId());
            customer.getContactList().get(i).setCreateTime(customer.getModifyTime());
            customer.getContactList().get(i).setModifierId(customer.getModifierId());
            customer.getContactList().get(i).setModifyTime(customer.getModifyTime());
            contactMapper.createContact(customer.getContactList().get(i));
            customerMapper.createRelation(customer.getId(),customer.getContactList().get(i).getId());
        }
    }

    public List<Contact> getContact(String id){
        return contactMapper.getContact(id);
    }
}
