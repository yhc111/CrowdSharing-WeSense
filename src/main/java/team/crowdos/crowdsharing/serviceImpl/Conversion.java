package team.crowdos.crowdsharing.serviceImpl;

import team.crowdos.crowdsharing.entity.Task;
import team.crowdos.crowdsharing.entity.appTask;
import team.crowdos.crowdsharing.mapper.TaskMapper;
import team.crowdos.crowdsharing.mapper.appTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class Conversion {

    @Autowired
    private appTaskMapper apptaskMapper;

    @Autowired
    private TaskMapper taskMapper;

    //@Scheduled(cron = "1-2 0/30 * * * ?")
    @Scheduled(cron = "0 0/30 * * * ? ")
    public void appTaskUpdate()
    {
        Date now = new Date();
        Date before = new Date(now.getTime() - 30*60*1000);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, String> map = new HashMap<String, String>(){
            {
                put("now", df.format(now));
                put("before", df.format(before));
            }
        };
        List<appTask> list = new ArrayList<appTask>();
        list = apptaskMapper.getAllNewTask(map);
        //当list不为空时，将appTask类转换成Task类
        if (list.size() != 0){
            for (appTask element : list){
                Task element2 = new Task(element.getTaskid(), element.getTaskname(), element.getUserid().toString(),
                        element.getUsername(), element.getTaskkind(), element.getdescribe_task(), element.getTaskstatus(),
                        element.getPosttime(), element.getDeadline(), element.getTotalnum(), element.getTemp(), 0.0, 0.0,
                        0, 0, 0, element.getCoin());
                if (element2.getTaskstatus() == 1){
                    taskMapper.insertAppIntoFinishedTask(element2);
                    System.out.println("ok");
                } else {
                    taskMapper.insertAppIntoUnfinishedTask(element2);
                    System.out.println("ok");
                }
            }
        } else {
            System.out.println(df.format(now) + " 没有新纪录");
        }

    }


//    //原生的Timer方法不行
//    private appTaskMapper apptaskMapper = SpringContextHolder.getBean(appTaskMapper.class);
//
//    private TaskMapper taskMapper = SpringContextHolder.getBean(TaskMapper.class);
//
//    public void appTaskUpdate(){
//
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                //半小时之内的数据
//                Date now = new Date();
//                Date before = new Date(now.getTime() - 30*60*1000);
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//                Map<String, String> map = new HashMap<String, String>(){
//                    {
//                        put("now", df.format(now));
//                        put("before", df.format(before));
//                    }
//                };
//                List<appTask> list = new ArrayList<appTask>();
//                list = apptaskMapper.getAllNewTask(map);
//                System.out.println(list.size());
//            }
//        }, 1000,5000);
//    }

}
