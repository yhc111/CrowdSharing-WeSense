package team.crowdos.crowdsharing.algorithm.Util;

import team.crowdos.crowdsharing.algorithm.entity.HumanTaskSequence;
import team.crowdos.crowdsharing.algorithm.entity.Task_entropy;

import java.util.ArrayList;
import java.util.List;

public class UnallocatedTaskFilter {

    public List<Task_entropy> filter(List<HumanTaskSequence> taskSequences, List<Task_entropy> taskList){
        List<Integer> list = new ArrayList<Integer>();
        for (HumanTaskSequence element : taskSequences){
            for (int  j = 0; j < element.getList().size(); j++){
                list.add(element.getList().get(j));
            }
        }
        for (int i = 0; i < list.size(); i++){
            Task_entropy object = new Task_entropy();
            for (int j = 0; j < taskList.size(); j++){
                if (taskList.get(j).getTaskID() == list.get(i)){
                    object = taskList.get(j);
                }
            }
            taskList.remove(object);
        }
        return taskList;
    }
}
