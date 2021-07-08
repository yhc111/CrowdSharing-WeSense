package team.crowdos.crowdsharing.controller;

import team.crowdos.crowdsharing.serviceImpl.Conversion;
import team.crowdos.crowdsharing.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class StaticController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    Conversion conversion;

    @RequestMapping("/mytasks")
    public String mytasks(){
        return "mytasks";
    }

    @RequestMapping("/task_data")
    public String task_data(){
        return "task_data";
    }

    @RequestMapping("/visualization")
    public String visualization(){
        return "visualization";
    }

    @RequestMapping("/NoPermission")
    public String noPer(){
        return "NoPermission";
    }

    @RequestMapping("/algorithmResult")
    public String alRe(){
        return "charts";
    }

    @RequestMapping("/upload")
    public String upload(){
        return "upload";
    }

    @RequestMapping("/newHeader")
    public String newHeader(){
        return "newHeader";
    }

    @RequestMapping("/visualization2")
    public String visualization2(){
        return "visualization2";
    }

    @RequestMapping("/taskListstest")
    public String taskListstest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取cookies
        Cookie[] cs = request.getCookies();
        String username = "",password = "";
        //获取数据，遍历cookies
        if(cs != null){
            for(Cookie c : cs){
                String name = c.getName();
                String value = c.getValue();
                if (name.equals("userName")){
                    username = value;
                }else if(name.equals("userPassword")){
                    password = value;
                }
            }
            //String userPassword = SHA.getSHA256StrJava(password);
            int result = userService.getUser(username,password);
            if(result != 0) {
                return "taskListstest";
            }else {
                response.sendRedirect("http://localhost:8080/login");
            }
        }else {
            response.sendRedirect("http://localhost:8080/login");
        }
        return "taskListstest";
    }

    @RequestMapping("/")
    public String hello(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cs = request.getCookies();
        if(cs != null){
            for (Cookie c : cs){
                c.setMaxAge(0);
                response.addCookie(c);
            }
        }
        return "login";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cs = request.getCookies();
        if(cs != null){
            for (Cookie c : cs){
                c.setMaxAge(0);
                response.addCookie(c);
            }
        }
        return "login";
    }

    @RequestMapping(value = "/register")
    public String register(HttpServletRequest request, HttpServletResponse response){
        return "register";
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取cookies
        Cookie[] cs = request.getCookies();
        String username = "",password = "";
        //获取数据，遍历cookies
        if(cs != null){
            for(Cookie c : cs){
                String name = c.getName();
                String value = c.getValue();
                if (name.equals("userName")){
                    username = value;
                }else if(name.equals("userPassword")){
                    password = value;
                }
            }
            //String userPassword = SHA.getSHA256StrJava(password);
            int result = userService.getUser(username,password);
            if(result != 0) {
                return "index";
            }else {
                response.sendRedirect("http://localhost:8080/login");
            }
        }else{
            response.sendRedirect("http://localhost:8080/login");
        }
        return "index";
    }

    @RequestMapping("/taskLists")
    public String taskLists(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取cookies
        Cookie[] cs = request.getCookies();
        String username = "",password = "";
        //获取数据，遍历cookies
        if(cs != null){
            for(Cookie c : cs){
                String name = c.getName();
                String value = c.getValue();
                if (name.equals("userName")){
                    username = value;
                }else if(name.equals("userPassword")){
                    password = value;
                }
            }
            //String userPassword = SHA.getSHA256StrJava(password);
            int result = userService.getUser(username,password);
            if(result != 0) {
                return "taskLists";
            }else {
                response.sendRedirect("http://localhost:8080/login");
            }
        }else {
            response.sendRedirect("http://localhost:8080/login");
        }
        return "taskLists";
    }

    @RequestMapping("/map")
    public String map(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取cookies
        Cookie[] cs = request.getCookies();
        //获取数据，遍历cookies
        if(cs != null){
            for(Cookie c : cs){
                String name = c.getName();
                String value = c.getValue();
            }
            return "map";
        }else {
            response.sendRedirect("http://localhost:8080/login");
        }
        return "map";
    }

    @RequestMapping("/docs")
    public String docs(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取cookies
        Cookie[] cs = request.getCookies();
        String username = "",password = "";
        //获取数据，遍历cookies
        if(cs != null){
            for(Cookie c : cs){
                String name = c.getName();
                String value = c.getValue();
                if (name.equals("userName")){
                    username = value;
                }else if(name.equals("userPassword")){
                    password = value;
                }
            }
            //String userPassword = SHA.getSHA256StrJava(password);
            int result = userService.getUser(username,password);
            if(result != 0) {
                return "docs";
            }else {
                response.sendRedirect("http://localhost:8080/login");
            }
        }else {
            response.sendRedirect("http://localhost:8080/login");
        }
        return "docs";
    }

    /*
     * 获取download页面
     */
    @RequestMapping("/download")
    public String download(){
        return "download";
    }


    /*
     * 获取file.html页面
     */
    @RequestMapping("/file")
    public String file(){
        return "file";
    }

    /*
     * 获取multifile.html页面
     */
    @RequestMapping("/multifile")
    public String multifile(){
        return "multifile";
    }

    /**
     * 用户信息主页
     * @return String
     */
    @RequestMapping("/userHome.html")
    public String userHome(){
        return "userHome";
    }


    /* 任务管理部分 */

    /**
     * 发布任务
     * @return html
     */
    @RequestMapping("/publishTask.html")
    public String publishTask(){return "publishTask";}

    /**
     * 接受任务
     * @return html
     */
    @RequestMapping("/acceptTask.html")
    public String acceptTask(){return "acceptTask";}

    /**
     * 管理员操作
     * @return html
     */
    @RequestMapping("/administratorOperation.html")
    public String administratorOperation(){return "administratorOperation";}
}
