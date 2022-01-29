package pl.edu.pw.ee;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.junit.Assert;
import org.junit.Rule;


public class HashListChainingTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void Should_Throw_IA_When_Given_Negative_Size(){
        exception.expect(IllegalArgumentException.class);
        HashListChaining<String> table = new HashListChaining<String>(-1);
    }

    @Test
    public void Should_Return_Object_From_Table(){
        HashListChaining<String> table = new HashListChaining<String>(5);
        String x =  new String();
        String expected = new String();
        x = "Ania";

        table.add(x);
        expected = table.get(x);

        Assert.assertEquals(expected, x);
    }

    @Test
    public void Should_Delete_Object(){
        HashListChaining<String> table = new HashListChaining<String>(1);
        String x = new String();
        String expected = new String();
        String real = new String();
        x = "Ania";
        
        table.add(x);
        expected = null;
        table.delete(x);
        real = table.get(x);

        Assert.assertEquals(expected, real);
    }

    @Test
    public void Should_Delete_Object_In_A_Middle_Of_A_Node(){
        HashListChaining<String> table = new HashListChaining<String>(1);
        String x = new String();
        String y = new String();
        String z = new String();
        String expected_null = new String();
        String expected_x = new String();
        String expected_z = new String();
        String real = new String();
        x = "Ania";
        y = "Basia";
        z = "Kasia";

        table.add(x);
        table.add(y);
        table.add(z);
        expected_null = null;
        table.delete(y);
        real = table.get(y);
        expected_x = table.get(x);
        expected_z = table.get(z);

        Assert.assertEquals(expected_null, real);
        Assert.assertEquals(expected_x, x);
        Assert.assertEquals(expected_z, z);
    }

    @Test
    public void Getting_Non_Existing_Object(){
        HashListChaining<String> table = new HashListChaining<String>(1);
        String x = new String();
        String expected = new String();
        String real = new String ();

        x = "Ania";
        real = null;

        expected = table.get(x);

        Assert.assertEquals(expected, real);
    }

    @Test
    public void Deleting_Non_Existing_Object_From_An_Empty_Table(){
        exception.expect(IllegalArgumentException.class);
        HashListChaining<String> table = new HashListChaining<String>(1);
        String x = new String();

        x = "Ania";

        table.delete(x);
    }

    @Test
    public void Deleting_Non_Existing_Object_From_Table(){
        exception.expect(IllegalArgumentException.class);
        HashListChaining<String> table = new HashListChaining<String>(1);
        String x = new String();
        String y = new String();

        x = "Ania";
        y = "Basia";

        table.add(x);
        table.delete(y);
    }

    //TimeTesting using https://www.mit.edu/~ecprice/wordlist.100000

    @Test
    public void TimeTest_For_HashTable_Sized_4096() throws IOException{
        HashListChaining<String> table = new HashListChaining<String>(4096);

        File save = new File("src/test/java/pl/edu/pw/ee/Data/Size_4096.txt");
        save.createNewFile();
        FileWriter write = new FileWriter("src/test/java/pl/edu/pw/ee/Data/Size_4096.txt");


        URL oracle = new URL("https://www.mit.edu/~ecprice/wordlist.100000");
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

        String word;

        while ((word = in.readLine()) != null){
            table.add(word);
        }

        double sr = 0;
        
        for(int i = 0; i < 30; i++ ){
            double start = System.currentTimeMillis();

            
                BufferedReader tmp = new BufferedReader(new InputStreamReader(oracle.openStream()));
                while ((word = tmp.readLine()) != null){
                    table.get(word);
                }
                tmp.close();
            
            double end = System.currentTimeMillis();

            if(i > 9 && i < 20){
                sr+=end-start;
            }

            write.write(i+1 + ":  " + (end-start) +"\n");
        }
        write.write("Srednia: " + sr/10 +"\n");


        in.close();
        write.close();
    }


    @Test
    public void TimeTest_For_HashTable_Sized_8192() throws IOException{
        HashListChaining<String> table = new HashListChaining<String>(8192);

        File save = new File("src/test/java/pl/edu/pw/ee/Data/Size_8192.txt");
        save.createNewFile();
        FileWriter write = new FileWriter("src/test/java/pl/edu/pw/ee/Data/Size_8192.txt");


        URL oracle = new URL("https://www.mit.edu/~ecprice/wordlist.100000");
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

        String word;

        while ((word = in.readLine()) != null){
            table.add(word);
        }

        double sr = 0;
        
        for(int i = 0; i < 30; i++ ){
            double start = System.currentTimeMillis();

            BufferedReader tmp = new BufferedReader(new InputStreamReader(oracle.openStream()));
                while ((word = tmp.readLine()) != null){
                    table.get(word);
                }
            tmp.close();
            
            double end = System.currentTimeMillis();

            if(i > 9 && i < 20){
                sr+=end-start;
            }

            write.write(i+1 + ":  " + (end-start) +"\n");
        }
        write.write("Srednia: " + sr/10 +"\n");


        in.close();
        write.close();
    }

    @Test
    public void TimeTest_For_HashTable_Sized_16384() throws IOException{
        HashListChaining<String> table = new HashListChaining<String>(16384);

        File save = new File("src/test/java/pl/edu/pw/ee/Data/Size_16384.txt");
        save.createNewFile();
        FileWriter write = new FileWriter("src/test/java/pl/edu/pw/ee/Data/Size_16384.txt");


        URL oracle = new URL("https://www.mit.edu/~ecprice/wordlist.100000");
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

        String word;

        while ((word = in.readLine()) != null){
            table.add(word);
        }

        double sr = 0;
        
        for(int i = 0; i < 30; i++ ){
            double start = System.currentTimeMillis();

            BufferedReader tmp = new BufferedReader(new InputStreamReader(oracle.openStream()));
            while ((word = tmp.readLine()) != null){
                table.get(word);
            }
            tmp.close();
            double end = System.currentTimeMillis();

            if(i > 9 && i < 20){
                sr+=end-start;
            }

            write.write(i+1 + ":  " + (end-start) +"\n");
        }
        write.write("Srednia: " + sr/10 +"\n");


        in.close();
        write.close();
    }

    @Test
    public void TimeTest_For_HashTable_Sized_32768() throws IOException{
        HashListChaining<String> table = new HashListChaining<String>(32768);

        File save = new File("src/test/java/pl/edu/pw/ee/Data/Size_32768.txt");
        save.createNewFile();
        FileWriter write = new FileWriter("src/test/java/pl/edu/pw/ee/Data/Size_32768.txt");


        URL oracle = new URL("https://www.mit.edu/~ecprice/wordlist.100000");
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

        String word;

        while ((word = in.readLine()) != null){
            table.add(word);
        }

        double sr = 0;
        
        for(int i = 0; i < 30; i++ ){
            double start = System.currentTimeMillis();

            BufferedReader tmp = new BufferedReader(new InputStreamReader(oracle.openStream()));
                while ((word = tmp.readLine()) != null){
                    table.get(word);
                }
                tmp.close();
            double end = System.currentTimeMillis();

            if(i > 9 && i < 20){
                sr+=end-start;
            }

            write.write(i+1 + ":  " + (end-start) +"\n");
        }
        write.write("Srednia: " + sr/10 +"\n");


        in.close();
        write.close();
    }

    @Test
    public void TimeTest_For_HashTable_Sized_65536() throws IOException{
        HashListChaining<String> table = new HashListChaining<String>(65536);

        File save = new File("src/test/java/pl/edu/pw/ee/Data/Size_65536.txt");
        save.createNewFile();
        FileWriter write = new FileWriter("src/test/java/pl/edu/pw/ee/Data/Size_65536.txt");


        URL oracle = new URL("https://www.mit.edu/~ecprice/wordlist.100000");
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

        String word;

        while ((word = in.readLine()) != null){
            table.add(word);
        }

        double sr = 0;
        
        for(int i = 0; i < 30; i++ ){
            double start = System.currentTimeMillis();

            BufferedReader tmp = new BufferedReader(new InputStreamReader(oracle.openStream()));
                while ((word = tmp.readLine()) != null){
                    table.get(word);
                }
                tmp.close();
            double end = System.currentTimeMillis();

            if(i > 9 && i < 20){
                sr+=end-start;
            }

            write.write(i+1 + ":  " + (end-start) +"\n");
        }
        write.write("Srednia: " + sr/10 +"\n");


        in.close();
        write.close();
    }

    @Test
    public void TimeTest_For_HashTable_Sized_131072() throws IOException{
        HashListChaining<String> table = new HashListChaining<String>(131072);

        File save = new File("src/test/java/pl/edu/pw/ee/Data/Size_131072.txt");
        save.createNewFile();
        FileWriter write = new FileWriter("src/test/java/pl/edu/pw/ee/Data/Size_131072.txt");


        URL oracle = new URL("https://www.mit.edu/~ecprice/wordlist.100000");
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

        String word;

        while ((word = in.readLine()) != null){
            table.add(word);
        }
        
        double sr = 0;

        for(int i = 0; i < 30; i++ ){
            double start = System.currentTimeMillis();

            BufferedReader tmp = new BufferedReader(new InputStreamReader(oracle.openStream()));
                while ((word = tmp.readLine()) != null){
                    table.get(word);
                }
                tmp.close();
            double end = System.currentTimeMillis();

            if(i > 9 && i < 20){
                sr+=end-start;
            }

            write.write(i+1 + ":  " + (end-start) +"\n");
        }
        write.write("Srednia: " + sr/10 +"\n");

        in.close();
        write.close();
    }

    @Test
    public void TimeTest_For_HashTable_Sized_262144() throws IOException{
        HashListChaining<String> table = new HashListChaining<String>(262144);

        File save = new File("src/test/java/pl/edu/pw/ee/Data/Size_262144.txt");
        save.createNewFile();
        FileWriter write = new FileWriter("src/test/java/pl/edu/pw/ee/Data/Size_262144.txt");


        URL oracle = new URL("https://www.mit.edu/~ecprice/wordlist.100000");
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

        String word;

        while ((word = in.readLine()) != null){
            table.add(word);
        }

        double sr = 0;
        
        for(int i = 0; i < 30; i++ ){
            double start = System.currentTimeMillis();

            BufferedReader tmp = new BufferedReader(new InputStreamReader(oracle.openStream()));
                while ((word = tmp.readLine()) != null){
                    table.get(word);
                }
                tmp.close();
            double end = System.currentTimeMillis();

            if(i > 9 && i < 20){
                sr+=end-start;
            }

            write.write(i+1 + ":  " + (end-start) +"\n");
        }
        write.write("Srednia: " + sr/10 +"\n");


        in.close();
        write.close();
    }
}
