package pl.edu.pw.ee;

import java.util.*;
import pl.edu.pw.ee.services.HeapInterface;

public class Heap<T extends Comparable<T>> implements HeapInterface<T> {

    private T[] tab;
    private int size;


    public Heap(T[] nums) {
        tab = nums;
        size = nums.length;
        make();
    }
    

    private void make() {
        int last = tab.length - 1;
        int parent = (last - 1)/2;

        for (int i = parent; i >= 0; i--)
            goDown(i);
    }
    

    public void put(T item) {
        tab[size] = item;
        goUp(size);
        size++;
    }
    

    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        
        T tmp = tab[0];
        tab[0] = tab[size - 1];
        tab[size - 1] = null;
        size--;
        goDown(0);
        
        return tmp;
    }

    private void goUp(int i) {
        T tmp = tab[i];
        int child = i;

        while (child > 0) {
            int parent = (child - 1)/2;
            
            if (tmp.compareTo(tab[parent]) <= 0)
                break;
            
            tab[child] = tab[parent];
            child = parent;
        }
        tab[child] = tmp;
    }


    private void goDown(int i) {
        T tmp = tab[i];
        int parent = i;
        int child = 2 * parent + 1;

        while (child < size) {
            if (child < size - 1  && tab[child].compareTo(tab[child + 1]) < 0)
                child = child + 1;
            
            if (tmp.compareTo(tab[child]) >= 0)
                break;
            
            tab[parent] = tab[child];
            parent = child;
            child = 2 * parent + 1;
        }
        tab[parent] = tmp;
    }
}
