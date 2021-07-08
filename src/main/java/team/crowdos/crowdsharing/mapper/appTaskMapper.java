package team.crowdos.crowdsharing.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import team.crowdos.crowdsharing.entity.appTask;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface appTaskMapper {
    int deleteByPrimaryKey(Integer taskid);

    int insert(appTask record);

    int insertSelective(appTask record);

    appTask selectByPrimaryKey(Integer taskid);

    int updateByPrimaryKeySelective(appTask record);

    int updateByPrimaryKeyWithBLOBs(appTask record);

    int updateByPrimaryKey(appTask record);

    List<appTask> getAllNewTask(Map map);

}