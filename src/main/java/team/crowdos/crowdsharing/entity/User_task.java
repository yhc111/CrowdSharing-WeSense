package team.crowdos.crowdsharing.entity;

public class User_task {
    private Integer userTaskid;

    private Integer userid;

    private Integer taskid;

    private Integer userTaskstatus;

    private String content;

    private String image;

    private Integer tasktype;

    public Integer getUserTaskid() {
        return userTaskid;
    }

    public void setUserTaskid(Integer userTaskid) {
        this.userTaskid = userTaskid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public Integer getUserTaskstatus() {
        return userTaskstatus;
    }

    public void setUserTaskstatus(Integer userTaskstatus) {
        this.userTaskstatus = userTaskstatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Integer getTasktype() {
        return tasktype;
    }

    public void setTasktype(Integer tasktype) {
        this.tasktype = tasktype;
    }

    @Override
    public String toString() {
        return "User_task{" +
                "userTaskid=" + userTaskid +
                ", userid=" + userid +
                ", taskid=" + taskid +
                ", userTaskstatus=" + userTaskstatus +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", tasktype=" + tasktype +
                '}';
    }
}