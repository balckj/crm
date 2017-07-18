package com.yidatec.mapper;


import com.yidatec.model.Dictionary;
import com.yidatec.vo.DictionaryVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DictionaryMapper {

	/**
	 * 载入字典可列表
	 * @return
	 */
	@Select("SELECT * FROM T_DICTIONARY WHERE id = #{id}")
	Dictionary selectDictionary(String id);

	/**
	 * 根据code入字典数据
	 * @param dictionaryVO
	 * @return
	 */
	@SelectProvider(type=com.yidatec.mapper.DictionaryQueryProvider.class,method = "selectDictionary")
	List<Dictionary> selectDictionaryListByCode(DictionaryVO dictionaryVO);
	/**
	 * 根据code入字典数据
	 * @param dictionaryVO
	 * @return
	 */
	@SelectProvider(type=com.yidatec.mapper.DictionaryQueryProvider.class,method = "countDictionary")
	int countDictionaryListByCode(DictionaryVO dictionaryVO);


	@Insert("INSERT INTO T_DICTIONARY (id,`sort`,code,value,state,description,creatorId,createTime," +
			"modifierId,modifyTime) VALUES (#{id},#{sort}," +
			"#{code},#{value},#{state},#{description}," +
			"#{creatorId},#{createTime},#{modifierId},#{modifyTime})")
	int create(Dictionary dictionary);

	@Update("UPDATE T_DICTIONARY SET `sort`=#{sort},code=#{code},value=#{value},state=#{state}," +
			"description=#{description}," +
			"modifierId=#{modifierId}," +
			"modifyTime=#{modifyTime} WHERE id=#{id}")
	int update(Dictionary dictionary);

	@Delete("DELETE FROM T_DICTIONARY WHERE id=#{id}")
	int delete(String id);

}
