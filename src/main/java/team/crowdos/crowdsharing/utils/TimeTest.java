package team.crowdos.crowdsharing.utils;

import java.util.Timer;
import java.util.TimerTask;

public class TimeTest {
    public static void main(String[] args) {
        timer1();
    }

    //第一种方法：设定指定任务task在指定时间time执行任务
    public static void timer1(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

            }
        }, 1000,1000);
    }
}
