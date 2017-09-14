package com.yidatec.service;

import com.yidatec.mapper.DictionaryMapper;
import com.yidatec.model.Dictionary;
import com.yidatec.vo.DictionaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典服务
 *
 * @author jrw
 *
 */
@Service("dictionaryService")
public class DictionaryService {

	@Autowired
	DictionaryMapper dictionaryMapper;

	public Map<String,Dictionary> ALL_DICTIONARY_CACHE = null;

	public Map<String,List<Dictionary>> CODE_MAP = null;

//	@PostConstruct
	private void loadAllDictionary(){
		if (ALL_DICTIONARY_CACHE == null){
			ALL_DICTIONARY_CACHE = new HashMap<>();
			CODE_MAP = new HashMap<String,List<Dictionary>>();
			List<Dictionary> dictionaryList = dictionaryMapper.findDictionaryAll();
			for (Dictionary d: dictionaryList) {
				ALL_DICTIONARY_CACHE.put(d.getId(),d);
				List<Dictionary> dicList = CODE_MAP.get(d.getCode());
				if(dicList == null){
					dicList = new ArrayList<Dictionary>();
					CODE_MAP.put(d.getCode(),dicList);
				}
				dicList.add(d);
			}

		}
	}

	public void refreshDictionary(){
		if(ALL_DICTIONARY_CACHE != null){
			ALL_DICTIONARY_CACHE.clear();
			ALL_DICTIONARY_CACHE = null;
			CODE_MAP.clear();
			CODE_MAP = null;
		}
		loadAllDictionary();
	}

	public Dictionary selectDictionary(String id){
		loadAllDictionary();
		return ALL_DICTIONARY_CACHE.get(id);
//		return dictionaryMapper.selectDictionary(id);
	}

	public List<Dictionary> selectDictionaryListByCodeCommon(String code){
		loadAllDictionary();
		return  CODE_MAP.get(code);
	}

	/**
	 * 查询字典数据
	 *
	 * @return
	 */
	public List<Dictionary> selectDictionaryListByCode(DictionaryVO dictionaryVO) {
		return dictionaryMapper.selectDictionaryListByCode(dictionaryVO);
	}

	/**
	 * 查询字典数据
	 *
	 * @return
	 */
	public int countDictionaryListByCode(DictionaryVO dictionaryVO) {
		return dictionaryMapper.countDictionaryListByCode(dictionaryVO);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void deleteDictionary(String id) {
		dictionaryMapper.delete(id);
		refreshDictionary();
	}

	/**
	 * 保存一个字典项目
	 *
	 * @param dictionary
	 * @return
	 */
	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void createDictionary(Dictionary dictionary) {
		dictionaryMapper.create(dictionary);
		refreshDictionary();
	}

	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void updateDictionary(Dictionary dictionary) {
		dictionaryMapper.update(dictionary);
		refreshDictionary();
	}
}
