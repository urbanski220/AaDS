package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

import pl.edu.pw.ee.services.HashTable;

public class HashDoubleHashingTest {
    @Test
    public void TimeTesting() throws IOException{
        String[] words = new String[100000];
        File save = new File("src/test/java/pl/edu/pw/ee/DoubleTest.txt");
        save.createNewFile();
        FileWriter write = new FileWriter("src/test/java/pl/edu/pw/ee/DoubleTest.txt");
        BufferedReader br = new BufferedReader(new FileReader("src/test/java/pl/edu/pw/ee/words.txt"));

        String w;
        int k = 0;
        while ((w = br.readLine()) != null) {
            words[k] = w;
            k++;
        }

        double[] inTime = new double[30];
        double[] outTime = new double[30];
        double[] inAvg = new double[10];
        double[] outAvg = new double[10];


        for(int i = 0; i < 10; i++){
            HashTable<String> Hash = new HashDoubleHashing<>(512*((int)Math.pow(2,i)));

            for(int j = 0; j < 30; j++){
                double startIn = System.currentTimeMillis();

                for(int l = 0; l < 100000; l++){
                    Hash.put(words[l]);
                }

                double endIn = System.currentTimeMillis();

                inTime[j] = endIn - startIn; 
                
                double startGet = System.currentTimeMillis();

                for(int l = 0; l < 100000; l++){
                    Hash.get(words[l]);
                }
                double endGet = System.currentTimeMillis();

                outTime[j] = endGet - startGet;

                Arrays.sort(inTime);
                Arrays.sort(outTime);

                for(int m = 9; m < 20; m++){
                    inAvg[i] += inTime[m];
                    outAvg[i] += outTime[m];
                }
            }
        }

        write.write("Input times: \n");
        for(int i = 0; i < 10; i++){
            write.write(inAvg[i]/10 + "\n");
        }
        write.write("\n");
        write.write("Output times: \n");
        for(int i = 0; i < 10; i++){
            write.write(outAvg[i]/10 + "\n");
        }

        write.close();
        br.close();
    }

    @Test (expected = IllegalArgumentException.class)
    public void wrongSizeTest(){
        HashTable<String> Hash = new HashDoubleHashing<>(0);

        assert false;
    }
}
