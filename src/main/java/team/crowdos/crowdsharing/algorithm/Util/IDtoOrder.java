package team.crowdos.crowdsharing.algorithm.Util;

import team.crowdos.crowdsharing.algorithm.entity.Task_entropy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IDtoOrder {

    /*public static void main(String[] args){
        IDtoOrder test = new IDtoOrder();
        System.out.println(test.idToOrder(57));
    }*/

    //这个很重要，每次都要注意改!!!
    public int idToOrder(int id){
        int order = 0;
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

        for (int i = 0; i < taskList.size(); i++){
            if (taskList.get(i).getTaskID() == id){
                order = i;
            }
        }

        return order;
    }
}
