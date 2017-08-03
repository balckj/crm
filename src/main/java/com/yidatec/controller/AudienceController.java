package com.yidatec.controller;

import com.yidatec.model.Audience;
import com.yidatec.service.AudienceService;
import com.yidatec.service.DictionaryService;
import com.yidatec.util.Constants;
import com.yidatec.vo.AudienceVO;
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
 * Created by jrw on 2017/7/12.
 */
@Controller
public class AudienceController extends BaseController{

    @Autowired
    AudienceService audienceService;

    @Autowired
    DictionaryService dictionaryService;

    @RequestMapping("/audienceList")
    public String audienceList(){
        return "audienceList";
    }

    @RequestMapping("/audienceEdit")
    public String audienceEdit(ModelMap model, @RequestParam(value="id",required = false) String id){
        model.put("title",(id == null || id.isEmpty())?"新建观众":"编辑观众");
        model.put("audience",audienceService.selectAudience(id));
        model.put("hobbyList",dictionaryService.selectDictionaryListByCodeCommon(Constants.HOBBY));
        return "audienceEdit";
    }

    @RequestMapping("/saveAudience")
    @ResponseBody
    public Object saveAudience(@Validated @RequestBody Audience audience,
                                 BindingResult result)throws Exception{
        List<FieldError> errors = result.getFieldErrors();
        if(errors  != null && errors.size() > 0){
            return errors;
        }
        if(audience.getId() == null || audience.getId().trim().length() <= 0) {//新建
            audience.setId(UUID.randomUUID().toString().toLowerCase());
            audience.setCreatorId(getWebUser().getId());
            audience.setCreateTime(LocalDateTime.now());
            audience.setModifierId(getWebUser().getId());
            audience.setModifyTime(audience.getCreateTime());
            audienceService.createAudience(audience);
        } else {
            audience.setModifierId(getWebUser().getId());
            audience.setModifyTime(LocalDateTime.now());
            audienceService.updateAudience(audience);
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/findAudience")
    @ResponseBody
    public Object findAudience(@RequestBody AudienceVO audienceVO)throws Exception{
        List<Audience> audienceList = audienceService.selectAudienceList(audienceVO);
        int count = audienceService.countSelectAudienceList(audienceVO);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("draw", audienceVO.getDraw());
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        map.put("data", audienceList);
        return map;
    }
}
