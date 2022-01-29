package pl.edu.pw.ee;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.junit.Test;

public class SelectionSortTimeTest {

    private QuickSort sorter = new QuickSort();

    @Test
    public void optimistic_Case() throws IOException{
        double[] data = new double[5];

        File save = new File("src/test/java/pl/edu/pw/ee/DataSelectionSortOptimistic.txt");
        save.createNewFile();
        FileWriter write = new FileWriter("src/test/java/pl/edu/pw/ee/DataSelectionSortOptimistic.txt");

        for(int i = 0; i < 20; i++){
            if(i==0){
                for(int k = 0; k < 5; k++){
                    data[k] = (double)k;
                }
            }
            else{
            double[] newdata = new double[data.length + 5];
            System.arraycopy(data, 0, newdata, 0, data.length);
            for(int j = 0; j < 5; j++){
                newdata[i*5 + j] = (double)i*5 +j;
            }
            data = newdata;
            }

            double[] sortData = new double[data.length];
            System.arraycopy(data, 0, sortData, 0, data.length);

            long start = System.currentTimeMillis();

            for(int t = 0; t < 1000000; t++)
                sorter.sort(sortData);

            long end = System.currentTimeMillis();

            write.write(end-start +"\n");
        }

        write.close();

    }

    @Test
    public void pesimistic_Case() throws IOException{
        double[] data = new double[5];

        File save = new File("src/test/java/pl/edu/pw/ee/DataSelectionSortPesimistic.txt");
        save.createNewFile();
        FileWriter write = new FileWriter("src/test/java/pl/edu/pw/ee/DataSelectionSortPesimistic.txt");

        for(int i = 0; i < 20; i++){
            if(i==0){
                for(int k = 0; k < 5; k++){
                    data[k] = (double)100-k;
                }
            }
            else{
            double[] newdata = new double[data.length + 5];
            System.arraycopy(data, 0, newdata, 0, data.length);
            for(int j = 0; j < 5; j++){
                newdata[i*5 + j] = (double)(100-(i*5 +j));
            }
            data = newdata;
            }

            double[] sortData = new double[data.length];
            System.arraycopy(data, 0, sortData, 0, data.length);

            long start = System.currentTimeMillis();

            for(int t = 0; t < 1000000; t++)
                sorter.sort(sortData);

            long end = System.currentTimeMillis();

            write.write(end-start +"\n");
        }

        write.close();
    }

    @Test
    public void random_Case() throws IOException{
        double[] data = new double[5];

        File save = new File("src/test/java/pl/edu/pw/ee/DataSelectionSortRandom.txt");
        save.createNewFile();
        FileWriter write = new FileWriter("src/test/java/pl/edu/pw/ee/DataSelectionSortRandom.txt");

        for(int i = 0; i < 20; i++){
            if(i==0){
                for(int k = 0; k < 5; k++){
                    data[k] = (double)100-k;
                }
            }
            else{
            double[] newdata = new double[data.length + 5];
            System.arraycopy(data, 0, newdata, 0, data.length);
            for(int j = 0; j < 5; j++){
                Random ran = new Random(1234);
                newdata[i*5 + j] = ran.nextDouble(100);
            }
            data = newdata;
            }

            double[] sortData = new double[data.length];
            System.arraycopy(data, 0, sortData, 0, data.length);

            long start = System.currentTimeMillis();

            for(int t = 0; t < 1000000; t++)
                sorter.sort(sortData);

            long end = System.currentTimeMillis();

            write.write(end-start +"\n");
        }

        write.close();
        
    }
    
}
