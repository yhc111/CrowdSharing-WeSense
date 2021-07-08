package team.crowdos.crowdsharing.controller;

import org.springframework.web.bind.annotation.Mapping;
import team.crowdos.crowdsharing.entity.User;
import team.crowdos.crowdsharing.serviceImpl.UserServiceImpl;
import team.crowdos.crowdsharing.utils.SHA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @ResponseBody
    @RequestMapping("/getUserNum")
    public Map<String,Object> getUserNum(){
        Map<String,Object> map = new HashMap<>();
        int userNum = userService.getUserNum();
        map.put("userNum", userNum);
        return map;
    }

    //用户登录验证
    @ResponseBody
    @PostMapping(value = "/getUser")
    public void getUser(@NotNull HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        //        String userPassword = SHA.getSHA256StrJava(password);
        String userPassword = request.getParameter("password");
        int result = userService.getUser(username,userPassword);
        if(result != 0){
            //String userId = Integer.toString(result);
            //创建cookie对象
            Cookie c1 = new Cookie("userName", username);
            Cookie c2 = new Cookie("userPassword", userPassword);
            Cookie c3 = new Cookie("userID",Integer.toString(result));
            c1.setPath("/");
            c2.setPath("/");
            c3.setPath("/");
            //发送cookie
            response.addCookie(c1);
            response.addCookie(c2);
            response.addCookie(c3);
//            response.sendRedirect("http://10.70.163.90:8080/index");
            response.sendRedirect("http://localhost:8080/index");
        }else {
//            response.sendRedirect("http://10.70.163.90:8080/login");
            response.sendRedirect("http://localhost:8080/login");
        }
    }

    //用户注册  需改动
    @ResponseBody
    @PostMapping(value = "/userRegister")
    public void userRegister(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(request.getParameter("birthday"));
        User user = new User();
        user.setUsername(request.getParameter("userName"));
        user.setRealname(request.getParameter("realName"));
        user.setSex(request.getParameter("gender"));
        user.setBirthday(date);
        user.setPhonenumber(request.getParameter("phoneNumber"));
        user.setEmail(request.getParameter("email"));

//        String userPassword = SHA.getSHA256StrJava(request.getParameter("passWord"));
        String userPassword = request.getParameter("passWord");
        user.setPassword(userPassword);
        boolean isDup = userService.getUserByName(request.getParameter("userName"));
        if(isDup == true){
            //response.sendRedirect("http://localhost:8080/register");
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            Cookie c = new Cookie("isDup", "true");
            c.setPath("/");
            response.addCookie(c);
            response.sendRedirect("http://localhost:8080/register");
        }else{
            userService.userRegister(user);
            response.sendRedirect("http://localhost:8080/login");
        }
    }

    @ResponseBody
    @RequestMapping("/getUserInfo")
    public Map<String, Object> getUserInfo(HttpServletRequest request){
        int userID = -1;
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie c: cookies){
                if (c.getName().equals("userID")){
                    userID = Integer.parseInt(c.getValue());
                }
            }
        }
        Map<String, Object> userInfo = new HashMap<>();
        User user = userService.getUserInfo(userID);
        userInfo.put("username", user.getUsername());
        userInfo.put("realName", user.getRealname());
        userInfo.put("birthday", user.getBirthday());
        userInfo.put("phone", user.getPhonenumber());
        userInfo.put("email", user.getEmail());
        userInfo.put("level", user.getLevel());
        return userInfo;
    }

    @ResponseBody
    @RequestMapping("/userAvatar")
    public void handleUserAvatar(){
    }

}
