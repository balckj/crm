package com.yidatec.controller;


import com.yidatec.model.Dictionary;
import com.yidatec.model.User;
import com.yidatec.service.DictionaryService;
import com.yidatec.vo.DictionaryVO;
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
 * 字典管理
 * @author j
 *
 */
@Controller
public class DictionaryController extends BaseController {

	@Autowired
	DictionaryService dictionaryService;
	
	/**
	 * 转到字典主页
	 * @return
	 */
	@RequestMapping(value = "dictionaryList")
	public String dictionaryList(ModelMap model) {
//		// 取得字典类型
//		model.put("dictionaryList", dictionaryService.loadDictionaryList());
		return "dictionaryList";
	}

	@RequestMapping("/dictionaryEdit")
	public String dictionaryEdit(ModelMap model, @RequestParam(value="id",required = false) String id){
		model.put("title",(id == null || id.isEmpty())?"新建字典":"编辑字典");
		model.put("dictionary",dictionaryService.selectDictionary(id));
		return "dictionaryEdit";
	}

	@RequestMapping("/saveDictionary")
	@ResponseBody
	public Object saveDictionary(@Validated @RequestBody Dictionary dictionary,
							  BindingResult result)throws Exception{
		List<FieldError> errors = result.getFieldErrors();
		if(errors  != null && errors.size() > 0){
			return errors;
		}
		Dictionary dictionary1 = new Dictionary();
		if(dictionary.getId() == null || dictionary.getId().trim().length() <= 0)//新建
		{
			dictionary1.setId(UUID.randomUUID().toString().toLowerCase());
			dictionary1.setCode(dictionary.getCode());
			dictionary1.setSort(dictionary.getSort());
			dictionary1.setValue(dictionary.getValue());
			dictionary1.setState(dictionary.getState());
			dictionary1.setDescription(dictionary.getDescription());
			dictionary1.setCreatorId(getWebUser().getId());
			dictionary1.setCreateTime(LocalDateTime.now());
			dictionary1.setModifierId(getWebUser().getCreatorId());
			dictionary1.setModifyTime(getWebUser().getCreateTime());
			dictionaryService.createDictionary(dictionary1);
		} else {
			dictionary1.setId(dictionary.getId());
			dictionary1.setCode(dictionary.getCode());
			dictionary1.setSort(dictionary.getSort());
			dictionary1.setValue(dictionary.getValue());
			dictionary1.setState(dictionary.getState());
			dictionary1.setDescription(dictionary.getDescription());
			dictionary1.setModifierId(getWebUser().getId());
			dictionary1.setModifyTime(LocalDateTime.now());
			dictionaryService.updateDictionary(dictionary1);
		}
		return getSuccessJson(null);
	}

	@RequestMapping("/deleteDictionary")
	@ResponseBody
	public Object deleteDictionary(String id)throws Exception{
		dictionaryService.deleteDictionary(id);
		return getSuccessJson(null);
	}

	@RequestMapping(value = "/findDictionary")
	@ResponseBody
	public Object findDictionary(@RequestBody DictionaryVO dictionaryVO)throws Exception{
		List<Dictionary> dictionaryEntityList = dictionaryService.selectDictionaryListByCode(dictionaryVO);
		int count = dictionaryService.countDictionaryListByCode(dictionaryVO);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("draw", dictionaryVO.getDraw());
		map.put("recordsTotal", count);
		map.put("recordsFiltered", count);
		map.put("data", dictionaryEntityList);
		return map;
	}
	
}
