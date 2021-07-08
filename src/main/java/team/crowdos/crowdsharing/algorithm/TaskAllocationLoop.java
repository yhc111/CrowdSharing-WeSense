package team.crowdos.crowdsharing.algorithm;

import team.crowdos.crowdsharing.algorithm.Util.IDtoOrder;
import team.crowdos.crowdsharing.algorithm.entity.HumanTaskSequence;
import team.crowdos.crowdsharing.algorithm.entity.Task_entropy;

import java.util.*;
import java.util.stream.Collectors;

public class TaskAllocationLoop {

    public static final int disThreshold = 3000;

    public List<HumanTaskSequence> loop(List<HumanTaskSequence> taskSequences, double[][] distances, double[][] valueMatrix, List<Task_entropy> taskList){
        /**
         * 已有用户-参与者执行序列 taskSequences
         * 人类参与者信息列表 hwList （可以不加）
         * 人类参与者与任务的距离矩阵 distances[][]
         * 人类参与者与任务的价值矩阵 valueMatrix[][]
         * 剩余待分配任务列表 taskList
         */
        List<HumanTaskSequence> resultSequences = new ArrayList<HumanTaskSequence>();
        for (int i = 0; i < taskSequences.size(); i++){
            if (taskSequences.get(i).getTotalDistance() < disThreshold){
                resultSequences.add(DP(taskSequences.get(i),distances,valueMatrix,taskList,i));
            }
        }
        return resultSequences;
    }

    //对每个人类参与者进行动态规划求解
    public HumanTaskSequence DP(HumanTaskSequence humanWorker, double[][] distances, double[][] valueMatrix, List<Task_entropy> taskList, int flag){
        //flag标识这是第几个人类参与者
        double maxValue = humanWorker.getMaxValue();
        IDtoOrder ito = new IDtoOrder();
        int capacity = disThreshold-(int)Math.ceil(humanWorker.getTotalDistance());
        double[][] resultMatrix = new double[taskList.size()+1][capacity+1];

        for (int i = 1; i <= taskList.size(); i++){
            int order = ito.idToOrder(taskList.get(i-1).getTaskID());
            for (int j = 1; j <= capacity; j++){
                if (j < distances[flag][order]){
                    resultMatrix[i][j] = resultMatrix[i-1][j];
                }else {
                    if (resultMatrix[i-1][j] > resultMatrix[i-1][j - (int)Math.ceil(distances[flag][order])] + valueMatrix[flag][order]){
                        resultMatrix[i][j] = resultMatrix[i-1][j];
                    }else {
                        resultMatrix[i][j] = resultMatrix[i-1][j - (int)Math.ceil(distances[flag][order])] + valueMatrix[flag][order];
                    }
                }
            }
        }

        //更新最大价值
        maxValue = maxValue + resultMatrix[taskList.size()][capacity];
        humanWorker.setMaxValue(maxValue);
        //回溯价值矩阵将最优解的任务放入执行序列
        int m = taskList.size();
        int n = capacity;

        while (m > 0 && n > 0){
            int order = ito.idToOrder(taskList.get(m-1).getTaskID());
            if ((resultMatrix[m][n] > resultMatrix[m-1][n]) && (n - (int)Math.ceil(distances[flag][order]) > 0)){
                humanWorker.getList().add(taskList.get(m-1).getTaskID());
                humanWorker.setTotalDistance(humanWorker.getTotalDistance() + distances[flag][order]);
                //顺序不能错！！！
                n = n - (int)Math.ceil(distances[flag][order]);
                m = m - 1;
            }else{
                m = m - 1;
            }
        }
        return humanWorker;
    }

    //任务执行序列清洗--去除冗余任务
    public List<HumanTaskSequence> dupTaskDel(List<HumanTaskSequence> taskSequences, double[][] valueMatrix, double[][] distances){
        List<Integer> taskIDList = new ArrayList<Integer>();
        IDtoOrder ito = new IDtoOrder();
        for (int i = 0; i < taskSequences.size()-1; i++){
            for (int j = i+1; j < taskSequences.size(); j++){
                //比较两个序列的元素是否存在重复
                List<Integer> list1 = taskSequences.get(i).getList();
                List<Integer> list2 = taskSequences.get(j).getList();
                for (int e1 = 0; e1 < list1.size(); e1++){
                    for (int e2 = 0; e2 < list2.size(); e2++){
                        if (list1.get(e1) == list2.get(e2)){
                            taskIDList.add(list1.get(e1));
                        }
                    }
                }
            }
        }
        //去除重复元素
        taskIDList = taskIDList.stream().distinct().collect(Collectors.toList());

        //找出每个冗余任务对应的参与者集合，并存放在map中
        Map<Integer,List<Integer>> task_HumanWorkerSet = new HashMap<>();
        for (Integer element : taskIDList){
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < taskSequences.size(); i++){
                if (taskSequences.get(i).getList().contains(element)){
                    list.add(i);
                }
            }
            task_HumanWorkerSet.put(element,list);
        }

        //求任务-参与者最大价值对
        Map<Integer,Integer> maxValue_Task_Worker = new HashMap<>();
        Set<Map.Entry<Integer,List<Integer>>> entrySet = task_HumanWorkerSet.entrySet();
        Iterator<Map.Entry<Integer,List<Integer>>> it = entrySet.iterator();
        while (it.hasNext()){
            Map.Entry<Integer,List<Integer>> entry = it.next();
            Integer task = entry.getKey();
            List<Integer> humanList = entry.getValue();
            //System.out.println(task + ":" + humanList);
            double maxValue = 0.00;
            //价值矩阵
            int order = ito.idToOrder(task);
            for (Integer i : humanList){
                if (valueMatrix[i][order] > maxValue){
                    maxValue = valueMatrix[i][order];
                    maxValue_Task_Worker.put(task,i);
                }
            }
        }

        //若不是最大价值任务-参与者对，则删除
        for (Integer taskIDelement : taskIDList){
            for (int i = 0; i < taskSequences.size(); i++){
                if (taskSequences.get(i).getList().contains(taskIDelement)){
                    //如果包含该任务，判断是否是最大价值参与者
                    if (i != maxValue_Task_Worker.get(taskIDelement)){
                        //移除该任务
                        taskSequences.get(i).getList().remove(taskIDelement);
                        //重置总价值和总距离
                        int order = ito.idToOrder(taskIDelement);
                        double newMaxValue = taskSequences.get(i).getMaxValue() - valueMatrix[i][order];
                        taskSequences.get(i).setMaxValue(newMaxValue);
                        double newTotalDis = taskSequences.get(i).getTotalDistance() - distances[i][order];
                        taskSequences.get(i).setTotalDistance(newTotalDis);
                    }
                }else {}
            }
        }
        /*System.out.println("-----------------------");
        for (HumanTaskSequence element : taskSequences){
            for (int i = 0; i < element.getList().size(); i++){
                System.out.print(element.getList().get(i) + " ");
            }
            System.out.println(element.getMaxValue() + " " + element.getTotalDistance());
        }*/
        return taskSequences;
    }

}
