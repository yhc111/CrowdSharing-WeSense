package team.crowdos.crowdsharing.algorithm.entity;

public class Task_entropy {

    private int taskID;
    private double latitude;
    private double longitude;
    private int taskType;
    private double entropy;

    public Task_entropy(){

    }

    public Task_entropy(int taskID, double latitude, double longitude, int taskType, double entropy) {
        this.taskID = taskID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.taskType = taskType;
        this.entropy = entropy;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public double getEntropy() {
        return entropy;
    }

    public void setEntropy(double entropy) {
        this.entropy = entropy;
    }

    @Override
    public String toString() {
        return taskID + "," + latitude + "," + longitude + "," + taskType + "," + entropy;
    }
}
