package team.crowdos.crowdsharing.serviceImpl;

import team.crowdos.crowdsharing.entity.Task;
import team.crowdos.crowdsharing.mapper.TaskMapper;
import team.crowdos.crowdsharing.service.TaskService;
import team.crowdos.crowdsharing.utils.PageRequest;
import team.crowdos.crowdsharing.utils.PageResult;
import team.crowdos.crowdsharing.utils.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public int getUnfinishedTaskNum() {
        return taskMapper.getUnfinishedTaskNum();
    }

    @Override
    public int getFinishedTaskNum() {
        return taskMapper.getFinishedTaskNum();
    }

    @Override
    public Map<String, Integer> getUnfinishedTaskNumByClass() {
        Map<String,Integer> map = new HashMap<String, Integer>();
        List<Task> unfinishedTaskList;
        unfinishedTaskList = taskMapper.getAllUnfinishedTask();
        int PuSaf = 0, DalLif = 0, CiTra = 0, BuAct = 0, EnTai = 0;
        for (int i = 0; i < unfinishedTaskList.size(); i++)
        {
            Task a = unfinishedTaskList.get(i);
            switch (a.gettaskkind()){
                case 1 : PuSaf++; break;
                case 2 : DalLif++; break;
                case 3 : CiTra++; break;
                case 4 : BuAct++; break;
                case 5 : EnTai++; break;
            }
        }
        map.put("PuSaf", PuSaf);
        map.put("DalLif", DalLif);
        map.put("CiTra", CiTra);
        map.put("BuAct",BuAct);
        map.put("EnTai",EnTai);
        return map;
    }

    @Override
    public Map<String, Integer> getFinishedTaskNumByClass() {
        Map<String,Integer> map = new HashMap<String, Integer>();
        List<Task> finishedTaskList;
        finishedTaskList = taskMapper.getAllFinishedTask();
        int PuSaf = 0, DalLif = 0, CiTra = 0, BuAct = 0, EnTai = 0;
        for (int i = 0; i < finishedTaskList.size(); i++)
        {
            Task a = finishedTaskList.get(i);
            switch (a.gettaskkind()){
                case 1 : PuSaf++; break;
                case 2 : DalLif++; break;
                case 3 : CiTra++; break;
                case 4 : BuAct++; break;
                case 5 : EnTai++; break;
            }
        }
        map.put("PuSaf", PuSaf);
        map.put("DalLif", DalLif);
        map.put("CiTra", CiTra);
        map.put("BuAct",BuAct);
        map.put("EnTai",EnTai);
        return map;
    }

    @Override
    public List<Task> getAllFinishedTask() {
        return taskMapper.getAllFinishedTask();
    }

    @Override
    public List<Task> getAllUnfinishedTask() {
        return taskMapper.getAllUnfinishedTask();
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
    }

    @Override
    public List<Map<String, Object>> getTaskLonLat() {
        return taskMapper.getTaskLonLat();
    }

    /**
     * 调用分页插件完成分页
     */
    private PageInfo<Task> getPageInfo(PageRequest pageRequest){
        boolean flag = pageRequest.isFlag();
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        if(flag == true){
            List<Task> unfinishedTasks = taskMapper.getAllUnfinishedTask();
            for (int i = 0; i < unfinishedTasks.size(); i++){
            }
            return new PageInfo<Task>(unfinishedTasks);
        }else {
            List<Task> finishedTasks = taskMapper.getAllFinishedTask();
            return new PageInfo<Task>(finishedTasks);
        }
    }

    @Override
    public Task getUnfinishedTaskByID(Integer id) {
        return taskMapper.getUnfinishedTaskByID(id);
    }

    @Override
    public Task getFinishedTaskByID(Integer id) {
        return taskMapper.getFinishedTaskByID(id);
    }

    @Override
    public List<Task> getUnfinishedTaskByUserID(Integer id) {
        return taskMapper.getUnfinishedTaskByUserID(id);
    }

    @Override
    public List<Task> getFinishedTaskByUserID(Integer id) {
        return taskMapper.getFinishedTaskByUserID(id);
    }
}
