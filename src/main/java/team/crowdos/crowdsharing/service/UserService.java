package team.crowdos.crowdsharing.service;

import team.crowdos.crowdsharing.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    int getUserNum();

    int getUser(String username, String password);

    void userRegister(User user);

    boolean getUserByName(String username);

    int getDownloadPermission(int userID);

    User getUserInfo(int userID);

}
