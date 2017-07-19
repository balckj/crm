package com.yidatec.service;

import com.yidatec.mapper.CustomerMapper;
import com.yidatec.model.Customer;
import com.yidatec.model.Dictionary;
import com.yidatec.vo.CustomerVO;
import com.yidatec.vo.DictionaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/7/19.
 */
@Service("customerService")
public class CustomerService {
    @Autowired
    CustomerMapper customerMapper;


    public Customer selectCustomer(String id){
        return  customerMapper.selectCustomer(id);
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
    public void createCustomer(Customer customer) {
        customerMapper.create(customer);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void updateCustomer(Customer customer) {
        customerMapper.update(customer);
    }

}
