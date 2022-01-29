package pl.edu.pw.ee;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class SelectionSortTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    
    private InsertionSort sorter = new InsertionSort();


    @Test
    public void should_Throw_IA_When_ArrayIsNull(){
        double[] values = null;
        exception.expect(IllegalArgumentException.class);
        sorter.sort(values);
    }


    @Test
    public void should_Throw_IA_When_ArrayIsEmpty(){
        double[] values = {};
        exception.expect(IllegalArgumentException.class);

        sorter.sort(values);
    }


    @Test
    public void should_Return_Sorted_Vector(){
        double[] values = { 9, -3, 5, 0, 1};
        double[] expectedOrder = { -3, 0, 1, 5, 9};
 
        sorter.sort(values);
 
        Assert.assertArrayEquals(values, expectedOrder, 0);
    }

    @Test
    public void should_Return_Sorted_Vector2(){
        double[] values = {1, 2, 3, 4, 5, 6, 7, 8};
        double[] expectedOrder = { 1, 2, 3, 4, 5, 6, 7, 8};
 
        sorter.sort(values);
 
        Assert.assertArrayEquals(values, expectedOrder, 0);
    }

    @Test
    public void should_Return_Sorted_Vector3(){
        double[] values = { 8,7,6,5,4,3,2,1};
        double[] expectedOrder = { 1, 2, 3, 4, 5, 6, 7, 8};
 
        sorter.sort(values);
 
        Assert.assertArrayEquals(values, expectedOrder, 0);
    }

    @Test
    public void should_Return_Sorted_Vector4(){
        double[] values = { 4,4,6,6,2,2,1,1,0,0};
        double[] expectedOrder = { 0,0,1,1,2,2,4,4,6,6};
 
        sorter.sort(values);
 
        Assert.assertArrayEquals(values, expectedOrder, 0);
    }
}
