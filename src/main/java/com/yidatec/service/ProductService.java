package com.yidatec.service;

import com.yidatec.mapper.ProductMapper;
import com.yidatec.model.Param;
import com.yidatec.model.Product;
import com.yidatec.model.User;
import com.yidatec.util.Constants;
import com.yidatec.vo.ProductVO;
import com.yidatec.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("productService")
public class ProductService {

    @Autowired
    ProductMapper productMapper;

    public Product selectProduct(String id){
        return  productMapper.selectProduct(id);
    }

    /**
     * 查询产品list
     *
     * @return
     */
    public List<Product> selectProductList(ProductVO product) {
        return  productMapper.selectProductList(product);
    }

    /**
     * 查询产品
     *
     * @return
     */
    public int countSelectProductList(ProductVO product) {
        return productMapper.countSelectProductList(product);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void deleteProduct(String id) {
        productMapper.delete(id);
    }


    /**
     * 创建一个产品
     *
     * @param product
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void createProduct(Product product) {
        productMapper.create(product);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void updateProduct(Product product) {
        productMapper.update(product);
    }
}
