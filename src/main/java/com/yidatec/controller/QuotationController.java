package com.yidatec.controller;

import com.yidatec.model.Dictionary;
import com.yidatec.model.Product;
import com.yidatec.service.DictionaryService;
import com.yidatec.service.ProductService;
import com.yidatec.service.ProjectService;
import com.yidatec.service.QuotationService;
import com.yidatec.util.DownloadHelper;
import com.yidatec.vo.ProductVO;
import com.yidatec.vo.QuotationVO;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Administrator on 2017/7/13.
 */
@Controller
public class QuotationController extends BaseController{

    @Autowired
    ProductService productService;

    @Autowired
    QuotationService quotationService;

    @Autowired
    ProjectService projectService;

    @Autowired
    DictionaryService dictionaryService;



    @RequestMapping("/quotationList")
    public  String quotationList(){
        return  "quotationList";
    }

    @RequestMapping("/quotationEdit")
    public String quotationEdit(ModelMap model, @RequestParam(value="id",required = false) String id){
        model.put("title",(id == null || id.isEmpty())?"新建报价单":"编辑报价单");
        model.put("quotation",quotationService.selectQuotation(id));
        return "quotationEdit";
    }

    @RequestMapping("/saveQuotation")
    @ResponseBody
    public Object saveQuotation(@Validated @RequestBody QuotationVO quotation,
                              BindingResult result)throws Exception{
        List<FieldError> errors = result.getFieldErrors();
        if(errors  != null && errors.size() > 0){
            return errors;
        }
        if(quotation.getId() == null || quotation.getId().trim().length() <= 0)//新建
        {
            quotation.setId(UUID.randomUUID().toString());
            quotation.setCreatorId(getWebUser().getId());
            quotation.setCreateTime(LocalDateTime.now());
            quotation.setModifierId(quotation.getCreatorId());
            quotation.setModifyTime(quotation.getCreateTime());
            quotationService.createQuotation(quotation);

        } else {//编辑
            quotation.setModifierId(getWebUser().getCreatorId());
            quotation.setModifyTime(LocalDateTime.now());
            quotationService.updateQuotation(quotation);
        }
        return getSuccessJson(null);
    }

    @RequestMapping("/findProduct")
    @ResponseBody
    public Object findProduct(@RequestBody ProductVO productVO){
        List<Product> ProductList = productService.selectProductList(productVO);
        int count = productService.countSelectProductList(productVO);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("draw", productVO.getDraw());
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        map.put("data", ProductList);
        return map;
    }

    @RequestMapping(value = "/findQuotation")
    @ResponseBody
    public Object findQuotation(@RequestBody QuotationVO quotationVO)throws Exception{
        List<QuotationVO> QuotationList = quotationService.selectQuotationList(quotationVO);
        int count = quotationService.countQuotationList(quotationVO);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("draw", quotationVO.getDraw());
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        map.put("data", QuotationList);
        return map;
    }

    @RequestMapping("/getProduction")
    @ResponseBody
    public Object getProduction(@RequestParam(value="id",required = false) String id)throws Exception{
        QuotationVO quotation = quotationService.selectQuotation(id);
        if(quotation!=null){
            for (Product product:quotation.getProduct()){
                String category = product.getCategory();
                String unit = product.getUnit();
                if (!StringUtils.isEmpty(category)){
                    Dictionary dictionary = dictionaryService.selectDictionary(product.getCategory());
                    product.setCategory(dictionary.getValue());
                }
                if (!StringUtils.isEmpty(unit)){
                    Dictionary dictionary = dictionaryService.selectDictionary(unit);
                    product.setUnit(dictionary.getValue());
                }
                product.setUnitPrice(product.getLow()+"/"+ product.getMiddle()+"/"+ product.getHigh());
            }
            return quotation.getProduct();
        }
        return  quotation;
    }


    @RequestMapping("/quotationDownLoadIndex")
    public  String quotationDownLoadIndex(){
        return  "quotationDownLoad";
    }

    @RequestMapping(value = "quotationDownLoad")
    public void quotationDownLoad (
            HttpServletRequest request, HttpServletResponse response,
            @DateTimeFormat(pattern="yyyy-MM-dd") Date startTime,
            @DateTimeFormat(pattern="yyyy-MM-dd") Date endTime
            ) throws Exception{

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        String fileName = "报价单"+date+".xlsx";
        XSSFWorkbook wb = new XSSFWorkbook();

        String beginYear = sdf.format(startTime);
        String entYear = sdf.format(endTime);
        quotationService.quotationDownLoad(wb,beginYear,entYear);

        new DownloadHelper().downLoad(wb, response, fileName);
    }
}
