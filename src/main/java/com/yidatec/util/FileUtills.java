package com.yidatec.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/4.
 */
public class FileUtills {

    /*
    文件上传
     */
    public static Map FileUpload(MultipartFile file) throws IOException {
        String serverName = new Date().getTime()+"_"+file.getOriginalFilename();
        String path = buildFile(ConfigProperties.getFileUrl(),true)+File.separator+serverName;
        File newFile=new File(path);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("url",serverName);
        return map;
    }

    /**
     * 文件删除
     * @return
     * @throws Exception
     */
    public static String filedelete(String filename)
            throws Exception {
        String result = "";
        String path = buildFile(ConfigProperties.getFileUrl(),true)+File.separator+filename;
        File targetFile = new File(path);
        if (targetFile.exists()) {
            targetFile.delete();
            result="success";
        } else {
            result="failed";
        }
        return result;
    }

    public static File buildFile(String fileName,boolean isDirectory){
        File target = new File(fileName);
        if (isDirectory){
            target.mkdirs();
        }else {
            if (!target.getParentFile().exists()){
                target.getParentFile().mkdirs();
                target = new File(target.getAbsolutePath());
            }
        }
        return target;
    }
}
