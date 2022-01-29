package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class HeapSort implements Sorting  {

    @Override
    public void sort(double[] nums) {
        if (nums == null || nums.length==0) {
            throw new IllegalArgumentException("Nums array cannot be null or empty");
        }
        int n = nums.length;
        Double[] values = new Double[n];
        for (int i = 0; i < n; i++) {
            values[i] = nums[i];
        }
        heapSort(values);
        for (int i = 0; i < n; i++) {
            nums[i] = values[i];
        }
        
    }

    public <T extends Comparable<T>> void heapSort (T[] nums) {
        
        Heap<T> heap = new Heap<T>(nums);
        
        int end = nums.length - 1;
        while (end > 0) {
            T tmp = heap.pop();
            nums[end] = tmp;
            end--;
        }
    }
    
}
