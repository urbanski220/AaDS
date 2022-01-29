package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class InsertionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null || nums.length==0) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }
        int n = nums.length;
        for (int i = 1; i < n; ++i){
            double a = nums[i]; 
            int j = i - 1;
            while (j >= 0 && nums[j] > a){
            nums[j + 1] = nums[j];
                j = j - 1;
            }
        nums[j + 1] = a;
        }
    }

}
