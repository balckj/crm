package com.yidatec.service;

import com.yidatec.mapper.ProjectMapper;
import com.yidatec.model.ProjectEntity;
import com.yidatec.vo.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/8/17.
 */
@Service("projectService")
public class ProjectService {
    @Autowired
    ProjectMapper projectMapper;
    
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void createProject(ProjectEntity project){
        projectMapper.create(project);
        for (int i=0;i<project.getDesignerId().size();i++){
            projectMapper.createDesignerRelation(project.getId(),project.getDesignerId().get(i));
        }

        for (int i=0;i<project.getFactoryId().size();i++){
            projectMapper.createFactoryRelation(project.getId(),project.getFactoryId().get(i));
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public void updateProject(ProjectEntity project) {
        projectMapper.deleteDesignerRelation(project.getId());
        projectMapper.deleteFactoryRelation(project.getId());
        projectMapper.update(project);
        for (int i=0;i<project.getDesignerId().size();i++){
            projectMapper.createDesignerRelation(project.getId(),project.getDesignerId().get(i));
        }

        for (int i=0;i<project.getFactoryId().size();i++){
            projectMapper.createFactoryRelation(project.getId(),project.getFactoryId().get(i));
        }
    }

    public List<ProjectVO> selectProjectList(ProjectVO project) {
        return projectMapper.selectProjectList(project);
    }

    public int countProjectList(ProjectVO project) {
        return projectMapper.countCustomerList(project);
    }

    public ProjectEntity selectProject(String id){
        ProjectEntity project = projectMapper.selectProject(id);
        if (project!=null){
            project.setDesignerId(projectMapper.selectDesigner(id));
            project.setFactoryId(projectMapper.selectFactory(id));
        }
        return project;
    }


}
