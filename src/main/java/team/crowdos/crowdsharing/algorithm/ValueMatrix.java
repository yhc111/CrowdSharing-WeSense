package team.crowdos.crowdsharing.algorithm;

import team.crowdos.crowdsharing.algorithm.entity.Task_entropy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ValueMatrix {

    /**
     * 人类参与者任务分配算法第四步：
     * 执行任务价值矩阵
     */

    public double[][] calculateValue(int workerNum, int taskNum){
        File f1 = new File("E:\\实验二\\处理一\\人类参与者任务分配算法\\参与者数量变化-任务数量\\10\\rewardMatrix.txt");
        File f2 = new File("E:\\实验二\\处理一\\人类参与者任务分配算法\\参与者数量变化-任务数量\\10\\人类任务处理(权重).txt");
        List<Task_entropy> list = new ArrayList<Task_entropy>();
        double[][] valueMatrix = new double[workerNum][taskNum];

        try{
            InputStreamReader reader = new InputStreamReader(new FileInputStream(f1), "UTF-8");
            BufferedReader br = new BufferedReader(reader);
            String lineTxt = null;
            int i = 0;
            while((lineTxt = br.readLine()) != null){
                Task_entropy task = new Task_entropy();
                String[] str = lineTxt.split(" ");
                for(int j = 0; j < str.length; j++){
                    valueMatrix[i][j] = Double.parseDouble(str[j]);
                }
                i++;
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
                list.add(task);
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

        for (int i = 0; i < valueMatrix.length; i++){
            for (int j = 0; j < valueMatrix[i].length; j++){
                valueMatrix[i][j] = valueMatrix[i][j] * list.get(j).getEntropy();
            }
        }

        /*DecimalFormat df = new DecimalFormat("#0.0");
        for(int i = 0; i < workerNum; i++){
            for (int j = 0; j < taskNum; j++){
                System.out.print(df.format(valueMatrix[i][j]) + " ");
            }
            System.out.println();
        }*/

        return valueMatrix;
    }

}
