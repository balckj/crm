package com.yidatec.mapper;

import com.yidatec.vo.DictionaryVO;
import com.yidatec.vo.UserVO;
import org.springframework.util.StringUtils;

/**
 * Created by jrw on 17-7-18.
 */
public class DictionaryQueryProvider {

    public String selectDictionary(final DictionaryVO dictionaryVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM T_DICTIONARY  as D WHERE 1=1");

        if(!StringUtils.isEmpty(dictionaryVO.getCode())){
            sb.append(" AND D.code = #{code}");
        }
        if(!StringUtils.isEmpty(dictionaryVO.getDescription())){
            sb.append(" AND D.description LIKE CONCAT('%',#{description},'%')");
        }
        if(!StringUtils.isEmpty(dictionaryVO.getState())){
            sb.append(" AND D.state = #{state}");
        }
        sb.append(" ORDER BY D.code,D.sort");
        sb.append(" LIMIT #{start},#{length}");
        return sb.toString();
    }
    public String countDictionary(final DictionaryVO dictionaryVO)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT count(*) from T_DICTIONARY  as D WHERE 1=1");

        if(!StringUtils.isEmpty(dictionaryVO.getCode())){
            sb.append(" AND D.code = #{code}");
        }
        if(!StringUtils.isEmpty(dictionaryVO.getDescription())){
            sb.append(" AND D.description = #{description}");
        }
        if(!StringUtils.isEmpty(dictionaryVO.getState())){
            sb.append(" AND D.state = #{state}");
        }
        return sb.toString();
    }
}
