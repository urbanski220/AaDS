package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.junit.Test;

public class RedBlackTreeTest {
    RedBlackTree<String, Integer> tree = new RedBlackTree<>();

    @Test
    public void PutTest(){
        tree.put("c", 1);
        tree.put("z", 2);
        tree.put("l", 3);
        tree.put("o", 4);
        tree.put("w", 5);
        tree.put("i", 6);
        tree.put("e", 7);
        tree.put("k", 8);
    }

    @Test
    public void GetTest(){
        tree.put("c", 1);
        tree.put("z", 2);
        tree.put("l", 3);
        tree.put("o", 4);
        tree.put("w", 5);
        tree.put("i", 6);
        tree.put("e", 7);
        tree.put("k", 8);
        assertEquals(2, (int) tree.get("z"));
        assertEquals(1, (int) tree.get("c"));
        assertEquals(7, (int) tree.get("e"));
    }


    @Test
    public void DeleteMaxTest(){
        tree.put("c", 1);
        tree.put("z", 2);
        tree.put("l", 3);
        tree.put("o", 4);
        tree.put("w", 5);
        tree.put("i", 6);
        tree.put("e", 7);
        tree.put("k", 8);
        tree.deleteMax();
        tree.deleteMax();
        tree.deleteMax();
        assertEquals(null, tree.get("z"));
        assertEquals(null, tree.get("w"));
        assertEquals(null, tree.get("o"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void EmptyTreeDeleteMaxTest(){
        tree.deleteMax();
    }

    @Test(expected = IllegalArgumentException.class)
    public void NullNodeKeyTest() {
        tree.put(null, 234);
    }
    

    @Test(expected = IllegalArgumentException.class)
    public void NullNodeValueTest() {
        tree.put("a", null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void NullKeyInGetValueTest() {
        tree.get(null);
    }

    @Test
    public void NoSuchValueInATreeTest() {
        assertEquals(null, tree.get("o"));
    }

    @Test
    public void ValueOverwritingTest() {
        tree.put("a", 1);
        tree.put("b", 2);
        tree.put("c", 3);
        

        tree.put("b", 7);
        assertEquals(7, (int) tree.get("b"));
    }

    @Test
    public void isTreeBuiltCorrectly(){
        tree.put("c", 1);
        tree.put("z", 2);
        tree.put("l", 3);
        tree.put("o", 4);
        tree.put("w", 5);
        tree.put("i", 6);
        tree.put("e", 7);
        tree.put("k", 8);
        assertEquals("l", tree.root.key);
        assertEquals("e", tree.root.left.key);
        assertEquals("c", tree.root.left.left.key);
        assertEquals("k", tree.root.left.right.key);
        assertEquals("i", tree.root.left.right.left.key);
        assertEquals("w", tree.root.right.key);
        assertEquals("o", tree.root.right.left.key);
        assertEquals("z", tree.root.right.right.key);
    }

    @Test
    public void PreOrderTest(){
        tree.put("c", 25);
        tree.put("z", 128);
        tree.put("l", 543);
        tree.put("o", 37);
        tree.put("w", 259);
        tree.put("i", 43);
        tree.put("e", 25);
        tree.put("k", 20);
        String expected = "l:543 e:25 c:25 k:20 i:43 w:259 o:37 z:128";

        String preOrder = tree.getPreOrder();
        assertEquals(expected, preOrder);
    }

    @Test
    public void InOrderTest(){
        tree.put("c", 25);
        tree.put("z", 128);
        tree.put("l", 543);
        tree.put("o", 37);
        tree.put("w", 259);
        tree.put("i", 43);
        tree.put("e", 25);
        tree.put("k", 20);
        String expected = "c:25 e:25 i:43 k:20 l:543 o:37 w:259 z:128";

        String inOrder = tree.getInOrder();
        assertEquals(expected, inOrder);
    }

    @Test
    public void PostOrderTest(){
        tree.put("c", 25);
        tree.put("z", 128);
        tree.put("l", 543);
        tree.put("o", 37);
        tree.put("w", 259);
        tree.put("i", 43);
        tree.put("e", 25);
        tree.put("k", 20);
        String expected = "c:25 i:43 k:20 e:25 o:37 z:128 w:259 l:543";
        

        String postOrder = tree.getPostOrder();
        assertEquals(expected, postOrder);
    }

    @Test
    public void ReccurencyChangeTest() throws IOException{
        File save = new File("src/test/java/pl/edu/pw/ee/RecTest.txt");
        save.createNewFile();
        FileWriter write = new FileWriter("src/test/java/pl/edu/pw/ee/RecTest.txt");


        URL oracle = new URL("https://www.mit.edu/~ecprice/wordlist.100000");
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));

        String word;
        int i = 0;
        int rec = 0;
        

        while ((word = in.readLine()) != null){
            for(int j = 0; j < 10; j++){
                tree.put(word.concat(String.valueOf(j)), i);
                i++;
                if(rec < tree.getrec()){
                    rec = tree.getrec();
                    write.write("Liczba elementow: " + i + "  liczba rekurencji: " + rec + '\n');

                }
            }
        }

        in.close();
        write.close();
    }
}
