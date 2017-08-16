package com.yidatec.mapper;


import com.yidatec.model.User;
import com.yidatec.vo.UserVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * jrw
 */
@Mapper
public interface SequenceMapper {

	@Select("SELECT code_safe(#{c})")
	int generateSequence(String c);

}
