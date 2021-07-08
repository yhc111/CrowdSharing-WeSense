package team.crowdos.crowdsharing.controller;

import team.crowdos.crowdsharing.service.UserService;
import team.crowdos.crowdsharing.serviceImpl.User_taskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private User_taskServiceImpl userTaskService;
    @Autowired
    private UserService userService;

    static private final String file_root_path = "E:/4_project/crowd网站/file_test/file_local/";
//    static private final String file_root_path = "F:/IDEAproject/file_test/file_local/";

    /*
     *单文件上传
     */
    @ResponseBody
    @RequestMapping(value = "/fileUpload")
    public String upload(@RequestParam("dataset")MultipartFile file,@RequestParam("dataName")String dataName,
                         @RequestParam("describe")String describe, HttpServletResponse response){
        if(file.isEmpty()){
            return "false";
        }
        String fileName = file.getOriginalFilename();
        float size = (float)file.getSize()/(1024*1024);
        System.out.println(fileName + "-->" + size + "MB");

        String path =  file_root_path + dataName;
        File dest = new File(path + "/" + fileName);
        File dest2 = new File(path + "/" + "describe.txt");
        if (!dest.getParentFile().exists()){//判断文件父目录是否存在
            boolean result = dest.getParentFile().mkdirs();
            assert result;
        }
        try {
            file.transferTo(dest); //保存文件

            boolean result = dest2.createNewFile();
            assert result;

            BufferedWriter out = new BufferedWriter(new FileWriter(dest2));
            out.write(describe);
            out.flush();
            out.close();
            return "{\"status\":\"true\"}";
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            return "{\"status\":\"false\"}";
        }
    }

    /*
     *多文件上传
     */
    @ResponseBody
    @PostMapping(value = "/multifileUpload")
    public String multifileUpload(HttpServletRequest request, HttpServletResponse response){
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("fileName");
        if(files.isEmpty()){
            return "false";
        }
        for (MultipartFile file:files){
            String fileName = file.getOriginalFilename();
            float size = (float)file.getSize()/(1024*1024);
            System.out.println(fileName + "-->" + size + "MB");

            if(file.isEmpty()){
                return "false";
            }else{
                File dest = new File(file_root_path + "/" + fileName);
                if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                    boolean result = dest.getParentFile().mkdir();
                    assert result;
                }
                try {
                    file.transferTo(dest);
                }catch (Exception e) {
                    e.printStackTrace();
                    return "false";
                }
            }
        }
        try {
            response.sendRedirect("http://localhost:8080/multifile?status=true");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "true";
    }

    /*
     *csv数据集文件下载
     */
    @RequestMapping("/csvFileDownload")
    public String csvFileDownload(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
        String csvFileID = request.getParameter("csvFileID") + ".csv";
        String filePath = "I:/ideapro/file_test/file_local/csv";
        File file = new File(filePath + "/" + csvFileID);
        //判断是否具有权限
        Cookie[] cookies = request.getCookies();
        int userID = -1;
        //遍历cookies获取数据
        if (cookies != null){
            for (Cookie c : cookies){
                String name = c.getName();
                String value = c.getValue();
                if (name.equals("userID")){
                    userID = Integer.parseInt(value);
                }
            }
        }
        //根据用户ID查看是否具有下载权限
        int level = userService.getDownloadPermission(userID);
        if (level == 1){
            if (file.exists()){//判断文件父目录是否存在
                response.setContentType("charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-Disposition", "attachment;fileName=" +   java.net.URLEncoder.encode(csvFileID,"UTF-8"));
                byte[] buffer = new byte[1024];
                FileInputStream fis = null; //文件输入流
                BufferedInputStream bis = null;
                OutputStream os = null; //输出流
                try {
                    os = response.getOutputStream();
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    int i = bis.read(buffer);
                    while(i != -1){
                        os.write(buffer);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //System.out.println("---file download---" + filename);
                try {
                    assert bis != null;
                    bis.close();
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            try {
                response.sendRedirect("http://localhost:8080/NoPermission");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /*
     *图片文件打包下载
     */

}
