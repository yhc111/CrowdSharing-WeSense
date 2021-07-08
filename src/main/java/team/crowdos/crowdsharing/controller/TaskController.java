package team.crowdos.crowdsharing.controller;

import org.omg.CORBA.WStringSeqHelper;
import team.crowdos.crowdsharing.entity.Task;
import team.crowdos.crowdsharing.serviceImpl.TaskServiceImpl;
import team.crowdos.crowdsharing.serviceImpl.User_taskServiceImpl;
import team.crowdos.crowdsharing.utils.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskService;
    @Autowired
    private User_taskServiceImpl userTaskService;

    //任务状态：未完成为0，已完成为1
    @ResponseBody
    @RequestMapping("/getTaskNum")
    public Map<String,Object> getAllTasks(){
        Map<String,Object> map = new HashMap<>();
        int unfinishedTaskNum = taskService.getUnfinishedTaskNum();
        int finishedTaskNum = taskService.getFinishedTaskNum();
        int totalTaskNum = unfinishedTaskNum + finishedTaskNum;
        map.put("unfinishedTaskNum",unfinishedTaskNum);
        map.put("finishedTaskNum",finishedTaskNum);
        map.put("totalTaskNum",totalTaskNum);
        return map;
    }

    @ResponseBody
    @RequestMapping("/getUnfinishedTaskNumByClass")
    public Map<String,Integer> getUnfinishedTaskNumByClass(){
        return taskService.getUnfinishedTaskNumByClass();
    }

    @ResponseBody
    @RequestMapping("/getFinishedTaskNumByClass")
    public Map<String,Integer> getFinishedTaskNumByClass(){
        return taskService.getFinishedTaskNumByClass();
    }

    @ResponseBody
    @RequestMapping("/getAllUnfinishedTask")
    public List<Task> getAllUnfinishedTask(){
        return taskService.getAllUnfinishedTask();
    }

    @ResponseBody
    @RequestMapping("/getAllFinishedTask")
    public List<Task> getAllFinishedTask(){
        return taskService.getAllFinishedTask();
    }

    @ResponseBody
    @PostMapping(value = "/findPage")
    public Object findPage(@RequestBody PageRequest pageQuery){
        return taskService.findPage(pageQuery);
    }

    @ResponseBody
    @GetMapping(value = "/getTaskLonLat")
    public List<Map<String, Object>> getTaskLonLat(){
        return taskService.getTaskLonLat();
    }

    /*返回某个任务的详细信息*/
    @ResponseBody
    @PostMapping(value = "/getSingleTaskByID")
    public Task getSingleTaskByID(@RequestBody Map<String, String> map){
        Task task;
        int status = Integer.parseInt(map.get("taskstatus"));
        int taskId = Integer.parseInt(map.get("taskID"));
        if(status == 0){
            task = taskService.getUnfinishedTaskByID(taskId);
        }else if(status == 1){
            task = taskService.getFinishedTaskByID(taskId);
        }else {
            task = null;
        }
        return task;
    }

    //返回某个用户发布的所有未完成或已完成任务
    @ResponseBody
    @PostMapping(value = "/getTaskByUserID")
    public List<Task> getTaskByUserID(@RequestBody Map<String, String> map){
        int status = Integer.parseInt(map.get("taskstatus"));
        int userID = Integer.parseInt(map.get("userID"));
        if(status == 0){
            return taskService.getUnfinishedTaskByUserID(userID);
        }else if (status == 1){
            return taskService.getFinishedTaskByUserID(userID);
        }else {
            return null;
        }
    }

    //返回某个用户接收且正在完成的所有任务
    @ResponseBody
    @PostMapping(value = "/getAcceptTaskByUserID")
    public List<Task> getAcceptTaskByUserID(@RequestBody Map<String, String> map){
        int userID = Integer.parseInt(map.get("userID"));
        return userTaskService.selectAcceptTaskByUserID(userID);
    }
}
