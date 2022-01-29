package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;

import org.junit.Test;

import pl.edu.pw.ee.services.HashTable;

public class HashLinearProbingTest {
    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenInitialSizeIsLowerThanOne() {
        // given
        int initialSize = 0;

        // when
        HashTable<Double> hash = new HashLinearProbing<>(initialSize);

        // then
        assert false;
    }

    @Test
    public void should_CorrectlyAddNewElems_WhenNotExistInHashTable() {
        // given
        HashTable<String> emptyHash = new HashLinearProbing<>();
        String newEleme = "nothing special";

        // when
        int nOfElemsBeforePut = getNumOfElems(emptyHash);
        emptyHash.put(newEleme);
        int nOfElemsAfterPut = getNumOfElems(emptyHash);
        System.out.println(nOfElemsBeforePut);
        System.out.println(nOfElemsAfterPut);

        // then
        assertEquals(0, nOfElemsBeforePut);
        assertEquals(1, nOfElemsAfterPut);
    }

    private int getNumOfElems(HashTable<?> hash) {
        String fieldNumOfElems = "nElems";
        try {
            System.out.println(hash.getClass().getSuperclass().getName());
            Field field = hash.getClass().getSuperclass().getDeclaredField(fieldNumOfElems);
            field.setAccessible(true);

            int numOfElems = field.getInt(hash);

            return numOfElems;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getTest(){
        HashTable<String> Hash = new HashLinearProbing<>(1);
        String newEleme = "a";
        String newEleme2 = "b";
        String newEleme3 = "c";
        Hash.put(newEleme);
        Hash.put(newEleme2);
        Hash.put(newEleme3);
        assertEquals(newEleme, Hash.get(newEleme));
        assertEquals(newEleme2, Hash.get(newEleme2));
        assertEquals(newEleme3, Hash.get(newEleme3));
    }

    @Test
    public void deleteTest(){
        HashTable<String> Hash = new HashLinearProbing<>(10);
        String newEleme = "a";
        String newEleme2 = "b";
        String newEleme3 = "c";
        Hash.put(newEleme);
        Hash.put(newEleme2);
        Hash.put(newEleme3);
        Hash.delete(newEleme2);
        assertEquals(null, Hash.get(newEleme2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullInputTest(){
        HashTable<String> Hash = new HashLinearProbing<>(10);
        String newEleme = null;
        Hash.put(newEleme);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullSearchingTest(){
        HashTable<String> Hash = new HashLinearProbing<>(10);
        String newEleme = null;
        Hash.get(newEleme);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullDeletingTest(){
        HashTable<String> Hash = new HashLinearProbing<>(10);
        String newEleme = null;
        Hash.delete(newEleme);
    }

    @Test
    public void SearchForNonExistingElem(){
        HashTable<String> Hash = new HashLinearProbing<>(10);
        String newEleme = "a";
        String search = "b";

        Hash.put(newEleme);
        assertEquals(null, Hash.get(search));
    }

    @Test
    public void SameArgumentInput(){
        HashLinearProbing<String> Hash = new HashLinearProbing<>(1);
        String newEleme = "a";
        Hash.put(newEleme);
        Hash.put(newEleme);
        assertEquals(1, Hash.getnElems());
    }

    @Test
    public void DeletingNonExistingTest(){
        HashTable<String> Hash = new HashLinearProbing<>(10);
        String newEleme = "a";
        String search = "b";

        Hash.put(newEleme);
        Hash.delete(search);
    }

    






    @Test
    public void TimeTesting() throws IOException{
        String[] words = new String[100000];
        File save = new File("src/test/java/pl/edu/pw/ee/LinearTest.txt");
        save.createNewFile();
        FileWriter write = new FileWriter("src/test/java/pl/edu/pw/ee/LinearTest.txt");
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
            System.out.println(i);
            HashTable<String> Hash = new HashLinearProbing<>(512*((int)Math.pow(2,i)));

            for(int j = 0; j < 30; j++){
                System.out.println(j);
               
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

}
