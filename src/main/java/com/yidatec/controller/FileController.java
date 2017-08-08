package com.yidatec.controller;

import com.yidatec.util.ConfigProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
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
        String serverName = new Date().getTime()+file.getOriginalFilename();
        String path=ConfigProperties.getSetting("path")+serverName;
        File newFile=new File(path);

        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        long  endTime=System.currentTimeMillis();
        System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("url",serverName);
        return map;
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
        // 文件路径
//        String path = "E:/";
//        String newFileName = name ;
        File targetFile = new File(name);
        if (targetFile.exists()) {
            targetFile.delete();
            reMap.put("code",200);
//            Boolean bool = exhibitionpoService.deleteFile(id);
//            if (bool == true) {
//                reMap.put("mess", "附件删除成功！");
//            } else {
//                reMap.put("mess", "附件删除失败！");
//            }
        } else {
//            reMap.put("mess", "附件不存在！");

        }
        return reMap;
    }
}
