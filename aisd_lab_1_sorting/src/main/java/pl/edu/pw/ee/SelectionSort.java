package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class SelectionSort implements Sorting {

    @Override
    public void sort(double[] nums) { 
        if (nums == null || nums.length==0) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }
        int n = nums.length;
        for (int i = 0; i < n-1; i++){
            int min = i;
            for (int j = i+1; j < n; j++)
                if (nums[j] < nums[min])
                    min = j;
            double temp = nums[min];
            nums[min] = nums[i];
            nums[i] = temp;
        }  
    }

}
