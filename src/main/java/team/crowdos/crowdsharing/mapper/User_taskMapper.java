package team.crowdos.crowdsharing.mapper;

import team.crowdos.crowdsharing.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface User_taskMapper {

    List<Task> selectAcceptTaskByUserID(Integer id);

    /*
    int deleteByPrimaryKey(Integer userTaskid);

    int insert(User_task record);

    int insertSelective(User_task record);

    User_task selectByPrimaryKey(Integer userTaskid);

    int updateByPrimaryKeySelective(User_task record);

    int updateByPrimaryKey(User_task record);
     */
}