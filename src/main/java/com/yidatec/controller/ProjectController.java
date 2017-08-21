package com.yidatec.controller;

import com.yidatec.model.Dictionary;
import com.yidatec.model.ProjectEntity;
import com.yidatec.service.*;
import com.yidatec.util.Constants;
import com.yidatec.vo.ProjectVO;
import com.yidatec.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
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
 * Created by neo on 2017/7/12.
 */
@Controller
public class ProjectController extends BaseController {

    @Autowired
    ProjectService projectService;

    @Autowired
    DictionaryService dictionaryService;

    @Autowired
    SequenceService  sequenceService;

    @Autowired
    DesignerService designerService;

    @Autowired
    PMService pmService;

    @Autowired
    FactoryService factoryService;


    @RequestMapping("/projectEdit")
    public String projectEdit(ModelMap model, @RequestParam(value="id",required = false) String id){
        model.put("title",(id == null || id.isEmpty())?"新建客户":"编辑客户");
        model.put("project",projectService.selectProject(id));
        model.put("code",sequenceService.generateProjectSequence());
        model.put("designer",designerService.selectDesignerforProject(new UserVO()));
        model.put("pm",pmService.selectPMforProject(new UserVO()));
        model.put("factories",factoryService.selectFactoryListForProject());
        model.put("designerProgress",dictionaryService.selectDictionaryListByCodeCommon(Constants.DESIGN_PROGRESS));
        model.put("projectProgress",dictionaryService.selectDictionaryListByCodeCommon(Constants.PROJECT_PROGRESS));
        model.put("factoryProgress",dictionaryService.selectDictionaryListByCodeCommon(Constants.FACTORY_PROGRESS));
        return "projectEdit";
    }

    @RequestMapping("/projectList")
    public String projectList(ModelMap model){
        return "projectList";
    }

    @RequestMapping("/saveProject")
    @ResponseBody
    public Object saveProject(@Validated @RequestBody ProjectEntity project,
                            BindingResult result)throws Exception{

        List<FieldError> errors = result.getFieldErrors();
        if(errors  != null && errors.size() > 0){
            return errors;
        }
        if(project.getId() == null || project.getId().trim().length() <= 0)//新建
        {
            project.setId(UUID.randomUUID().toString());
            project.setCreatorId(getWebUser().getId());
            project.setCreateTime(LocalDateTime.now());
            project.setModifierId(getWebUser().getCreatorId());
            project.setModifyTime(project.getCreateTime());
            projectService.createProject(project);

        } else {//编辑
            project.setModifierId(getWebUser().getId());
            project.setModifyTime(LocalDateTime.now());
            projectService.updateProject(project);
        }
        return getSuccessJson(null);
    }

    @RequestMapping(value = "/findProject")
    @ResponseBody
    public Object findProject(@RequestBody ProjectVO project)throws Exception{
        List<ProjectEntity> ProjectEntityList = projectService.selectProjectList(project);
        if (ProjectEntityList != null){
            for(ProjectEntity project1 : ProjectEntityList){
                String designparams = project1.getDesignProgress();
                if (!StringUtils.isEmpty(designparams)){
                    String[] paramsList = designparams.split(",");
                    String temp = "";
                    for(int i = 0 ; i < paramsList.length; i++){
                        Dictionary dictionary = dictionaryService.selectDictionary(paramsList[i]);
                        if(i != paramsList.length -1){
                            temp = temp + dictionary.getValue()  +",";
                        }else{
                            temp = temp + dictionary.getValue();
                        }
                    }
                    project1.setDesignProgress(temp);
                }

                String projectparams = project1.getProjectProgress();
                if (!StringUtils.isEmpty(projectparams)){
                    String[] paramsList = projectparams.split(",");
                    String temp = "";
                    for(int i = 0 ; i < paramsList.length; i++){
                        Dictionary dictionary = dictionaryService.selectDictionary(paramsList[i]);
                        if(i != paramsList.length -1){
                            temp = temp + dictionary.getValue()  +",";
                        }else{
                            temp = temp + dictionary.getValue();
                        }
                    }
                    project1.setProjectProgress(temp);
                }

                String factoryparams = project1.getFactoryProgress();
                if (!StringUtils.isEmpty(factoryparams)){
                    String[] paramsList = factoryparams.split(",");
                    String temp = "";
                    for(int i = 0 ; i < paramsList.length; i++){
                        Dictionary dictionary = dictionaryService.selectDictionary(paramsList[i]);
                        if(i != paramsList.length -1){
                            temp = temp + dictionary.getValue()  +",";
                        }else{
                            temp = temp + dictionary.getValue();
                        }
                    }
                    project1.setFactoryProgress(temp);
                }
            }
        }

        int count = projectService.countProjectList(project);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("draw", project.getDraw());
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        map.put("data", ProjectEntityList);
        return map;
    }
}
