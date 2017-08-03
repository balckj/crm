package com.yidatec.controller;

import com.yidatec.mapper.ExhibitionMapper;
import com.yidatec.model.Exhibition;
import com.yidatec.service.ExhibitionService;
import com.yidatec.vo.ExhibitionVO;
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
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.yidatec.controller.BaseController.getSuccessJson;

/**
 * Created by Administrator on 2017/7/12.
 */
@Controller
public class ExhibitionController extends BaseController{

    @Autowired
    ExhibitionService exhibitionService;

    @RequestMapping("/exhibitionList")
    public String exhibitionList(){
        return "exhibitionList";
    }

    @RequestMapping("/exhibitionEdit")
    public String exhibitionEdit(ModelMap model, @RequestParam(value="id",required = false) String id){
        model.put("title",(id == null || id.isEmpty())?"新建展馆":"编辑展馆");
        model.put("ex",exhibitionService.selectExhibition(id));
        return "exhibitionEdit";
    }

    @RequestMapping("/saveExhibition")
    @ResponseBody
    public Object saveExhibition(@Validated @RequestBody Exhibition exhibition,
                                  BindingResult result)throws Exception{
        List<FieldError> errors = result.getFieldErrors();
        if(errors  != null && errors.size() > 0){
            return errors;
        }
        if(exhibition.getId() == null || exhibition.getId().trim().length() <= 0) {//新建
            exhibition.setId(UUID.randomUUID().toString().toLowerCase());
            exhibition.setCreatorId(getWebUser().getId());
            exhibition.setCreateTime(LocalDateTime.now());
            exhibition.setModifierId(getWebUser().getId());
            exhibition.setModifyTime(LocalDateTime.now());
            exhibitionService.createExhibition(exhibition);
        } else {
            exhibition.setModifierId(getWebUser().getId());
            exhibition.setModifyTime(LocalDateTime.now());
            exhibitionService.updateExhibition(exhibition);
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/findExhibition")
    @ResponseBody
    public Object findExhibition(@RequestBody ExhibitionVO exhibitionVO)throws Exception{
        List<ExhibitionVO> exhibitionList = exhibitionService.selectExhibitionList(exhibitionVO);
        int count = exhibitionService.countSelectExhibitionList(exhibitionVO);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("draw", exhibitionVO.getDraw());
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        map.put("data", exhibitionList);
        return map;
    }
}
