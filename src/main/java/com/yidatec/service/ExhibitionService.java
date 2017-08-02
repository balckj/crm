package com.yidatec.service;

import com.yidatec.mapper.ExhibitionMapper;
import com.yidatec.model.Exhibition;
import com.yidatec.vo.ExhibitionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("exhibitionService")
public class ExhibitionService {

    @Autowired
    ExhibitionMapper exhibitionMapper;

    public ExhibitionVO selectExhibition(String id){
        return  exhibitionMapper.selectExhibition(id);
    }

    /**
     * 查找所有展馆
     * @return
     */
    public List<ExhibitionVO> selectExhibitionAll(){
        return  exhibitionMapper.selectExhibitionAll();
    }
    /**
     * 查询展馆list
     *
     * @return
     */
    public List<ExhibitionVO> selectExhibitionList(ExhibitionVO exhibitionVO) {
        return  exhibitionMapper.selectExhibitionList(exhibitionVO);
    }

    /**
     * 查询展馆count
     *
     * @return
     */
    public int countSelectExhibitionList(ExhibitionVO exhibitionVO) {
        return exhibitionMapper.countSelectExhibitionList(exhibitionVO);
    }

    /**
     * 创建一个产品
     *
     * @param Exhibition
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void createExhibition(Exhibition exhibition) {
        exhibitionMapper.create(exhibition);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void updateExhibition(Exhibition exhibition) {
        exhibitionMapper.update(exhibition);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void deleteExhibition(String id) {
        exhibitionMapper.delete(id);
    }
}
