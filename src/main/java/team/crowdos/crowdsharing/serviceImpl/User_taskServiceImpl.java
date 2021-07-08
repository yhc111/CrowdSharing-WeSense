package team.crowdos.crowdsharing.serviceImpl;

import team.crowdos.crowdsharing.entity.Task;
import team.crowdos.crowdsharing.mapper.User_taskMapper;
import team.crowdos.crowdsharing.service.User_taskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User_taskServiceImpl implements User_taskService {

    @Autowired
    private User_taskMapper user_taskMapper;

    @Override
    public List<Task> selectAcceptTaskByUserID(Integer id) {
        return user_taskMapper.selectAcceptTaskByUserID(id);
    }
}
