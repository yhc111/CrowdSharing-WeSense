package team.crowdos.crowdsharing.algorithm;

import team.crowdos.crowdsharing.algorithm.Util.IDtoOrder;
import team.crowdos.crowdsharing.algorithm.Util.QuickSort;
import team.crowdos.crowdsharing.algorithm.Util.UnallocatedTaskFilter;
import team.crowdos.crowdsharing.algorithm.entity.HumanTaskSequence;
import team.crowdos.crowdsharing.algorithm.entity.Task_entropy;

import java.io.*;
import java.util.*;

public class DuplicateProcess {

    //定义参与者人数和任务数量全局常量
    public static final int workerNum = 19;
    public static final int taskNum = 646;

    //统计并处理初次分配中的重复任务
    public static void main(String[] args){
        IDtoOrder idtoorder = new IDtoOrder();
        //读取初次分配结果
        File f1 = new File("E:\\实验二\\处理一\\人类参与者任务分配算法\\参与者数量变化-任务数量\\10\\初次分配结果.txt");
        List<String[]> worker_task = new ArrayList<String[]>();
        try{
            InputStreamReader reader = new InputStreamReader(new FileInputStream(f1), "UTF-8");
            BufferedReader br = new BufferedReader(reader);
            String lineTxt = null;
            while((lineTxt = br.readLine()) != null){
                String[] str = lineTxt.split(" ");
                worker_task.add(str);
            }
            br.close();
            reader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //找出重复的任务集合
        int[] taskID = new int[5000];
        int flag = 0;
        for (int i = 0; i < worker_task.size()-1; i++){
            for (int j = i + 1; j < worker_task.size(); j++){
                //比较两个数组的元素是否存在在重复
                String[] str1 = worker_task.get(i);
                String[] str2 = worker_task.get(j);
                for (int e1 = 0; e1 < str1.length-1; e1++){
                    for (int e2 = 0; e2 < str2.length-1; e2++){
                        if (str1[e1].equals(str2[e2])){
                            taskID[flag] = Integer.parseInt(str1[e1]);
                            flag++;
                        }
                    }
                }
            }
        }
        QuickSort qs = new QuickSort();
        qs.quickSort(taskID,0,flag);
        //去除重复元素，得到最终的重复任务列表duplicateTaskID
        ArrayList<Integer> duplicateTaskID = new ArrayList<Integer>();
        duplicateTaskID.add(taskID[0]);
        for(int i = 1; i < flag; i++){
            if (taskID[i-1] != taskID[i]){
                duplicateTaskID.add(taskID[i]);
            }
        }

        //输出重复任务的ID
        /*System.out.println(duplicateTaskID);
        System.out.println("-------------------------------------------------------");*/

        //worker_task是任务执行序列的list，存放workerNum个字符串数组，将这个列表的内容放进实体类，并得到任务执行序列taskSequences
        List<HumanTaskSequence> taskSequences = new ArrayList<>();
        for (String[] strings : worker_task){
            HumanTaskSequence h_t = new HumanTaskSequence();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < strings.length-1; i++){
                list.add(Integer.parseInt(strings[i]));
            }
            h_t.setList(list);
            h_t.setMaxValue(Double.parseDouble(strings[strings.length-1]));
            taskSequences.add(h_t);
        }

        //找出每个冗余任务对应的参与者集合，并存放在map中
        Map<Integer,List<Integer>> task_HumanWorkerSet = new HashMap<>();
        for (Integer element : duplicateTaskID){
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < taskSequences.size(); i++){
                if (taskSequences.get(i).getList().contains(element)){
                    list.add(i);
                }
            }
            task_HumanWorkerSet.put(element,list);
        }

        //读取任务信息taskList
        File f2 = new File("E:\\实验二\\处理一\\人类参与者任务分配算法\\参与者数量变化-任务数量\\10\\人类任务处理(权重).txt");
        List<Task_entropy> taskList = new ArrayList<Task_entropy>();

        try{
            InputStreamReader reader = new InputStreamReader(new FileInputStream(f2), "UTF-8");
            BufferedReader br = new BufferedReader(reader);
            String lineTxt = null;
            while((lineTxt = br.readLine()) != null){
                Task_entropy task = new Task_entropy();
                String[] str = lineTxt.split(",");
                task.setTaskID(Integer.parseInt(str[0]));
                task.setLatitude(Double.parseDouble(str[1]));
                task.setLongitude(Double.parseDouble(str[2]));
                task.setTaskType(Integer.parseInt(str[3]));
                task.setEntropy(Double.parseDouble(str[4]));
                taskList.add(task);
            }
            br.close();
            reader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //读取参与者-任务距离矩阵distances[][]
        File f3 = new File("E:\\实验二\\处理一\\人类参与者任务分配算法\\参与者数量变化-任务数量\\10\\参与者与任务距离.txt");
        double[][] distances = new double[workerNum][taskNum];
        int distanceFlag = 0;
        try{
            InputStreamReader reader = new InputStreamReader(new FileInputStream(f3), "UTF-8");
            BufferedReader br = new BufferedReader(reader);
            String lineTxt = null;
            while((lineTxt = br.readLine()) != null){
                String[] str = lineTxt.split(" ");
                for (int i = 0; i < distances[distanceFlag].length; i++){
                    distances[distanceFlag][i] = Double.parseDouble(str[i]);
                }
                distanceFlag++;
            }
            br.close();
            reader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*for (int i = 0; i < workerNum; i++){
            for (int j = 0; j < taskNum; j++){
                System.out.print(distances[i][j] + " ");
            }
            System.out.println();
        }*/

        //读取价值矩阵
        ValueMatrix vm = new ValueMatrix();
        double[][] matrix = vm.calculateValue(workerNum,taskNum);  //记得改一下
        /*for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }*/

        //对每个任务找出被执行价值最大的参与者  taskList存放了所有任务信息
        //map存放了每个任务的ID和对应的最大价值参与者
        //由于执行价值最大的参与者可能不在分配结果内，所以要改变求map的方法
        Map<Integer,Integer> maxValue_Task_Worker = new HashMap<>();
        Set<Map.Entry<Integer,List<Integer>>> entrySet = task_HumanWorkerSet.entrySet();
        Iterator<Map.Entry<Integer,List<Integer>>> it = entrySet.iterator();
        while (it.hasNext()){
            Map.Entry<Integer,List<Integer>> entry = it.next();
            Integer task = entry.getKey();
            List<Integer> humanList = entry.getValue();
            //输出每个任务及其对应的参与者集合
            //System.out.println(task + ":" + humanList);
            double maxValue = 0.00;
            for (int i = 0; i < taskList.size(); i++){
                if (taskList.get(i).getTaskID() == task){
                    for (Integer j : humanList){
                        if (matrix[j][i] > maxValue){
                            maxValue = matrix[j][i];
                            maxValue_Task_Worker.put(task,j);
                        }
                    }
                    break;
                }
            }
        }

        /*System.out.println("-------------------------------------------");
        //测试读入的任务序列数据是否正确
        int num = 0;
        for (HumanTaskSequence element : taskSequences){
            double maxValue = 0.00;
            for (int i = 0; i < element.getList().size(); i++){
                int tag = -1;
                for (int j = 0; j < taskList.size(); j++){
                    if (taskList.get(j).getTaskID() == element.getList().get(i)){
                        tag = j;
                        break;
                    }
                }
                maxValue = maxValue + matrix[num][tag];
                System.out.print(element.getList().get(i) + " ");
            }
            num++;
            System.out.println(element.getMaxValue() + " " + maxValue);
        }*/

        //计算去除冗余任务后每个序列的总偏移距离
        for (int j = 0; j < taskSequences.size(); j++){
            double totalDistance = 0.00;
            for (int i = 0; i < taskSequences.get(j).getList().size(); i++){
                int order = idtoorder.idToOrder(taskSequences.get(j).getList().get(i));
                double dis = distances[j][order];
                totalDistance = totalDistance + dis;
            }
            taskSequences.get(j).setTotalDistance(totalDistance);
        }

        System.out.println("--------------------------------------");
        for (HumanTaskSequence element : taskSequences){
            System.out.println(element.getList() + " " + element.getMaxValue() + " " + element.getTotalDistance());
        }

        //我们可以看到结果中存在十分多的冗余任务，对于原子任务点是不利的，但是对于复杂任务点的分配是有利的
        //论文中可以改的一个创新点是：如果一个任务点需要多人完成，那么我们就可以正好利用多个参与者任务序列的冗余性
        //对于列表中的每一个重复任务，根据价值只让最大价值的参与者执行，从其他参与者的任务执行序列中删除
        for (Integer taskIDelement : duplicateTaskID){
            for (int i = 0; i < taskSequences.size(); i++){
                if (taskSequences.get(i).getList().contains(taskIDelement)){
                    //如果包含该任务，判断是否是最大价值参与者
                    if (i != maxValue_Task_Worker.get(taskIDelement)){
                        //移除该任务
                        taskSequences.get(i).getList().remove(taskIDelement);
                        //重置最大价值
                        int tag = -1;
                        for (int j = 0; j < taskList.size(); j++){
                            if (taskList.get(j).getTaskID() == taskIDelement){
                                tag = j;
                                break;
                            }
                        }
                        double newMaxValue = taskSequences.get(i).getMaxValue()-matrix[i][tag];
                        taskSequences.get(i).setMaxValue(newMaxValue);
                    }
                }else {}
            }
        }

        //计算去除冗余任务后每个序列的总偏移距离
        for (int j = 0; j < taskSequences.size(); j++){
            double totalDistance = 0.00;
            for (int i = 0; i < taskSequences.get(j).getList().size(); i++){
                int order = idtoorder.idToOrder(taskSequences.get(j).getList().get(i));
                double dis = distances[j][order];
                totalDistance = totalDistance + dis;
            }
            taskSequences.get(j).setTotalDistance(totalDistance);
        }

        System.out.println("--------------------------------------");
        for (HumanTaskSequence element : taskSequences){
            System.out.println(element.getList() + " " + element.getMaxValue() + " " + element.getTotalDistance());
        }

        /*//打印去除冗余任务后的参与者执行序列以及最大总价值
        System.out.println("----------------------------------------");
        for (HumanTaskSequence element : taskSequences){
            for (int i = 0; i < element.getList().size(); i++){
                System.out.print(element.getList().get(i) + " ");
            }
            System.out.println(element.getMaxValue() + " " + element.getTotalDistance());
        }*/

        int cycles = 0;

        //生成剩余任务集合
        UnallocatedTaskFilter utf = new UnallocatedTaskFilter();
        taskList = utf.filter(taskSequences,taskList);

        //定义所有参与者最大容忍总距离之和并计算所有参与者的总容忍距离
        double maxTotalDistance = 3000*workerNum;
        double totalDistance = 0.00;
        for (int i = 0; i < taskSequences.size(); i++){
            totalDistance = totalDistance + taskSequences.get(i).getTotalDistance();
        }

        int Num = -1;
        //终止条件应为当存在未分配任务且存在参与者的当前最大距离小于容忍距离且本次分配结果与上次不同，有更新
        while (taskList.size() != 0 && totalDistance < maxTotalDistance && Num != taskList.size()){
            Num = taskList.size();
            //再次动态规划分配
            TaskAllocationLoop tal = new TaskAllocationLoop();
            taskSequences = tal.loop(taskSequences,distances,matrix,taskList);

            System.out.println("--------------------------------------");
            for (HumanTaskSequence element : taskSequences){
                System.out.println(element.getList() + " " + element.getMaxValue() + " " + element.getTotalDistance());
            }

            //去除冗余任务
            taskSequences = tal.dupTaskDel(taskSequences,matrix,distances);

            System.out.println("--------------------------------------");
            for (HumanTaskSequence element : taskSequences){
                System.out.println(element.getList() + " " + element.getMaxValue() + " " + element.getTotalDistance());
            }

            //生成剩余任务集合
            taskList = utf.filter(taskSequences,taskList);
            cycles++;
        }

        int remainTaskNum = taskList.size();
        int receivedTaskNum = taskNum - remainTaskNum;

        //分配总价值、总偏移距离
        double totalValue = 0.0, totalDis = 0.0;
        for (HumanTaskSequence element : taskSequences){
            totalValue = totalValue + element.getMaxValue();
            totalDis = totalDis + element.getTotalDistance();
        }

        System.out.println("--------------------------------------");
        System.out.println("再分配轮次：" + cycles);
        System.out.println("已分配任务：" + receivedTaskNum);
        System.out.println("未分配任务：" + remainTaskNum);
        System.out.println("分配率:" + (double)receivedTaskNum/(double)taskNum);
        System.out.println("总价值：" + totalValue);
        System.out.println("总偏移距离：" + totalDis);
        System.out.println("工人能力使用率：" + totalDis/maxTotalDistance);
    }

}
