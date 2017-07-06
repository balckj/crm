package com.yidatec.util;


import com.yidatec.exception.BusinessException;
import com.yidatec.exception.ExceptionID;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.net.URLEncoder;

/**
 * @author QuShengWen
 */
public class DownloadHelper {
	
	 public void downLoad(XSSFWorkbook wb, HttpServletResponse response, String fileName) throws Exception {
	        OutputStream out = null;
	        try {
	            out = response.getOutputStream();
	            response.setContentType("application/x-msdownload");
	            response.setCharacterEncoding("utf-8");
	            response.setHeader("Content-disposition",
	                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
	            //response.setContentType("application/msexcel;charset=UTF-8");
	            wb.write(out);

	        } catch (FileNotFoundException e) {
	            throw new BusinessException(ExceptionID.GENERATE_FILE_ERROR,e);
	        } catch (Exception e) {
	            throw new BusinessException(ExceptionID.DOWNLOAD_FILE_ERROR,e);
	        } finally {
                if(out != null) {
                    try {
                        out.flush();
                        out.close();
                    } catch (Exception ee) {
                    }
                }
	        }


//         org.apache.commons.io.output.ByteArrayOutputStream baos = new org.apache.commons.io.output.ByteArrayOutputStream(40960);
//         wb.write(baos);
////         wb.close();
//         baos.flush();
//         baos.close();
//         HttpHeaders headers = new HttpHeaders();
//         headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//         headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"",fileName));
//         headers.add("Pragma", "no-cache");
//         headers.add("Expires", "0");
//
//         return ResponseEntity
//                 .ok()
//                 .headers(headers)
//                 .contentLength(baos.size())
//                 .contentType(MediaType.parseMediaType("application/octet-stream"))
////                 .body(new InputStreamResource(file.getInputStream()));
//         .body(baos.toByteArray());
     }



    public void downLoad(SXSSFWorkbook wb, HttpServletResponse response, String fileName) throws Exception {
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            response.setContentType("application/x-msdownload");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            //response.setContentType("application/msexcel;charset=UTF-8");
            wb.write(out);
            wb.dispose();
        } catch (FileNotFoundException e) {
            throw new BusinessException(ExceptionID.GENERATE_FILE_ERROR,e);
        } catch (Exception e) {
            throw new BusinessException(ExceptionID.DOWNLOAD_FILE_ERROR,e);
        } finally {
            if(out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (Exception ee) {
                }
            }
        }
    }
	 
    public void downLoad(ByteArrayOutputStream output, HttpServletResponse response, String fileName) throws Exception {
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            response.setContentType("application/x-msdownload");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            //response.setContentType("application/msexcel;charset=UTF-8");
            output.writeTo(out);
        } catch (FileNotFoundException e) {
            throw new BusinessException(ExceptionID.GENERATE_FILE_ERROR,e);
        } catch (Exception e) {
            throw new BusinessException(ExceptionID.DOWNLOAD_FILE_ERROR,e);
        } finally {
            if(out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (Exception ee) {
                }
            }
        }
    }

    public void downLoadFile(HttpServletRequest request, HttpServletResponse response, String fileName, String showName) throws Exception {
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            response.setContentType("application/x-msdownload");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition",
                    "attachment;filename=" + URLEncoder.encode(showName, "UTF-8"));
            //response.setContentType("application/msexcel;charset=UTF-8");
            String filePath = request.getSession().getServletContext().getRealPath(File.separator) + "WEB-INF"+File.separator+"classes"+File.separator+"template"+File.separator;
            String downLoadPath = filePath + fileName;
            File downLoadFile = new File(downLoadPath);
            byte[] res = FileUtils.readFileToByteArray(downLoadFile);
            out.write(res);
        } catch (Exception e) {
            throw new BusinessException(ExceptionID.DOWNLOAD_FILE_ERROR,e);
        } finally {
            if(out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (Exception ee) {
                }
            }
        }
    }

//    public void downExsitReport(HttpServletRequest request,HttpServletResponse response, String fileName,String showName) throws Exception {
//        OutputStream out = null;
//        try {
//            out = response.getOutputStream();
//            response.setContentType("application/x-msdownload");
//            response.setCharacterEncoding("utf-8");
//
//            String userAgent = request.getHeader("User-Agent");
//            byte[] bytes = userAgent.contains("MSIE") || userAgent.contains("Gecko") ? showName.getBytes() : showName.getBytes("UTF-8"); // name.getBytes("UTF-8")处理safari的乱码问题
//            showName = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码
//
//            response.setHeader("Content-disposition",
//                    "attachment;filename=" + showName);
//            //response.setContentType("application/msexcel;charset=UTF-8");
//            String filePath = request.getSession().getServletContext().getRealPath(File.separator) + HisToryReportController.UPLOAD_REPORT_PATH+File.separator+HisToryReportController.REPORT_PATH+File.separator;
//            String downLoadPath = filePath + fileName;
//            File downLoadFile = new File(downLoadPath);
//            byte[] res = FileUtils.readFileToByteArray(downLoadFile);
//            out.write(res);
//        } catch (Exception e) {
//            throw new BusinessException(ExceptionID.DOWNLOAD_FILE_ERROR,e);
//        } finally {
//            if(out != null) {
//                try {
//                    out.flush();
//                    out.close();
//                } catch (Exception ee) {
//                }
//            }
//        }
//    }
}
