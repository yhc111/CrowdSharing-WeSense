package team.crowdos.crowdsharing.algorithm.Util;

public class QuickSort {

    public void quickSort(int[] R, int low, int high){
        int temp;
        int i = low,j = high;
        if(low < high){
            temp = R[low];
            while (i < j){
                while (j > i && R[j] > temp) --j;
                if (i < j){
                    R[i] = R[j];
                    ++i;
                }
                while (i < j && R[i] < temp) ++i;
                if (i < j){
                    R[j] = R[i];
                    --j;
                }
            }
            R[i] = temp;
            quickSort(R,low,i-1);
            quickSort(R,i+1,high);
        }
    }

}
