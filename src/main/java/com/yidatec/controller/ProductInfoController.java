package com.yidatec.controller;

import com.yidatec.mapper.ProductMapper;
import com.yidatec.model.Product;
import com.yidatec.model.User;
import com.yidatec.service.DictionaryService;
import com.yidatec.service.ProductService;
import com.yidatec.util.Constants;
import com.yidatec.vo.ProductVO;
import com.yidatec.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2017/7/10.
 */
@Controller
public class ProductInfoController extends BaseController{

    @Autowired
    DictionaryService dictionaryService;

    @Autowired
    ProductService productService;

    @RequestMapping("/productInfoEdit")
    public String productInfoEdit(ModelMap model, @RequestParam(value="id",required = false) String id){
        model.put("categoryList",dictionaryService.selectDictionaryListByCodeCommon(Constants.PRODUCT_CATEGORY));// 产品分类
        model.put("unitList",dictionaryService.selectDictionaryListByCodeCommon(Constants.COMPANY));// 单位
        model.put("wayList",dictionaryService.selectDictionaryListByCodeCommon(Constants.MODE));// 方式
        model.put("title",(id == null || id.isEmpty())?"新建产品":"编辑产品");
        model.put("pd",productService.selectProduct(id));
        return "productInfoEdit";
    }

    @RequestMapping("/productInfoList")
    public String productInfoList(ModelMap model){
        return "productInfoList";
    }

    @RequestMapping("/saveProductInfo")
    @ResponseBody
    public Object saveProductInfo(@Validated @RequestBody Product product,
                           BindingResult result)throws Exception{
        List<FieldError> errors = result.getFieldErrors();
        if(errors  != null && errors.size() > 0){
            return errors;
        }
        if(product.getId() == null || product.getId().trim().length() <= 0) {//新建
            product.setId(UUID.randomUUID().toString().toLowerCase());
            product.setCreatorId(getWebUser().getId());
            product.setCreateTime(LocalDateTime.now());
            product.setModifierId(getWebUser().getId());
            product.setModifyTime(LocalDateTime.now());
            productService.createProduct(product);
        } else {
            product.setModifierId(getWebUser().getId());
            product.setModifyTime(LocalDateTime.now());
            productService.updateProduct(product);
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/findProductInfo")
    @ResponseBody
    public Object findProductInfo(@RequestBody ProductVO productVO)throws Exception{
        List<Product> productList = productService.selectProductList(productVO);
        int count = productService.countSelectProductList(productVO);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("draw", productVO.getDraw());
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        map.put("data", productList);
        return map;
    }
}
