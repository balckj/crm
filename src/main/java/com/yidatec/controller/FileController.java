package com.yidatec.controller;

import com.yidatec.util.FileUtills;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/18.
 */
@Controller
public class FileController extends BaseController{

    //多文件上传\
    @RequestMapping(value = "/fileupload", method = RequestMethod.POST)
    @ResponseBody
    public Map handleFileUpload(@RequestParam("files") MultipartFile file) throws IOException {
        long  startTime=System.currentTimeMillis();
        System.out.println("fileName："+file.getOriginalFilename());
        return FileUtills.FileUpload(file);
    }

    /**
     * 单条记录删除
     *
     * @return JSON
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/filedelete" ,method = RequestMethod.POST)
    public Map filedelete(@RequestParam("name") String name, HttpServletResponse response, HttpServletRequest request)
            throws Exception {
        Map<String, Object> reMap = new HashMap<String, Object>();
        String result =FileUtills.filedelete(name);
        if(result == "success"){
            //删除数据库记录
//            Boolean bool = fileService.deleteFile(id);
//            if (bool == true) {
//                reMap.put("mess", "附件删除成功！");
//            } else {
//                reMap.put("mess", "附件删除失败！");
//            }
            reMap.put("code",200);
        }else {
            reMap.put("code",400);
        }
        return reMap;
    }
}
