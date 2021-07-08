package team.crowdos.crowdsharing.entity;

import java.util.Date;

public class Task {
    private Integer taskid;

    private String taskname;

    private String userid;

    private String username;

    private Integer taskkind;

    private String describe_task;

    private Integer taskstatus;

    private Date posttime;

    private Date deadline;

    private Integer totalNum;

    private Integer temp;

    private Double latitude;

    private Double longitude;

    private Integer totalworker;

    private Integer nowworker;

    private Integer needworker;

    private Float coin;

    public Task(Integer taskid, String taskname, String userid, String username, Integer taskkind, String describe_task, Integer taskstatus, Date posttime, Date deadline, Integer totalNum, Integer temp, Double latitude, Double longitude, Integer totalworker, Integer nowworker, Integer needworker, Float coin) {
        this.taskid = taskid;
        this.taskname = taskname;
        this.userid = userid;
        this.username = username;
        this.taskkind = taskkind;
        this.describe_task = describe_task;
        this.taskstatus = taskstatus;
        this.posttime = posttime;
        this.deadline = deadline;
        this.latitude = latitude;
        this.longitude = longitude;
        this.totalworker = totalworker;
        this.nowworker = nowworker;
        this.needworker = needworker;
        this.coin = coin;
        this.totalNum = totalNum;
        this.temp = temp;
    }

    public Task(){}

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname == null ? null : taskname.trim();
    }

    public String getuserid() {
        return userid;
    }

    public void setuserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer gettaskkind() {
        return taskkind;
    }

    public void settaskkind(Integer taskkind) {
        this.taskkind = taskkind;
    }

    public String getdescribe_task() {
        return describe_task;
    }

    public void setdescribe_task(String describe_task) {
        this.describe_task = describe_task == null ? null : describe_task.trim();
    }

    public Integer getTaskstatus() {
        return taskstatus;
    }

    public void setTaskstatus(Integer taskstatus) {
        this.taskstatus = taskstatus;
    }

    public Date getposttime() {
        return posttime;
    }

    public void setposttime(Date posttime) {
        this.posttime = posttime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getTotalworker() {
        return totalworker;
    }

    public void setTotalworker(Integer totalworker) {
        this.totalworker = totalworker;
    }

    public Integer getNowworker() {
        return nowworker;
    }

    public void setNowworker(Integer nowworker) {
        this.nowworker = nowworker;
    }

    public Integer getNeedworker() {
        return needworker;
    }

    public void setNeedworker(Integer needworker) {
        this.needworker = needworker;
    }

    public Float getCoin() {
        return coin;
    }

    public void setCoin(Float coin) {
        this.coin = coin;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskid=" + taskid +
                ", taskname='" + taskname + '\'' +
                ", userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", taskkind=" + taskkind +
                ", describe_task='" + describe_task + '\'' +
                ", taskstatus=" + taskstatus +
                ", posttime=" + posttime +
                ", deadline=" + deadline +
                ", totalNum=" + totalNum +
                ", temp=" + temp +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", totalworker=" + totalworker +
                ", nowworker=" + nowworker +
                ", needworker=" + needworker +
                ", coin=" + coin +
                '}';
    }
}