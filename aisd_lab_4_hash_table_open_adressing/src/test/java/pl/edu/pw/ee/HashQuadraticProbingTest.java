package pl.edu.pw.ee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

import pl.edu.pw.ee.services.HashTable;

public class HashQuadraticProbingTest {

    @Test
    public void TimeTesting() throws IOException{
        double a = 0.5;
        double b = 0.5;
        String[] words = new String[100000];
        File save = new File("src/test/java/pl/edu/pw/ee/QuadraticTest.txt");
        save.createNewFile();
        FileWriter write = new FileWriter("src/test/java/pl/edu/pw/ee/QuadraticTest.txt");
        BufferedReader br = new BufferedReader(new FileReader("src/test/java/pl/edu/pw/ee/words.txt"));

        String w;
        int k = 0;
        while ((w = br.readLine()) != null) {
            words[k] = w;
            k++;
        }

        double[] inTime = new double[30];
        double[] outTime = new double[30];
        double[][] inAvg = new double[10][10];
        double[][] outAvg = new double[10][10];

        for(int n = 0; n < 10; n++){
            a += 0.5;
            b += 0.5;
            for(int i = 0; i < 10; i++){
                
                HashTable<String> Hash = new HashQuadraticProbing<>(512*((int)Math.pow(2,i)),a,b);

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
                        inAvg[n][i] += inTime[m];
                        outAvg[n][i] += outTime[m];
                    }
                }
            }
           
        }

        for(int j = 0; j < 10; j++){

            write.write("Input times for a and b = " + 0.5*(1+j)+ "  " + 0.5*(1+j) + " : \n");
            for(int i = 0; i < 10; i++){
                write.write(inAvg[j][i]/10 + "\n");
            }
            write.write("Output times: \n");
            for(int i = 0; i < 10; i++){
                write.write(outAvg[j][i]/10 + "\n");
            }
            write.write("\n");
        }
    

        write.close();
        br.close();
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void wrongSizeTest(){
        HashTable<String> Hash = new HashQuadraticProbing<>(0,1,2);

        assert false;
    }

    @Test (expected = IllegalArgumentException.class)
    public void wrong_A_and_B_Test(){
        HashTable<String> Hash = new HashQuadraticProbing<>(5,0,0);

        assert false;
    }
}
