package team.crowdos.crowdsharing.service;

import team.crowdos.crowdsharing.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface User_taskService {

    List<Task> selectAcceptTaskByUserID(Integer id);
}
