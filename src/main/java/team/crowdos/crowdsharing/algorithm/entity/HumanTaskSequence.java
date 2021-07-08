package team.crowdos.crowdsharing.algorithm.entity;

import java.util.List;

public class HumanTaskSequence {

    private List<Integer> list;
    private double maxValue;
    private double totalDistance;

    public HumanTaskSequence(){}

    public HumanTaskSequence(List<Integer> list, double maxValue, double totalDistance) {
        this.list = list;
        this.maxValue = maxValue;
        this.totalDistance = totalDistance;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }
}
