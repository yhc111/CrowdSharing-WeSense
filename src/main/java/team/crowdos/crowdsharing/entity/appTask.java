package team.crowdos.crowdsharing.entity;

import java.util.Date;

public class appTask {
    private Integer taskid;

    private String taskname;

    private Date posttime;

    private Date deadline;

    private Integer userid;

    private String username;

    private Float coin;

    private Integer totalnum;

    private Integer taskstatus;

    private Integer taskkind;

    private Integer temp;

    private String describe_task;

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

    public Date getPosttime() {
        return posttime;
    }

    public void setPosttime(Date posttime) {
        this.posttime = posttime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Float getCoin() {
        return coin;
    }

    public void setCoin(Float coin) {
        this.coin = coin;
    }

    public Integer getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(Integer totalnum) {
        this.totalnum = totalnum;
    }

    public Integer getTaskstatus() {
        return taskstatus;
    }

    public void setTaskstatus(Integer taskstatus) {
        this.taskstatus = taskstatus;
    }

    public Integer getTaskkind() {
        return taskkind;
    }

    public void setTaskkind(Integer taskkind) {
        this.taskkind = taskkind;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public String getdescribe_task() {
        return describe_task;
    }

    public void setdescribe_task(String describe_task) {
        this.describe_task = describe_task == null ? null : describe_task.trim();
    }
}