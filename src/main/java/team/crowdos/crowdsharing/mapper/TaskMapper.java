package team.crowdos.crowdsharing.mapper;

import team.crowdos.crowdsharing.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TaskMapper {

    int getUnfinishedTaskNum();

    int getFinishedTaskNum();

    List<Task> getAllUnfinishedTask();

    List<Task> getAllFinishedTask();

    List<Map<String, Object>> getTaskLonLat();

    Task getUnfinishedTaskByID(Integer id);

    Task getFinishedTaskByID(Integer id);

    List<Task> getUnfinishedTaskByUserID(Integer id);

    List<Task> getFinishedTaskByUserID(Integer id);

    void insertAppIntoFinishedTask(Task task);

    void insertAppIntoUnfinishedTask(Task task);

    /*
    int deleteByPrimaryKey(Integer taskid);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Integer taskid);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);
     */

}