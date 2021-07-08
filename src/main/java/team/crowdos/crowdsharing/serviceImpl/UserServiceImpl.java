package team.crowdos.crowdsharing.serviceImpl;

import team.crowdos.crowdsharing.entity.User;
import team.crowdos.crowdsharing.mapper.UserMapper;
import team.crowdos.crowdsharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int getUserNum() {
        return userMapper.getUserNum();
    }

    @Override
    public int getUser(String username, String password) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("username",username);
        map.put("password",password);
        User user = userMapper.getUser(map);
        if(user != null){
            return user.getUserid();
        }else {
            return 0;
        }
    }

    @Override
    public boolean getUserByName(String username) {
        User user = userMapper.getUserByName(username);
        return user != null;
    }

    @Override
    public void userRegister(User user) {
        userMapper.userRegister(user);
    }

    @Override
    public int getDownloadPermission(int userID) {
        return userMapper.getDownloadPermission(userID);
    }

    @Override
    public User getUserInfo(int userID){
        return userMapper.getUserById(userID);
    }
}
