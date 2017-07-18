package com.yidatec.service;

import com.yidatec.mapper.DictionaryMapper;
import com.yidatec.model.Dictionary;
import com.yidatec.vo.DictionaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典服务
 *
 * @author j
 *
 */
@Service("dictionaryService")
public class DictionaryService {

	@Autowired
	DictionaryMapper dictionaryMapper;

	public Dictionary selectDictionary(String id){
		return  dictionaryMapper.selectDictionary(id);
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
	}

	@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
	public void updateDictionary(Dictionary dictionary) {
		dictionaryMapper.update(dictionary);
	}
}
